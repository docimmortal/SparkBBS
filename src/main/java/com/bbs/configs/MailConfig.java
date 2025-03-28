package com.bbs.configs;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.bbs.pojos.BBSConfig;

@Configuration
public class MailConfig {


	@Autowired
	private BBSConfigConfig bcc;

	@Bean
	protected JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		BBSConfig config = bcc.bbsConfig();
		mailSender.setHost(config.getValue("mail.host"));
		mailSender.setPort(Integer.parseInt(config.getValue("mail.port")));
		mailSender.setUsername(config.getValue("mail.email"));
		mailSender.setPassword(config.getValue("mail.password"));

		Properties props = mailSender.getJavaMailProperties();
		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", config.getValue("mail.smtp.auth"));
		props.put("mail.smtp.starttls.enable", config.getValue("mail.smtp.starttls.enable"));
		String debug = config.getValue("mail.debug");
		if (debug!=null && debug.equalsIgnoreCase("true")) {
			props.put("mail.debug", debug.toLowerCase()); // Enable for debugging
		}
		return mailSender;
	}
}
