package com.lotus.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MyPasswordEncoder implements PasswordEncoder {

	 /**
     * 对密码进行加密并返回
     */
	@Override
	public String encode(CharSequence rawPassword) {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		String encPassword = encoder.encode(rawPassword);
        return encPassword;
	}

	/**
     * 验证密码是否正确
     */
	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		// TODO Auto-generated method stub
		return encode(rawPassword).equals(encodedPassword);
	}

}
