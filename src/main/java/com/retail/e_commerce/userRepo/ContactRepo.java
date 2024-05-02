package com.retail.e_commerce.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.e_commerce.Entity.Contact;

public interface ContactRepo  extends JpaRepository<Contact, Integer>{
	

}
