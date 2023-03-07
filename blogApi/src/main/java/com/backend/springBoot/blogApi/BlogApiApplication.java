package com.backend.springBoot.blogApi;


import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.backend.springBoot.blogApi.Config.AppConstant;
import com.backend.springBoot.blogApi.Entity.Role;
import com.backend.springBoot.blogApi.Repositery.RoleRepo;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {

	@Autowired
	private PasswordEncoder password;
	
	@Autowired
	private RoleRepo roleRepo;
	
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.password.encode("shimi"));
		
		try {
			Role role=new Role();
			role.setId(AppConstant.ROLE_ADMIN);
			role.setName("ROLE_ADMIN");
			
			Role role1=new Role();
			role1.setId(AppConstant.ROLE_USER);
			role1.setName("ROLE_USER");
			
			List<Role>roles=List.of(role,role1);
			
			List<Role>rolelist=this.roleRepo.saveAll(roles);
			rolelist.forEach(r->{
				System.out.println(r);
			});
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}


}
