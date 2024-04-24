package com.retail.e_commerce.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.e_commerce.Entity.Seller;

public interface SellerRepo extends JpaRepository<Seller, Integer> {

}
