package com.bbs.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import com.bbs.entites.UserDetails;
import com.bbs.utilities.ImageUtilities;

import jakarta.transaction.Transactional;

@Transactional
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DetailsServiceImplTests {

	@Autowired
	private DetailsService service;
	
	@Test
	public void testfindByUsername() {
		Optional<UserDetails> optional = service.findByUsername("Bob");
		assertTrue(optional.isPresent());
	}
	
	@Test
	public void testSaveNewDetails() {
		UserDetails details = new UserDetails("Amy","Amy","Jones","a.j@email.com");
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
	public void testFindAll() {
		List<UserDetails> list = service.findAll();
		assertEquals(2, list.size());
	}
}
