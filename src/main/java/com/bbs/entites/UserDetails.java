package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="User_Details")
@Getter @Setter @NoArgsConstructor 
public class UserDetails implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column
	private String username;
	
	@Column(name="door_id")
	private String doorId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column
	private String email;
	
	@Lob
	@Column
	private byte[] photo;
	
	@Column(name="last_login")
	private LocalDateTime lastLogin;
	
	@OneToMany(mappedBy="userDetails",fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Message> messages;

	public UserDetails(String username, String playerId, String firstName, String lastName, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.doorId=playerId;
		this.lastLogin = LocalDateTime.now();
	}
}
