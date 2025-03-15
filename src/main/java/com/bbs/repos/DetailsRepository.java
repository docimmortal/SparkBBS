package com.bbs.repos;

import java.math.BigInteger;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbs.entites.BBSUserDetails;

@Repository
public interface DetailsRepository extends JpaRepository<BBSUserDetails, BigInteger> {

	public Optional<BBSUserDetails> findOptionalByUsername(String username);
	
	@Query("SELECT d.username from BBSUserDetails d WHERE  d.doorId = :doorId ")
	public String findUsernameByDoorId(String doorId);
}
