package com.bbs.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.bbs.entites.UserDetails;
import com.bbs.enums.ReactionType;
import com.bbs.entites.Message;
import com.bbs.entites.MessageForum;
import com.bbs.entites.Reaction;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageServiceTest {

	@Autowired
	private MessageService service;
	
	@Autowired
	private DetailsService dService;
	
	@Autowired
	private MessageForumService mfService;
	
	private UserDetails author;
	private MessageForum forum;
	
	@BeforeEach
	public void before() {
		author = new UserDetails("amy","Amy","Jones","a.j@email.com");
		author=dService.save(author);
		forum = new MessageForum("Boring","Boring stuff");
		forum = mfService.save(forum);
	}
	
	/*
	@Test
	@Transactional
	public void getMessageWithReaction() {
		Optional<Message> optional=service.findById(BigInteger.valueOf(1));
		assertTrue(optional.isPresent());
		Message message = optional.get();
		assertEquals(BigInteger.valueOf(1), message.getId());
		List<Reaction> reactions = message.getReactions();
		Reaction react = new Reaction();
		react.setId(BigInteger.valueOf(1));
		react.setUserDetails(message.getUserDetails());
		react.setReactionType(ReactionType.FUNNY);
		reactions.add(react);
		System.out.println(reactions.size());
		
		service.save(message);
		
		optional=service.findById(BigInteger.valueOf(1));
		message = optional.get();
		reactions = message.getReactions();
		assertEquals(1,reactions.size());
		assertEquals(ReactionType.FUNNY, reactions.iterator().next().getReactionType());
	}
	*/
	
	@Test
	@Transactional
	public void testSave() {
		Message message = new Message("The Title","This is a test", author, forum);
		message = service.save(message);
		assertNotNull(message.getId());
		System.out.println("MID: "+message.getId());
		MessageForum forum = message.getMessageForum();
		assertNotNull(forum.getId());
		System.out.println("MFID: "+forum.getId());
		UserDetails details = message.getUserDetails();
		assertNotNull(details.getId());
		System.out.println("DID: "+details.getId());
	}
	
	@Test
	@Transactional
	public void testSaveToAutopopulatedData() {
		Optional<UserDetails> dOptional = dService.findByUsername("Bob");
		assertTrue(dOptional.isPresent());
		UserDetails author1 = dOptional.get();
		
		Optional<Message> optional = service.findById(BigInteger.valueOf(1));
		assertTrue(optional.isPresent());
		
		
		Optional<MessageForum> mfOptional = mfService.findById(BigInteger.valueOf(1));
		assertTrue(mfOptional.isPresent());
		MessageForum forum1 = mfOptional.get();
		Message message = new Message("The Title","This is a test", author1, forum1);
		message = service.save(message);
		assertNotNull(message.getId());
		assertEquals(BigInteger.valueOf(5), message.getId());
		UserDetails details = message.getUserDetails();
		assertEquals(BigInteger.valueOf(1), details.getId());
		MessageForum mForum = message.getMessageForum();
		assertEquals(BigInteger.valueOf(1), mForum.getId());
		
		// Refetch
		Optional<Message> optional3 = service.findById(BigInteger.valueOf(3));
		assertTrue(optional3.isPresent());
		message = optional3.get();
		assertEquals(BigInteger.valueOf(3), message.getId());
		details = message.getUserDetails();
		assertNotNull(details);
		MessageForum forum = message.getMessageForum();
		assertNotNull(forum);
	}

	@Test
	@Transactional
	public void testFindById() {
		Optional<Message> optional = service.findById(BigInteger.valueOf(1));
		assertTrue(optional.isPresent());
		Message message = optional.get();
		assertEquals(BigInteger.valueOf(1), message.getId());
		assertEquals("Hello!",message.getTitle());
		assertEquals("This is a test message.", message.getMessage());
		// I guess there is a database issue because testSaveToAutopopulatedData works.
		UserDetails detail = message.getUserDetails();
		assertNotNull(detail);
		MessageForum forum = message.getMessageForum();
		assertNotNull(forum);
	}
}
