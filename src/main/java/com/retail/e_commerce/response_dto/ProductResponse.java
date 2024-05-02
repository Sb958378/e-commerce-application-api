package com.retail.e_commerce.response_dto;



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
public class ProductResponse {
	
	
	  private int productId;
	  private String productName;
	  private String productDescription;
	  private int productPrice;
	  private int productQuantity;
	

}
