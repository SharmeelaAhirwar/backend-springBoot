package com.backend.springBoot.blogApi.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springBoot.blogApi.PlayLoads.JwtAuthRequest;
import com.backend.springBoot.blogApi.PlayLoads.JwtAuthResponse;
import com.backend.springBoot.blogApi.PlayLoads.UserDTO;
import com.backend.springBoot.blogApi.Service.UserSer;
import com.backend.springBoot.blogApi.security.JwtTokenHelper;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	
	@Autowired
	private JwtTokenHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDS;
	
	@Autowired
	private UserSer userS;
	
	@Autowired
	private AuthenticationManager authM;
	
	@PostMapping("login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAR){
		this.authenticate(jwtAR.getUsername(),jwtAR.getPassword());
		UserDetails userdetail=this.userDS.loadUserByUsername(jwtAR.getUsername());
		 String token=this.jwtHelper.generateToken(userdetail);
		 JwtAuthResponse jwtARes=new JwtAuthResponse();
		 jwtARes.setToken(token);
		 return new ResponseEntity<JwtAuthResponse>(jwtARes,HttpStatus.OK);
	}

	private void authenticate(String username, String password) {
		UsernamePasswordAuthenticationToken usernamePAT=new UsernamePasswordAuthenticationToken(username, password);
		
		this.authM.authenticate(usernamePAT);
		
	}
	@PostMapping("/register")
	public ResponseEntity<UserDTO>regiterNewUsers(@RequestBody UserDTO userDto){
		 UserDTO saved= this.userS.registerNewUser(userDto);
		 return new ResponseEntity<UserDTO>(saved,HttpStatus.CREATED);
		
	}
	
	
	
	
	
	

}
