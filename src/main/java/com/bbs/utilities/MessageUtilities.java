package com.bbs.utilities;

public class MessageUtilities {

	public static String cleanMessage(String message) {
		if (message == null) {
			message="";
		}
		message = message.replace("<", "&lt;").replace(">", "&gt;");
		return message;
	}
}
