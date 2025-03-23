package com.bbs.entites;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Last_Read_Messages")
@Getter @Setter @NoArgsConstructor 
public class LastReadMessage {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
	
	@ManyToOne
	private BBSUserDetails userDetails;
	
	@ManyToOne
	private MessageForum messageForum;
	
	@ManyToOne
	private Message message;
}
