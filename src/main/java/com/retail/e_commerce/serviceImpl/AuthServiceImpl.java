package com.retail.e_commerce.serviceImpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.retail.e_commerce.Entity.Customer;
import com.retail.e_commerce.Entity.Seller;
import com.retail.e_commerce.Entity.User;
import com.retail.e_commerce.Exception.OtpExpiredException;
import com.retail.e_commerce.Exception.OtpInvalidException;
import com.retail.e_commerce.Exception.RegistrationSessionExpiredException;
import com.retail.e_commerce.Exception.RoleNotPresentException;
import com.retail.e_commerce.Exception.UserAlreadyPresentException;
import com.retail.e_commerce.Exception.UserCredentialsWrongException;
import com.retail.e_commerce.cache.CacheStore;
import com.retail.e_commerce.jwt.JwtService;
import com.retail.e_commerce.mail_service.MailService;
import com.retail.e_commerce.mail_service.MessageModel;
import com.retail.e_commerce.request_dto.AuthRequest;
import com.retail.e_commerce.request_dto.OtpRequest;
import com.retail.e_commerce.request_dto.UserRequest;
import com.retail.e_commerce.response_dto.AuthResponse;
import com.retail.e_commerce.response_dto.UserResponse;
import com.retail.e_commerce.service.AuthService;
import com.retail.e_commerce.userRepo.UserRepo;
import com.retail.e_commerce.util.ResponseStructure;
import com.retail.e_commerce.util.SimpleResponseStructure;

import jakarta.mail.MessagingException;
import lombok.NoArgsConstructor;

@Service
public class AuthServiceImpl implements AuthService {

	private UserRepo userRepo;
	private PasswordEncoder encoder;
	private CacheStore<String> otpCache;
	private CacheStore<User> userCache;
	private ResponseStructure<AuthResponse> authStructure;
	private ResponseStructure<UserResponse> structure;
	private SimpleResponseStructure simpleResponse;
	private AuthenticationManager authenticationManager;
	private JwtService jwtservice;
	private MailService mailService;
	@Value("${myapp.jwt.refresh.expiration}")
	private int refreshExpiration;
	@Value("${myapp.jwt.access.expiration}")
	private int accessExpiration;
	
	public AuthServiceImpl(UserRepo userRepo, PasswordEncoder encoder, CacheStore<String> otpCache,
			CacheStore<User> userCache, ResponseStructure<AuthResponse> authStructure,
			ResponseStructure<UserResponse> structure, SimpleResponseStructure simpleResponse,
			AuthenticationManager authenticationManager, JwtService jwtservice, MailService mailService) {
		super();
		this.userRepo = userRepo;
		this.encoder = encoder;
		this.otpCache = otpCache;
		this.userCache = userCache;
		this.authStructure = authStructure;
		this.structure = structure;
		this.simpleResponse = simpleResponse;
		this.authenticationManager = authenticationManager;
		this.jwtservice = jwtservice;
		this.mailService = mailService;
	}

	@Override
	public ResponseEntity<SimpleResponseStructure> registerUser(UserRequest userRequest) {
		if (userRepo.existsByEmail(userRequest.getEmail()))
			throw new UserAlreadyPresentException("user already present");
		User user = mapToChildEntity(userRequest);
		String otp = generateOTP();

		otpCache.add(user.getEmail(), otp);
		userCache.add(user.getEmail(), user);
		System.err.println(otp);
		// send mail otp

		try {
			sendOTP(user, otp);
		} catch (MessagingException e) {

			e.printStackTrace();
		}

		// return user data
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(simpleResponse.setStatus(HttpStatus.ACCEPTED.value())
				.setMessage("Verify OTP sent through mail to complete registration | " + "OTP expires in 1 minute"));
	}

	private void sendOTP(User user, String otp) throws MessagingException {
		MessageModel model = MessageModel.builder().to(user.getEmail()).subject("verify your otp").text("<p>Hi,<br>"
				+ "Thanks for your interest in E-commerce,"
				+ "Please verify your email ID using the OTP given below</p>." + "<br>" + "<h1>" + otp + "</h1>"
				+ "<br>" + "<p> If this wasn't you, please ignore this email." + "<br>" + "With best regards, <br>"
				+ "<h3>E-commerce Team</h3>"
				+ "<img height=100px width=200px src= https://seeklogo.com/images/F/flipkart-logo-C9E637A758-seeklogo.com.png>")
				.build();

		mailService.SendMailMessage(model);

	}

	public ResponseEntity<ResponseStructure<UserResponse>> verifyOTP(OtpRequest otpRequest) {
		if (otpCache.get(otpRequest.getEmail()) == null)
			throw new OtpExpiredException("Failed to verify OTP");
		if (!otpCache.get(otpRequest.getEmail()).equals(otpRequest.getOtp()))
			throw new OtpInvalidException("otp is invalid");
		User user = userCache.get(otpRequest.getEmail());
		if (user == null)
			throw new RegistrationSessionExpiredException("Failed to verify OTP ");
		user.setEmailVerfied(true);

		return ResponseEntity.status(HttpStatus.CREATED).body(structure.setBody(mapToUserResponse(userRepo.save(user)))
				.setMessage("otp verfication sucessfully").setStatus(HttpStatus.CREATED.value()));

	}

	public UserResponse mapToUserResponse(User user) {

		return UserResponse.builder().userId(user.getUserId()).userName(user.getUsername())
				.displayName(user.getDisplayName()).email(user.getEmail()).userRole(user.getUserRole())
				.isEmailVerfied(user.isEmailVerfied()).build();
	}

	public AuthResponse mapToAuthResponse(User user) {
		return AuthResponse.builder().userId(user.getUserId()).username(user.getUsername()).userRole(user.getUserRole())
				.isAuthenticated(true).accessTokenExpiry(LocalDateTime.now().plusSeconds(accessExpiration))
				.refreshTokenExpiry(LocalDateTime.now().plusSeconds(refreshExpiration)).build();

	}
//
//	private User mappedToUserRequestToUsers(User user, UserRequest userRequest) {
//
//		return user.builder().username(userRequest.getName()).email(userRequest.getEmail())
//				.password(encoder.encode(userRequest.getPassword())).build();
//
//	}

	private <T extends User> T mapToChildEntity(UserRequest userRequest) {
		User user = null;
		// Check if userRole is not null before proceeding
//	    System.out.println(userRequest.getUserRole());
		if (userRequest.getUserRole().name() != null) {
			switch (userRequest.getUserRole()) {
			case SELLER -> {
				user = new Seller();
				break;
			}
			case CUSTOMER -> {
				user = new Customer();
				break;
			}
			default -> throw new RoleNotPresentException("Failed to process the request");
			}
			if (user != null) {
				user.setUsername(userRequest.getEmail().split("@")[0]);
				user.setEmail(userRequest.getEmail());
				user.setPassword(encoder.encode(userRequest.getPassword()));
				user.setUserRole(userRequest.getUserRole());
			}
		} else {
			throw new NullPointerException("User role cannot be null");
		}
		return (T) user;
	}

	private String generateOTP() {
		return String.valueOf(new Random().nextInt(100000, 999999));
	}

	@Override
	public ResponseEntity<ResponseStructure<AuthResponse>> login(AuthRequest authRequest) {
		  String username = authRequest.getUserName();
		   
		 username = authRequest.getUserName().split("@gmail.com")[0];
		
		System.out.println(username + ", " + authRequest.getPassword());

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(username, authRequest.getPassword()));
		if (! authentication.isAuthenticated())
			throw new UserCredentialsWrongException("user creditinals not found");

		SecurityContextHolder.getContext().setAuthentication(authentication);

		HttpHeaders headers = new HttpHeaders();
		return userRepo.findByUsername(username).map(user -> {

			generateAccessToken(user, headers);
			generateRefreshToken(user, headers);

			return ResponseEntity.ok().headers(headers).body(authStructure.setStatus(HttpStatus.OK.value())
					.setMessage("Authentication Sucessfully").setBody(mapToAuthResponse(user)));
		}).get();

	}

	private void generateRefreshToken(User user, HttpHeaders headers) {
		String token = jwtservice.generateRefreshToken(user.getUsername(), user.getUserRole().name());
		headers.add(HttpHeaders.SET_COOKIE, ConfigureCookie("rt", token, refreshExpiration));

	}

	private String ConfigureCookie(String name, String value, long maxAge) {
		return ResponseCookie.from(name, value).domain("localhost").path("/").httpOnly(true).secure(false)
				.maxAge(Duration.ofMillis(maxAge)).sameSite("Lax").build().toString();

	}

	private void generateAccessToken(User user, HttpHeaders headers) {
		// TODO Auto-generated method stub
		String token = jwtservice.generateAccessToken(user.getUsername(), user.getUserRole().name());
		headers.add(HttpHeaders.SET_COOKIE, ConfigureCookie("at", token, accessExpiration));

	}

}
