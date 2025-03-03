package com.bbs.repos;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbs.entites.MessageForum;

@Repository
public interface MessageForumRepository extends JpaRepository<MessageForum,BigInteger> {

	@Query("select count(*)>0 from MessageForum mf WHERE mf.id > :id")
	public boolean existsNextMessageForum(BigInteger id);

	@Query("select f from MessageForum f where f.id > :id ORDER BY f.id ASC LIMIT 1")
	public Optional<MessageForum> findNextMessageForum(BigInteger id);

	@Query("select f from MessageForum f where f.id < :id ORDER BY f.id DESC LIMIT 1")
	public Optional<MessageForum> findPrevMessageForum(BigInteger id);
}
