package com.moviecon.InventoryProduct.InventoryConfiguration;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.moviecon.InventoryProduct.JWTtoken.JwtTokenValidation;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfiguration {
	
	@Autowired
	private JwtTokenValidation jwtToken;
	
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
		.authorizeHttpRequests().requestMatchers("/ShowAllMovieInInventory", "/ShowSearchedMovie/**").permitAll()
		.anyRequest().authenticated()
		.and().httpBasic()
		.and().formLogin();
		
		http.addFilterBefore(jwtToken, UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {      
         return new MethodValidationPostProcessor();
    }
}
