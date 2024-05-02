package com.retail.e_commerce.serviceImpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retail.e_commerce.Entity.Address;
import com.retail.e_commerce.Entity.Contact;
import com.retail.e_commerce.Entity.Customer;
import com.retail.e_commerce.Entity.Seller;

import com.retail.e_commerce.Exception.AddressLimitExceededException;
import com.retail.e_commerce.Exception.AlreadyHaveAddressException;
import com.retail.e_commerce.Exception.ContactExcceedException;
import com.retail.e_commerce.Exception.UserIsNotLogInException;
import com.retail.e_commerce.Exception.UserNotFoundException;
import com.retail.e_commerce.enums.UserRole;
import com.retail.e_commerce.jwt.JwtService;
import com.retail.e_commerce.request_dto.AddressRequest;
import com.retail.e_commerce.request_dto.ContactRequest;
import com.retail.e_commerce.response_dto.AddressResponse;
import com.retail.e_commerce.response_dto.ContactResponse;
import com.retail.e_commerce.service.AddressService;
import com.retail.e_commerce.userRepo.AddressRepo;
import com.retail.e_commerce.userRepo.ContactRepo;
import com.retail.e_commerce.userRepo.CustomerRepo;
import com.retail.e_commerce.userRepo.SellerRepo;
import com.retail.e_commerce.userRepo.UserRepo;
import com.retail.e_commerce.util.ResponseStructure;

import lombok.AllArgsConstructor;




@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService{

	private AddressRepo addressRepo;
	 private JwtService jwtservice;
	 private UserRepo  userRepo;
	 private SellerRepo sellerRepo;
	 private CustomerRepo customerRepo;
	 private ContactRepo contactrepo;
	 private ResponseStructure<List<AddressResponse>> responseStr;

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> addAddress(AddressRequest addressRequest ,String accesstoken) {
		
		String username = jwtservice.getusername(accesstoken);
		System.out.println(username);
		Optional<Address> optional = userRepo.findByUsername(username).map(user->{
			
		Address address =null;	
		
		if(user.getUserRole().equals(UserRole.SELLER)) {
		if(((Seller)user).getAddress()!=null) 
				throw new AlreadyHaveAddressException("seller is already have a address to add");
				address =addressRepo.save(mapToAddress(new Address(), addressRequest));
				 ((Seller)user).setAddress(address);
				 sellerRepo.save((Seller)user);
						
			
		}
		
		else if(user.getUserRole().equals(UserRole.CUSTOMER)) {
			if(((Customer)user).getAddresses().size()>=5) 
				 throw new AddressLimitExceededException("A customer can have up to 5 addresses.");
				 address= addressRepo.save(mapToAddress(new Address(), addressRequest));
				 ((Customer)user).getAddresses().add(address);
			
				 customerRepo.save((Customer)user);
				 
				 
			
			
			
			
		}
		return address;
			
		});
		return ResponseEntity.ok(new ResponseStructure<AddressResponse>().setBody(mapToAddressResponse(optional.get()))
				.setMessage("address add").setStatus(HttpStatus.OK.value()));
	}

	private AddressResponse mapToAddressResponse(Address address) {
	

		AddressResponse addressResponse = AddressResponse.builder().addressId(address.getAddressId()).state(address.getState()).city(address.getCity())
				.streetAddress(address.getStreetAddress()).streetAddressAdditional(address.getStreetAddressAdditional())
				.pincode(address.getPincode())
				.addressType(address.getAddressType())
				.build();
		List<Contact>contacts = address.getContacts();
		if(contacts!=null) 
			addressResponse.setContacts(contacts.stream().map(this::mapContactsToContactResponses).collect(Collectors.toList()));
		return addressResponse;
	
	}

	private ContactResponse mapContactsToContactResponses(Contact savecontact) {
		
		return ContactResponse.builder().contactId(savecontact.getContactId()).contactName(savecontact.getContactName())
				.email(savecontact.getEmail()).phoneNumber(savecontact.getPhoneNumber()).priority(savecontact.getPriority()).build();
				
		
	}

	
	
	 private Contact mapToContact(Contact contact , ContactRequest contactRequest) {
		 
		 contact.setContactName(contactRequest.getContactName());
		 contact.setPhoneNumber(contactRequest.getContactNumber());
		 contact.setPriority(contactRequest.getPriority());
		 
		return contact;
		 
	 }
	private Address mapToAddress(Address address, AddressRequest addressRequest) {
		// TODO Auto-generated method stub
		 address.setStreetAddress(addressRequest.getStreetAddress());
		 address.setStreetAddressAdditional(addressRequest.getStreetAddressAdditional());
		 address.setState(addressRequest.getState());
		 address.setCity(addressRequest.getCity());
		 address.setPincode(addressRequest.getPincode());
		 address.setAddressType(addressRequest.getAddressType());

		 return address;
		       
	}

	
	@Override
	public ResponseEntity<ResponseStructure<List<AddressResponse>>> findAddressbyUser(String accessToken,
	        String refreshToken) {
	    if (accessToken == null || refreshToken == null) 
	        throw new UserIsNotLogInException("User is not logged in");
	    
	    String username = jwtservice.getusername(refreshToken);
	  
	    List<AddressResponse> addresses = userRepo.findByUsername(username).map(user -> {
	        List<AddressResponse> addressList = new ArrayList<>();

	        if (user instanceof Seller) { 
	            Seller seller = (Seller) user;
	            addressList.add(mapToAddressResponse(seller.getAddress()));
	        }

	        if (user instanceof Customer) {
	            Customer customer = (Customer) user;
	            for (Address add : customer.getAddresses()) {
	                addressList.add(mapToAddressResponse(add));
	            }
	        }
	        return addressList;
	    }).orElseThrow(() -> new UserNotFoundException("User not found"));

	    // Construct ResponseEntity outside the map function
//	    ResponseStructure<List<AddressResponse>> responseStr = new ResponseStructure<>();
	    return ResponseEntity.ok().body(responseStr.setBody(addresses)
	        .setStatus(HttpStatus.OK.value())
	        .setMessage("List of addresses for user: " + username));
	}

	@Override
	public ResponseEntity<ResponseStructure<AddressResponse>> updateAddress(int addressId, AddressRequest addressRequest) {
	    return addressRepo.findById(addressId)
	            .map(address -> ResponseEntity.ok().body(
	                    new ResponseStructure<AddressResponse>()
	                            .setStatus(HttpStatus.OK.value())
	                            .setMessage("Address updated successfully")
	                            .setBody(mapToAddressResponse(addressRepo.save(mapToAddress(address, addressRequest))))
	            )).get();
	}
	
	public ResponseEntity<ResponseStructure<ContactResponse>> addContact(int addressId, ContactRequest contactRequest) {
	    Contact contact = mapToContact(new Contact(), contactRequest);
        return addressRepo.findById(addressId).map(address->{
        	if(address.getContacts().size()>=2)
        		throw new ContactExcceedException("User cannot add more than 2 contacts.");
        	Contact saveContact = contactrepo.save(contact);
        	address.getContacts().add(saveContact);
        	addressRepo.save(address);
        return ResponseEntity.ok().body(new ResponseStructure<ContactResponse>().setStatus(HttpStatus.OK.value())
        			.setMessage("contact add sucessfully").setBody(mapContactsToContactResponses(saveContact)));
        }).get();
	}


	public ResponseEntity<ResponseStructure<ContactResponse>> updateContact(int contactId, ContactRequest contactRequest) {
	    return contactrepo.findById(contactId)
	            .map(existingContact -> {
	                
	                Contact updatedContact = mapToContact(existingContact, contactRequest);
	            
	                Contact savedContact = contactrepo.save(updatedContact);
	             

	           ContactResponse contactResponse = mapContactsToContactResponses(savedContact);
	                return ResponseEntity.ok().body(
	                        new ResponseStructure<ContactResponse>()
	                                .setStatus(HttpStatus.OK.value())
	                                .setMessage("Updated contact successfully")
	                                .setBody(contactResponse)
	                );
	            }).get();
	           
	}
           
	}


