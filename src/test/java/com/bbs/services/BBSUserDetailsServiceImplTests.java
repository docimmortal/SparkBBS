package com.bbs.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.DirtiesContext;

import com.bbs.entites.BBSUserDetails;
import com.bbs.utilities.ImageUtilities;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BBSUserDetailsServiceImplTests {

	@Autowired
	private BBSUserDetailsService service;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	public void testfindByUsername() {
		Optional<BBSUserDetails> optional = service.findByUsername("Bob");
		assertTrue(optional.isPresent());
	}
	
	@Test
	public void testSaveNewDetails() {
		BBSUserDetails details = new BBSUserDetails("Amy","123","Amy","Jones","a.j@email.com");
		try {
			BufferedImage image = ImageUtilities.getImageFromFile("none.jpg",true);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			byte[] bytes = baos.toByteArray();
			details.setPhoto(bytes); // local file in project
			details = service.save(details);
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertNotNull(details.getId());
	}
	
	@Test
	public void testPlayerIdEncryption() {
		long now = System.currentTimeMillis();
        Long number = new Random(now).nextLong();
        String username="Joe Cool";
        String firstName="Joe";
        String lastName="Cool";
        String email="joe@king.com";
		String playerId=number.toString()+username.charAt(0)+firstName.charAt(0)+lastName.charAt(0)+email.charAt(0);
		playerId =  passwordEncoder.encode(playerId).substring(8);
		System.out.println(playerId);
	}
	
	@Test
	public void testGetPlayerNameByDoorIdFoundPlayer() {
		String doorId="$2a$10$pR5VxQAnxZzq7HYqJlOBcOWlyuUeUInj3DS/G5EPmyL89q7wZwgga";
		String username=service.findUsernameByDoorId(doorId);
		System.out.println(username);
	}
	
	@Test
	public void testGetPlayerNameByDoorIdPlayerNotFound() {
		String doorId="123";
		String username=service.findUsernameByDoorId(doorId);
		System.out.println(username);
	}
	
	@Test
	public void testFindAll() {
		List<BBSUserDetails> list = service.findAll();
		assertEquals(2, list.size());
	}
}
