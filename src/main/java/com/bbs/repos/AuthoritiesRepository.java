package com.bbs.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bbs.entites.Authority;
import com.bbs.entites.AuthorityPK;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authority, AuthorityPK> {

}
