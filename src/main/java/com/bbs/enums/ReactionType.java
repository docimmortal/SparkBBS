package com.bbs.enums;

public enum ReactionType {
	FUNNY("\uD83D\uDE00"), LIKE("\u10000\u1FFFF"), ANGRY("\u1F600\u1F64F"), SAD("\u1F600\u1F64F"), 
	LOVE("\u2764\uFE0F"), SURPRISED("\u1F600\u1F64F"), NONE("");
	
	private String unicode;
	
	private ReactionType(String unicode) {
		this.unicode=unicode;
	}
	
	public String getUnicode() {
		return unicode;
	}
}
