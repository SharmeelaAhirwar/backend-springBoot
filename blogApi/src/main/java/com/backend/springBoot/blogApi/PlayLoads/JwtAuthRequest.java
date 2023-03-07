package com.backend.springBoot.blogApi.PlayLoads;

import lombok.Data;

@Data
public class JwtAuthRequest {
	private String username;
	private String password;

}
