package com.bbs.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.bbs.pojos.BBSConfig;

@Configuration
public class BBSConfigConfig {

	@Bean
	protected BBSConfig bbsConfig() {
		BBSConfig bc = new BBSConfig();
		return bc;
	}
}
