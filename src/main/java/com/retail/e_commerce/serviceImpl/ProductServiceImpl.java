package com.retail.e_commerce.serviceImpl;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retail.e_commerce.Entity.Product;
import com.retail.e_commerce.request_dto.ProductRequest;
import com.retail.e_commerce.response_dto.ProductResponse;
import com.retail.e_commerce.service.ProductService;
import com.retail.e_commerce.util.ResponseStructure;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
public class ProductServiceImpl  implements ProductService{

	

	@Override
	public ResponseEntity<ResponseStructure<ProductResponse>> addproduct(ProductRequest productRequest) {
		// TODO Auto-generated method stub
		return null;
	}

	private ProductResponse mapToProductResponse(Product product) {
		
		return ProductResponse.builder().productId(product.getProductId()).productName(product.getProductName())
		.productDescription(product.getProductDescription()).productPrice(product.getProductPrice())
		.productQuantity(product.getProductQuantity()).build();
	}
	
	
	 private Product maptoProduct(Product product , ProductRequest productRequest) {
		  product.setProductName(productRequest.getProductName());
		  product.setProductPrice(productRequest.getProductPrice());
		  product.setProductPrice(productRequest.getProductPrice());
		  product.setProductDescription(productRequest.getProductDescription());
		  product.setProductQuantity(productRequest.getProductQuantity());
		return product;
		 
	 }

}
