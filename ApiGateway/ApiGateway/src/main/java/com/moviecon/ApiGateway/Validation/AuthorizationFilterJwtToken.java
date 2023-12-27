package com.moviecon.ApiGateway.Validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.moviecon.ApiGateway.JwtTokenValidation.TokenValidationLogic;

import reactor.core.publisher.Mono;

@Component
public class AuthorizationFilterJwtToken extends AbstractGatewayFilterFactory<AuthorizationFilterJwtToken.Config> {
	
	private String jwtToken;
	
	@Autowired
	private TokenValidationLogic TokenLogic;
	
	public static class Config {
		
	}
	
	public AuthorizationFilterJwtToken() {
		
		super(Config.class);
	}

	@Override
	public GatewayFilter apply(Config config) {
		
		return (ServerWebExchange, Chain) -> {
			
			ServerHttpRequest ClientHeaderRequest = ServerWebExchange.getRequest();
			
			if( !ClientHeaderRequest.getHeaders().containsKey("Authorization") ) {

				return onAuhtorizationToken(ServerWebExchange, "No Token Present With Name Authorization" , HttpStatus.UNAUTHORIZED);
			}
			
			String authorizationToken = ClientHeaderRequest.getHeaders().getFirst("Authorization");
			
			if( authorizationToken != null && authorizationToken.startsWith("Bearer ") ) {

				this.jwtToken = authorizationToken.substring(7);
			}
			
			else {
				
				return onNullToken(ServerWebExchange, "No Token Value Present" , HttpStatus.UNAUTHORIZED);
			}
			
			if( TokenLogic.tokenDateExpiredOrNot(this.jwtToken) && TokenLogic.usernameInToken(this.jwtToken) == null ) {

				return onUsernameDateExpireToken(ServerWebExchange, "Username Is Not Present, Token Is Expired" , HttpStatus.UNAUTHORIZED);
			}
			
			return Chain.filter(ServerWebExchange);
		};
	}

	private Mono<Void> onUsernameDateExpireToken(ServerWebExchange serverWebExchange, String ErrorMessage, HttpStatus httpStatus) {
		
		System.out.println(ErrorMessage);
		
		ServerHttpResponse ClientHeaderResponse = serverWebExchange.getResponse();
		ClientHeaderResponse.setStatusCode(httpStatus);
		return ClientHeaderResponse.setComplete();
	}

	private Mono<Void> onNullToken(ServerWebExchange serverWebExchange, String ErrorMessage, HttpStatus httpStatus) {
		
		System.out.println(ErrorMessage);
		
		ServerHttpResponse ClientHeaderResponse = serverWebExchange.getResponse();
		ClientHeaderResponse.setStatusCode(httpStatus);
		return ClientHeaderResponse.setComplete();
	}

	private Mono<Void> onAuhtorizationToken(ServerWebExchange serverWebExchange, String ErrorMessage, HttpStatus httpStatus) {
		
		System.out.println(ErrorMessage);
		
		ServerHttpResponse ClientHeaderResponse = serverWebExchange.getResponse();
		ClientHeaderResponse.setStatusCode(httpStatus);
		return ClientHeaderResponse.setComplete();
	}

	
}
