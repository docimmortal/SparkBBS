package com.bbs.repos;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
public class LastReadMessagesTests {

	@Autowired
	private LastReadMessageRepository repo;
	
	@Test
	public void testQuery() {
		List<BigInteger[]> results = repo.getNextForumWithUnreadMessages(BigInteger.ONE, BigInteger.ZERO);
		assertNotNull(results);
		assertNotEquals(0, results.size());
		for (BigInteger[] biArray: results) {
			for (BigInteger bi:biArray) {
				System.out.print(bi+" ");
			}
			System.out.println();
		}
		
	}
}
