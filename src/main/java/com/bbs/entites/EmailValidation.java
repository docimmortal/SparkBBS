package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table (name="Email_Validations")
@Getter @Setter @NoArgsConstructor 
public class EmailValidation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column
	private String email;
	
	@Column(name="code_key")
	private String codeKey;
	
	@Column(name="date_sent")
	private LocalDateTime dateSent;
	
	@Column
	private boolean validated;
}
