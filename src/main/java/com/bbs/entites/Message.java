package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import org.hibernate.annotations.Nationalized;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
	private BBSUserDetails bbsUserDetails;
	
	@ManyToOne
	private MessageForum messageForum;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "youtube_video_id")
	private YoutubeVideo youtubeVideo;
	
	//@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="message")
	//private List<Reply> replies;
	
	//@OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="message")
	//private List<Reaction> reactions;
	
	public Message(String title, String message, BBSUserDetails bbsUserDetails, MessageForum messageForum, YoutubeVideo youtubeVideo) {
		this.title = title;
		this.message = message;
		this.bbsUserDetails = bbsUserDetails;
		this.messageForum = messageForum;
		this.timestamp = LocalDateTime.now();
		this.messageForum=messageForum;
		this.youtubeVideo=youtubeVideo;
	}
	
	public Message(String title, String message, LocalDateTime timestamp, BBSUserDetails bbsUserDetails, MessageForum messageForum, YoutubeVideo youtubeVideo) {
		this.title = title;
		this.message = message;
		this.bbsUserDetails = bbsUserDetails;
		this.messageForum = messageForum;
		this.timestamp = timestamp;
		this.messageForum=messageForum;
		this.youtubeVideo=youtubeVideo;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", message=" + message + "]";
	}

}
