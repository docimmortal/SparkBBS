package com.bbs.entites;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Users")
@Getter @Setter @NoArgsConstructor 
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    private String username;
	
	@Column
    private String password;
	
	@Column
    private boolean enabled;

    public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
    
}
