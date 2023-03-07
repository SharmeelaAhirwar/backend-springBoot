package com.backend.springBoot.blogApi.PlayLoads;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
	
	private Integer id;
	
	@NotEmpty
	@Size(min=4,message="UserName must be min of 4 charecters ")
	private String name;
	@Email(message="email address is not valid")
	private String email;
	@NotEmpty
	private String password;
	@NotEmpty
	private String about;
	
	private Set<RoleDTO>roles=new HashSet<RoleDTO>();
	

}


