package com.bbs.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bbs.entites.BBSUserDetails;
import com.bbs.repos.BBSUserDetailsRepository;
import com.bbs.utilities.ImageUtilities;

@Service
public class BBSUserDetailsServiceImpl implements BBSUserDetailsService {

	@Autowired
	private BBSUserDetailsRepository repo;

	@Override
	public Optional<BBSUserDetails> findByUsername(String username) {
		return repo.findOptionalByUsername(username);
	}
	
	@Override
	public Optional<BBSUserDetails> findById(BigInteger id) {
		return repo.findById(id);
	}

	@Override
	public BBSUserDetails save(BBSUserDetails details) {
		
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
	public List<BBSUserDetails> findAll() {
		return repo.findAll();
	}

	@Override
	public String findUsernameByDoorId(String doorId) {
		String username = repo.findUsernameByDoorId(doorId);
		return username != null? username:"";
	}

	@Override
    public BBSUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       	Optional<BBSUserDetails> optional=repo.findOptionalByUsername(username);
        if (optional.isEmpty()) {
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("user not found");
        }
        
        return optional.get();
    }

}
