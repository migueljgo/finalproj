package com.csb.appadvc2122.security;

import javax.xml.bind.DatatypeConverter;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class Hash {
	
	public static String toSHA256(String word) {
		String sha256hex = Hashing.sha256()
				  .hashString(word, StandardCharsets.UTF_8)
				  .toString();
        
        return sha256hex;
	}

	public static boolean matches(String password, String passwordHash) {
        return toSHA256(password).equals(passwordHash);
	}
}
