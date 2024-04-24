package com.retail.e_commerce.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;


import com.retail.e_commerce.Entity.RefreshToken;

public interface  RefreshRepo  extends JpaRepository<RefreshToken, Integer>{

	boolean existsByTokenAndIsBlocked(String refreshToken, boolean b);

}
