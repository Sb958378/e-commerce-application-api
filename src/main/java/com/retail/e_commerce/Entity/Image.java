package com.retail.e_commerce.Entity;

import com.retail.e_commerce.enums.ImageType;


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
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int imageId;
	    private ImageType imageType;

}
