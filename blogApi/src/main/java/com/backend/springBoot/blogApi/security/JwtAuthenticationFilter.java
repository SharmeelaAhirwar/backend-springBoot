package com.backend.springBoot.blogApi.security;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private UserDetailsService userDS;
	@Autowired
	private JwtTokenHelper jwtHelper;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		
		//get token
		
		String requestToken=request.getHeader("Authorization");
		
		//Bearer 34456ghhf
		System.out.println(requestToken);
		
		String username=null;
		String token=null;
		if (requestToken!=null && requestToken.startsWith("Bearer")) {
			
			token=requestToken.substring(7);
			try {
				username=this.jwtHelper.getUsernameFromToken(token);
				
			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt");
				
			}
			catch (ExpiredJwtException e) {
				System.out.println("token has expired");
				
			}
			catch (MalformedJwtException e) {
				System.out.println("invalid jwt");
				
			}
			
		} else {
              
			System.out.println("jwt token does not start with bearer");
		}
		
		//once we get token
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			
			UserDetails userDetails = this.userDS.loadUserByUsername(username);
			if(this.jwtHelper.validateToken(token, userDetails)) {
				
				//shi chl rha authentication krna h
				UsernamePasswordAuthenticationToken auth=new UsernamePasswordAuthenticationToken(userDetails, 
						null,userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			else
				System.out.println("invalid jwt");
		}
		else {
			 System.out.println("username is null or contexxt is not null");
			 
		}
		filterChain.doFilter(request, response);
	}

}
