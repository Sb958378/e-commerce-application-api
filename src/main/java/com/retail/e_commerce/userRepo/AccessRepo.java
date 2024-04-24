package com.retail.e_commerce.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.e_commerce.Entity.AccessToken;
;

public interface AccessRepo extends JpaRepository<AccessToken, Integer>{

	boolean existsByTokenAndIsBlocked(String accessToken, boolean b);

}
