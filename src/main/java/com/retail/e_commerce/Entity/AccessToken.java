package com.retail.e_commerce.Entity;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
public class AccessToken {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        
	    private int tokenId;
	    private String token;
	    private LocalDateTime exipration;
	    private boolean isBlocked;
	    
	    @ManyToOne
	    private User user;
	    
	    
}
