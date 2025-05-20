package com.bbs.enums;

public enum ReactionType {
	// These are UTF-16 Encoding
	FUNNY("\uD83D\uDE00"), LOVE("\u2764\uFE0F"), LIKE("\uD83D\uDC4D"), ANGRY("\uD83D\uDE20"), SAD("\uD83D\uDE22"), 
	SURPRISED("\uD83D\uDE32"), NONE("");
	
	private String unicode;
	
	private ReactionType(String unicode) {
		this.unicode=unicode;
	}
	
	public String getUnicode() {
		return unicode;
	}
}
