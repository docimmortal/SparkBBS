package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Message_Forums")
@Getter @Setter @NoArgsConstructor 
public class MessageForum implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column
	private String name;
	
	@Column
	private String description;

	@OneToMany(mappedBy="messageForum",fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true)
	private List<Message> messages;
	
	public MessageForum(String name, String description) {
		this.name = name;
		this.description = description;
	}

	@Override
	public String toString() {
		return "MessageForum [id=" + id + ", name=" + name + ", description=" + description + "]";
	}
	
}
