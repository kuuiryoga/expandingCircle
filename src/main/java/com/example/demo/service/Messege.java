package com.example.demo.service;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class Messege {

	public static String getErrorMessege(MessageSource messageSource, FormPattern pattern) {
		return messageSource.getMessage(pattern.geterrorKeys().get(0), pattern.getParams(), Locale.JAPAN);
	}

	public static String getErrorMessege(MessageSource messageSource, String key, Object...params) {
		System.out.println(params);
		return messageSource.getMessage(key, params, Locale.JAPAN);
	}
}
