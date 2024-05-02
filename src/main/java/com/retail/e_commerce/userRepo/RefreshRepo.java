package com.retail.e_commerce.userRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.retail.e_commerce.Entity.RefreshToken;
import com.retail.e_commerce.Entity.User;

public interface  RefreshRepo  extends JpaRepository<RefreshToken, Integer>{

	boolean existsByTokenAndIsBlocked(String refreshToken, boolean b);

	Optional<RefreshToken> findByToken(String refreshToken);
	boolean existsByIsBlocked(boolean b);

	List<RefreshToken> findByExpirationLessThanEqual(LocalDateTime now);



}
