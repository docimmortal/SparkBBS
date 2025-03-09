package com.bbs.configs;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.thymeleaf.spring6.ISpringWebFluxTemplateEngine;
import org.thymeleaf.spring6.SpringWebFluxTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	private ApplicationContext ctx;
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(
				new AntPathRequestMatcher("/h2-console/**"));
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
		http.csrf(csrf -> csrf.ignoringRequestMatchers( "/userDetailsForDoor"));
		http.authorizeHttpRequests(authorize -> {	
			authorize.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll();
			authorize.requestMatchers("/userDetailsForDoor","/test").permitAll();
			authorize.requestMatchers("/actuator/**","/h2-console/**").permitAll();
			authorize.requestMatchers("/images/**").permitAll();
			authorize.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN");
			authorize.anyRequest().authenticated();
		})
        .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**"))
        .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()));
		http.httpBasic(withDefaults());
		http.logout(logout -> logout.permitAll());
		http.formLogin(form -> form.loginPage("/loginPage").defaultSuccessUrl("/hello", true).permitAll());
		http.headers(headers -> headers.disable());
		return http.build();
	}
    
    // The rest is for Unicode
    @Bean
    public SpringResourceTemplateResolver thymeleafTemplateResolver() {

        final SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(this.ctx);
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCacheable(false);
        resolver.setCheckExistence(false);
        return resolver;

    }

    @Bean
    public ISpringWebFluxTemplateEngine thymeleafTemplateEngine() {
        // We override here the SpringTemplateEngine instance that would otherwise be
        // instantiated by
        // Spring Boot because we want to apply the SpringWebFlux-specific context
        // factory, link builder...
        final SpringWebFluxTemplateEngine templateEngine = new SpringWebFluxTemplateEngine();
        templateEngine.setTemplateResolver(thymeleafTemplateResolver());
        return templateEngine;
    }
    
    @Bean
    public ThymeleafViewResolver  thymeleafViewResolver(){
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(thymeleafTemplateEngine());
        viewResolver.setCharacterEncoding("UTF-8");
        return viewResolver;
    }
    
}
