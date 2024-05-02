package com.retail.e_commerce.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.e_commerce.request_dto.ProductRequest;
import com.retail.e_commerce.response_dto.ProductResponse;
import com.retail.e_commerce.service.ProductService;
import com.retail.e_commerce.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor

@CrossOrigin(origins = "http://localhost:5173/", allowCredentials = "true")
public class ProductController {
	
	      private ProductService productService;
	      
	      public ResponseEntity<ResponseStructure<ProductResponse>> addProduct(@RequestBody ProductRequest  productRequest){
			return productService.addproduct(productRequest);
	    	  
	      }
	      
	      
	   
	
	

}
