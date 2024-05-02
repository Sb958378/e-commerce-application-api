package com.retail.e_commerce.userRepo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retail.e_commerce.Entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
