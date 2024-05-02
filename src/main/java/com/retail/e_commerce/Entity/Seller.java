package com.retail.e_commerce.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Seller extends User {
     @OneToOne
	private Address address;

}
