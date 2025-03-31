package com.bbs.entites;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@IdClass( AuthorityPK.class )
@Table(name = "Authorities")
@Getter @Setter @NoArgsConstructor 
public class Authority implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	
	@Id
	private String authority;

	public Authority(String username, String authority) {
		super();
		this.username = username;
		this.authority = authority;
	}
	
}
