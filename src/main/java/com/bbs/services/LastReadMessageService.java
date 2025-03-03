package com.bbs.services;

import java.math.BigInteger;
import java.util.List;

public interface LastReadMessageService {

	public BigInteger[] getNextForumWithUnreadMessages(BigInteger userDetailsId, BigInteger forumId);
}
