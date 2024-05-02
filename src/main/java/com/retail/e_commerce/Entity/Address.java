package com.retail.e_commerce.Entity;

import java.util.List;

import com.retail.e_commerce.enums.AddressType;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private int addressId;
	   private String streetAddress;
	   private String streetAddressAdditional;
	   private AddressType addressType;
	   private String city;
	   private String state;
	   private int pincode;

	    @OneToMany
	    private List<Contact> contacts;
	   
	   
	   

}
