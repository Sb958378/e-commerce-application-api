package com.retail.e_commerce.userRepo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.e_commerce.Entity.AccessToken;




public interface AccessRepo extends JpaRepository<AccessToken, Integer>{

	boolean existsByTokenAndIsBlocked(String accessToken, boolean b);

	Optional<AccessToken> findByToken(String accessToken);

	List<AccessToken> findByExiprationLessThanEqual(LocalDateTime now);



	
}
