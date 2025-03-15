package com.bbs.services;

import java.math.BigInteger;
import java.util.Optional;

import com.bbs.entites.Message;

public interface MessageService {

	public Optional<Message> findNextInMessageForum(BigInteger id, BigInteger forumId);
	public Optional<Message> findPrevInMessageForum(BigInteger id, BigInteger forumId);
	public boolean existsNextInMessageForum(BigInteger id, BigInteger forumId);
	public Optional<Message> getLastMessageInMessageForum(BigInteger forumId);
	
	public Optional<Message> findById(BigInteger id);
	public Message save(Message message);
}
