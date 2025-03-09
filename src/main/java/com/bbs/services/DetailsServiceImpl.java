package com.bbs.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.entites.UserDetails;
import com.bbs.repos.DetailsRepository;
import com.bbs.utilities.ImageUtilities;

@Service
public class DetailsServiceImpl implements DetailsService {

	@Autowired
	private DetailsRepository repo;
	

	@Override
	public Optional<UserDetails> findByUsername(String username) {
		return repo.findOptionalByUsername(username);
	}
	
	@Override
	public Optional<UserDetails> findById(BigInteger id) {
		return repo.findById(id);
	}

	@Override
	public UserDetails save(UserDetails details) {
		
		if (details.getPhoto() == null) {
			try {
				BufferedImage image = ImageUtilities.getImageFromFile("none.jpg",true);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				byte[] bytes = baos.toByteArray();
				details.setPhoto(bytes); // local file in project
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return repo.save(details);
	}

	@Override
	public List<UserDetails> findAll() {
		return repo.findAll();
	}

	@Override
	public String findUsernameByDoorId(String doorId) {
		String username = repo.findUsernameByDoorId(doorId);
		return username != null? username:"";
	}

}
