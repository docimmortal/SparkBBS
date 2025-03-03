package com.bbs.repos;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbs.entites.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, BigInteger> {

	@Query("select m from Message m where m.messageForum.id = :forumId AND m.id > :id ORDER BY m.id ASC LIMIT 1")
	public Optional<Message> findNextInMessageForum(BigInteger id, BigInteger forumId);

	@Query("select m from Message m where m.messageForum.id = :forumId AND m.id < :id ORDER BY m.id DESC LIMIT 1")
	public Optional<Message> findPrevInMessageForum(BigInteger id, BigInteger forumId);

	@Query("select count(*)>0 from Message m WHERE m.messageForum.id = :forumId AND m.id > :id")
	public boolean existsNextInMessageForum(BigInteger id, BigInteger forumId);

	@Query("select m from Message m where m.messageForum.id = :forumId ORDER BY m.id DESC LIMIT 1")
	public Optional<Message> getLastMessageInMessageForum(BigInteger forumId);
}
