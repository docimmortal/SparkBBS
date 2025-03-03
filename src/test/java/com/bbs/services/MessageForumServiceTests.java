package com.bbs.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.bbs.entites.Message;
import com.bbs.entites.MessageForum;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class MessageForumServiceTests {

	@Autowired
	private MessageForumService service;
	
	@Test
	public void testFindAll() {
		List<MessageForum> forums = service.findAll();
		assertEquals(4, forums.size());
		
		// Forum 1, has 2 messages
		MessageForum forum1 = forums.get(0);
		assertNotNull(forum1.getName());
		assertEquals("General", forum1.getName());
		assertNotNull(forum1.getDescription());
		assertEquals("Catch-all everyday chat.", forum1.getDescription());
		List<Message> messageList1 = forum1.getMessages();
		assertNotNull(messageList1);
		assertEquals(3,messageList1.size());
		Message message = messageList1.get(0);
		assertNotNull(message);
		assertNotNull(message.getTitle());
		assertNotNull(message.getMessage());
		assertNotNull(message.getUserDetails());
		
		// Forum 2, has 1 message
		MessageForum forum2 = forums.get(1);
		assertNotNull(forum2.getName());
		assertEquals("Get Creative!", forum2.getName());
		assertNotNull(forum2.getDescription());
		assertEquals("Share your art!", forum2.getDescription());
		List<Message> messageList2 = forum2.getMessages();
		assertNotNull(messageList2);
		assertEquals(0,messageList2.size());
	}
}
