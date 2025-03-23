package com.bbs.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbs.entites.User;

@Repository
public interface UsersRepository extends JpaRepository<User, String> {

	User findByUsername(String username);
}
