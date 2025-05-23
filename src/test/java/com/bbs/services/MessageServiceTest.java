package com.bbs.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigInteger;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.bbs.entites.BBSUserDetails;
import com.bbs.entites.Message;
import com.bbs.entites.MessageForum;
import com.bbs.entites.YoutubeVideo;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageServiceTest {

	@Autowired
	private MessageService service;
	
	@Autowired
	private BBSUserDetailsService dService;
	
	@Autowired
	private MessageForumService mfService;
	
	private BBSUserDetails author;
	private MessageForum forum;
	
	@BeforeEach
	public void before() {
		author = new BBSUserDetails("amy","123","Amy","Jones","a.j@email.com");
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
		Message message = new Message("The Title","This is a test", author, forum, null);
		message = service.save(message);
		assertNotNull(message.getId());
		System.out.println("MID: "+message.getId());
		MessageForum forum = message.getMessageForum();
		assertNotNull(forum.getId());
		System.out.println("MFID: "+forum.getId());
		BBSUserDetails details = message.getBbsUserDetails();
		assertNotNull(details.getId());
		System.out.println("DID: "+details.getId());
	}
	
	@Test
	@Transactional
	public void testSaveToAutopopulatedData() {
		Optional<BBSUserDetails> dOptional = dService.findByUsername("Bob");
		assertTrue(dOptional.isPresent());
		BBSUserDetails author1 = dOptional.get();
		
		Optional<Message> optional = service.findById(BigInteger.valueOf(1));
		assertTrue(optional.isPresent());
		
		
		Optional<MessageForum> mfOptional = mfService.findById(BigInteger.valueOf(1));
		assertTrue(mfOptional.isPresent());
		MessageForum forum1 = mfOptional.get();
		Message message = new Message("The Title","This is a test", author1, forum1, null);
		message = service.save(message);
		assertNotNull(message.getId());
		assertEquals(BigInteger.valueOf(5), message.getId());
		BBSUserDetails details = message.getBbsUserDetails();
		assertEquals(BigInteger.valueOf(1), details.getId());
		MessageForum mForum = message.getMessageForum();
		assertEquals(BigInteger.valueOf(1), mForum.getId());
		
		// Refetch
		Optional<Message> optional3 = service.findById(BigInteger.valueOf(3));
		assertTrue(optional3.isPresent());
		message = optional3.get();
		assertEquals(BigInteger.valueOf(3), message.getId());
		details = message.getBbsUserDetails();
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
		BBSUserDetails detail = message.getBbsUserDetails();
		assertNotNull(detail);
		MessageForum forum = message.getMessageForum();
		assertNotNull(forum);
	}
	
	@Test
	@Transactional
	public void testVideoFound() {
		Optional<Message> optional = service.findById(BigInteger.valueOf(3));
		assertTrue(optional.isPresent());
		Message m = optional.get();
		assertNotNull(m.getYoutubeVideo());
		YoutubeVideo video = m.getYoutubeVideo();
		assertEquals("a_iij12svL0",video.getEndpoint());
	}
}
