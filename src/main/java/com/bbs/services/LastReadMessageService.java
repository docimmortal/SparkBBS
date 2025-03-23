package com.bbs.services;

import java.math.BigInteger;

public interface LastReadMessageService {

	public BigInteger[] getNextForumWithUnreadMessages(BigInteger userDetailsId, BigInteger forumId);
}
