package com.bbs.services;

import java.math.BigInteger;
import java.util.Optional;

import com.bbs.entites.YoutubeVideo;

public interface YoutubeVideoService {
	
	public Optional<YoutubeVideo> findById(BigInteger id);
	public YoutubeVideo save(YoutubeVideo video);
}
