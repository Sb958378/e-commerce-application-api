package com.retail.e_commerce.util;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;

import com.retail.e_commerce.Entity.AccessToken;
import com.retail.e_commerce.Entity.RefreshToken;
import com.retail.e_commerce.userRepo.AccessRepo;
import com.retail.e_commerce.userRepo.RefreshRepo;

public class Schedule {

	private AccessRepo accessRepo;
	private RefreshRepo refreshRepo;
	 
	   @Scheduled(fixedDelay = 60 * 60 * 1000l)
	    public void deleteAllExpiredAccessTokens() {
	        List<AccessToken> as = accessRepo.findByExiprationLessThanEqual(LocalDateTime.now());
	        accessRepo.deleteAll(as);
           List<RefreshToken> rs = refreshRepo.findByExpirationLessThanEqual(LocalDateTime.now());

	    }

	   
}
