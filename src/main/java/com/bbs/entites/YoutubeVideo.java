package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Youtube_Videos")
@Getter @Setter @NoArgsConstructor 
public class YoutubeVideo implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="youtube_video_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Column(name="video_embed_endpoint")
	private String endpoint;
	
}
