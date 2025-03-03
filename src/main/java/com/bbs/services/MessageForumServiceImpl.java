package com.bbs.services;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.entites.MessageForum;
import com.bbs.repos.MessageForumRepository;

@Service
public class MessageForumServiceImpl implements MessageForumService {

	@Autowired
	private MessageForumRepository repo;
	
	@Override
	public MessageForum save(MessageForum forum) {
		return repo.save(forum);
	}

	@Override
	public List<MessageForum> findAll() {
		return repo.findAll();
	}

	@Override
	public Optional<MessageForum> findById(BigInteger id) {
		return repo.findById(id);
	}

	@Override
	public boolean existsNextMessageForum(BigInteger id) {
		return repo.existsNextMessageForum(id);
	}

	@Override
	public Optional<MessageForum> findNextMessageForum(BigInteger id) {
		return repo.findNextMessageForum(id);
	}

	@Override
	public Optional<MessageForum> findPrevMessageForum(BigInteger id) {
		return repo.findPrevMessageForum(id);
	}
}
