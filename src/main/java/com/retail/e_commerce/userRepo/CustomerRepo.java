package com.retail.e_commerce.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.e_commerce.Entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
