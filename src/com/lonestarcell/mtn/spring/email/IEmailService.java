package com.lonestarcell.mtn.spring.email;

public interface IEmailService {
	boolean sendSimpleMessage( String to, String subject, String text );
}
