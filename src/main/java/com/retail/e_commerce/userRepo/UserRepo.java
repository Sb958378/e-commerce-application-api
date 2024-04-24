package com.retail.e_commerce.userRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.retail.e_commerce.Entity.User;

public interface UserRepo extends JpaRepository<User , Integer>{

	boolean existsByEmail(String email);
	
	Optional<User> findByUsername(String username);
	

}
