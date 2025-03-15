package com.bbs.repos;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbs.entites.User;

@Repository
public interface UsersRepository extends JpaRepository<User, BigInteger> {

	User findByUsername(String username);
}
