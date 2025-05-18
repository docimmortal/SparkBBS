package com.bbs.services;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbs.entites.YoutubeVideo;
import com.bbs.repos.YoutubeVideoRepository;

@Service
public class YoutubeVideoServiceImpl implements YoutubeVideoService {

	@Autowired
	private YoutubeVideoRepository repo;
	
	@Override
	public Optional<YoutubeVideo> findById(BigInteger id) {
		return repo.findById(id);
	}

	@Override
	public YoutubeVideo save(YoutubeVideo video) {
		return repo.save(video);
	}

}
