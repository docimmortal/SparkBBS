package com.bbs.entites;

import java.io.Serializable;
import java.math.BigInteger;

import com.bbs.enums.ReactionType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="Reactions")
@Getter @Setter @NoArgsConstructor 
public class Reaction  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private BigInteger id;
	
	@Enumerated(EnumType.STRING)
	@Column(name="reaction_type")
	private ReactionType reactionType;
	
	@ManyToOne
	private BBSUserDetails userDetails;
	
	@ManyToOne
	private Message message;
	
}
