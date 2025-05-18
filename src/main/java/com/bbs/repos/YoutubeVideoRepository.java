package com.bbs.repos;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbs.entites.YoutubeVideo;

@Repository
public interface YoutubeVideoRepository extends JpaRepository<YoutubeVideo, BigInteger>{

}
