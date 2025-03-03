package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Messages")
@Getter @Setter @NoArgsConstructor 
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column
	private String title;
	
	@Column
	@Nationalized
	private String message;
	
	@Column
	private LocalDateTime timestamp;
	
	@ManyToOne
	private UserDetails userDetails;
	
	@ManyToOne
	private MessageForum messageForum;
	
	//@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="message")
	//private List<Reply> replies;
	
	//@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="message")
	//private List<Reaction> reactions;
	
	public Message(String title, String message, UserDetails userDetails, MessageForum messageForum) {
		this.title = title;
		this.message = message;
		this.userDetails = userDetails;
		this.messageForum = messageForum;
		this.timestamp = LocalDateTime.now();
		this.userDetails=userDetails;
		this.messageForum=messageForum;
	}
	
	public Message(String title, String message, LocalDateTime timestamp, UserDetails userDetails, MessageForum messageForum) {
		this.title = title;
		this.message = message;
		this.userDetails = userDetails;
		this.messageForum = messageForum;
		this.timestamp = timestamp;
		this.userDetails=userDetails;
		this.messageForum=messageForum;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", message=" + message + "]";
	}

}
