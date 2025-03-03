package com.bbs.services;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.entites.Message;
import com.bbs.repos.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository repo;
	
	@Override
	public Optional<Message> findById(BigInteger id) {
		return repo.findById(id);
	}

	@Override
	public Message save(Message message) {
		return repo.save(message);
	}

	@Override
	public Optional<Message> findNextInMessageForum(BigInteger id, BigInteger forumId) {
		return repo.findNextInMessageForum(id, forumId);
	}
	
	@Override
	public Optional<Message> findPrevInMessageForum(BigInteger id, BigInteger forumId) {
		return repo.findPrevInMessageForum(id, forumId);
	}


	@Override
	public boolean existsNextInMessageForum(BigInteger id, BigInteger forumId) {
		return repo.existsNextInMessageForum(id, forumId);
	}

	@Override
	public Optional<Message> getLastMessageInMessageForum(BigInteger forumId) {
		return repo.getLastMessageInMessageForum(forumId);
	}

}
