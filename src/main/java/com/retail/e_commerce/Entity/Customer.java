package com.retail.e_commerce.Entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Customer  extends User{
	
	@OneToMany
	private List<Address> addresses;
	
	

}
