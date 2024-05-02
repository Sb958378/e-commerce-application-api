package com.retail.e_commerce.Entity;

import java.util.List;

import com.retail.e_commerce.enums.AvailabilityStatus;
import com.retail.e_commerce.enums.UserRole;
//import com.retail.e_commerce.response_dto.ImageResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int productId;
	  private String productName;
	  private String productDescription;
	  private int productPrice;
	  private int productQuantity;
	  private AvailabilityStatus availabilityStatus;
//	  private List<ImageResponse>imageResponse;
	  
	  
	  
	  

}
