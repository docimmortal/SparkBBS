package com.bbs.repos;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbs.entites.UserDetails;

@Repository
public interface DetailsRepository extends JpaRepository<UserDetails, BigInteger> {

	public Optional<UserDetails> findOptionalByUsername(String username);
}
