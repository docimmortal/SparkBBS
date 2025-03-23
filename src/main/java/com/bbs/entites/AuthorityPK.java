package com.bbs.entites;

import java.io.Serializable;
import java.util.Objects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor @Getter
public class AuthorityPK implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	
	private String authority;
	
	@Override
	 public boolean equals(Object o) {
	  if (this == o) return true;
	  if (!(o instanceof AuthorityPK authorityPK)) return false;
	        return username.equals(authorityPK.username) &&
	        		authority.equals(authority);
	 }

	 @Override
	 public int hashCode() {
	  return Objects.hash(username, authority);
	 }
}
