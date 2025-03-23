package com.bbs.services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.repos.LastReadMessageRepository;

@Service
public class LastReadMessageServiceImpl implements LastReadMessageService {

	@Autowired
	private LastReadMessageRepository repo;
	
	@Override
	public BigInteger[] getNextForumWithUnreadMessages(BigInteger userDetailsId, BigInteger forumId) {
		List<BigInteger[]> results = repo.getNextForumWithUnreadMessages(userDetailsId, forumId);
		if (results.size()==0) return null;
		return results.get(0);
	}

}
