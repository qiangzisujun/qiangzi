package com.lotus.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Test {

	public static void main(String[] args) {
		BCryptPasswordEncoder encode=new BCryptPasswordEncoder();
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String str=encoder.encode("123456");
		String hashPass = encode.encode("123456");  
	    System.out.println(hashPass); 
	    System.out.println(str);
	}
}
