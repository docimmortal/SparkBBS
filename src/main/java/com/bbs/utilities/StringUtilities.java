package com.bbs.utilities;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtilities {

	public static byte[] toByteArray(String string) {
		return string.getBytes(Charset.forName("UTF-16"));
	}
	
	public static String fromByteArray(byte[] barray) {
		Charset charset = StandardCharsets.UTF_16;
		return new String(barray, charset);
	}
}
