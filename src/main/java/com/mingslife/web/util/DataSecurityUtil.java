package com.mingslife.web.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DataSecurityUtil {
	public static String encodeBCrypt(String data) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();  
		String hashedPassword = passwordEncoder.encode(data);
		return hashedPassword;
	}
	
	public static boolean matchBCrypt(String encodedPassword, String rawPassword) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.matches(rawPassword, encodedPassword);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String encodedPassword = encodeBCrypt("123456");
			System.out.println(encodedPassword + "\t" + matchBCrypt(encodedPassword, "123456"));
		}
	}
}
