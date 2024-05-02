package com.retail.e_commerce.service;

import org.springframework.http.ResponseEntity;

import com.retail.e_commerce.request_dto.ProductRequest;
import com.retail.e_commerce.response_dto.ProductResponse;
import com.retail.e_commerce.util.ResponseStructure;

public interface ProductService {
	
	 
	public ResponseEntity<ResponseStructure<ProductResponse>> addproduct(ProductRequest  productRequest);
	
	

}
