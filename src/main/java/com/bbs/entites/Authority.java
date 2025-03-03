package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Authorities")
@Getter @Setter @NoArgsConstructor 
public class Authority implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
	
	private String username;
	
	private String authority;

	public Authority(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}
	
}
