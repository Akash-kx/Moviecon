package com.moviecon.RegisterLogin.SpringSecurityConfiguration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private ManuelUsernamePasswordAuthentication AuthenticationManuel;
	
	@Bean
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		
		http.cors().configurationSource(new CorsConfigurationSource() {

			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				
				CorsConfiguration config = new CorsConfiguration();
				config.setAllowCredentials(true);
				config.setMaxAge(3600L);
				config.setAllowedMethods(Collections.singletonList("*"));
				config.addAllowedOrigin("http://localhost:4200/");
				config.setAllowedHeaders(Arrays.asList("Authorization"));
				return config;
			}
		})
		.and().csrf().disable()
		.authorizeHttpRequests().requestMatchers("/AddLoginInformation", "/UserRegistrationInformation").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().formLogin();
		
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
		
		AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
		authenticationManagerBuilder.authenticationProvider(AuthenticationManuel);
		return authenticationManagerBuilder.build();
	}

}