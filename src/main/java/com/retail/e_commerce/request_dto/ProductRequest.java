package com.retail.e_commerce.request_dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ProductRequest {
	
	private String productName;
	  private String productDescription;
	  private int productPrice;
	  private int productQuantity;
	

}
