package com.sboot.security.jwt;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.google.common.base.Strings;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

public class JwtTokenVerifierFilter extends OncePerRequestFilter{

	private JwtConfig jwtConfig;
	private SecretKey secretKey;
	
	
	public JwtTokenVerifierFilter(JwtConfig jwtConfig, SecretKey secretKey) {
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String reqToken=request.getHeader(jwtConfig.getAuthorizationHeader());
		if(Strings.isNullOrEmpty(reqToken) || !reqToken.startsWith(jwtConfig.getTokenPrefix())) {
			doFilterInternal(request, response, filterChain);
			return ;
		}
		
		String tokenwithoutPrefix=reqToken.replace(jwtConfig.getTokenPrefix(), "");
		try {
		        Jws<Claims> claimsJws=  Jwts.parser()
		          .setSigningKey(secretKey)
		          .parseClaimsJws(tokenwithoutPrefix);
		        
		        Claims body=claimsJws.getBody();
		        String username=body.getSubject();
		        
		 List<Map<String, String>> authorities= (List<Map<String,String>>)   body.get("authorities");
		           
		 Set<SimpleGrantedAuthority> filterauthorities=authorities.stream()
		            .map(m->new SimpleGrantedAuthority(m.get("authority"))).collect(Collectors.toSet());
		            
		 Authentication authentication=new UsernamePasswordAuthenticationToken(username,null,filterauthorities);
		 
		SecurityContextHolder.getContext().setAuthentication(authentication);
		          
		}catch(JwtException jwte) {
			throw new IllegalArgumentException(String.format("token %s can't be trusted..", tokenwithoutPrefix));
		}
		
		filterChain.doFilter(request, response);
	}

}
