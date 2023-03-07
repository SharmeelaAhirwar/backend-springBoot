package com.backend.springBoot.blogApi.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.springBoot.blogApi.Config.AppConstant;
import com.backend.springBoot.blogApi.Entity.Role;
import com.backend.springBoot.blogApi.Entity.User;
import com.backend.springBoot.blogApi.Exception.ResourceNotFoundException;
import com.backend.springBoot.blogApi.PlayLoads.UserDTO;
import com.backend.springBoot.blogApi.Repositery.RoleRepo;
import com.backend.springBoot.blogApi.Repositery.UserRepo;





@Service
public class UserSerImpl implements UserSer {
	@Autowired
	 private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordEncoder passwordEN;
	
	@Autowired
	private RoleRepo roleRepo;
	


	@Override
	public UserDTO creatUser(UserDTO userDto) {
		User user=this.DtoToUser(userDto);
		 User savedUser=this.userRepo.save(user);
		 System.out.println(savedUser);
		return userToDto(savedUser);
	}

	@Override
	public UserDTO updateUser(UserDTO userDto, Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());
		user.setPassword(userDto.getPassword());
		User updateUser=this.userRepo.save(user);
		 UserDTO userDTO=this.userToDto(updateUser);
		 return userDTO;
	}

	@Override
	public UserDTO getUserById(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
		return this.userToDto(user);
	}

	@Override
	public List<UserDTO> getAllUser() {
		List<User>users=this.userRepo.findAll();
		List<UserDTO>dtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return dtos;
	}

	@Override
	public void deleteUserById(Integer id) {
		User user=this.userRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("user","id",id));
		this.userRepo.delete(user);
		
	}
//	userDto to user
	private User DtoToUser(UserDTO userDto) {
//		User user=new User();
//		user.setId(userDto.getId());
//		user.setName(userDto.getName());
//		user.setEmail(userDto.getEmail());
//		user.setPassword(userDto.getPassword());
//		user.setAbout(userDto.getAbout());
//		return user;
		User user= this.modelMapper.map(userDto, User.class);
		return user;
		
	}
//	user to userDto
	private UserDTO userToDto(User user) {
//		UserDTO userDto=new UserDTO();
//		userDto.setId(user.getId());
//		userDto.setName(user.getName());
//		userDto.setEmail(user.getEmail());
//		userDto.setPassword(user.getPassword());
//		userDto.setAbout(user.getAbout());
//		return userDto;
		UserDTO userDto=modelMapper.map(user, UserDTO.class);
		return userDto;
	}

	@Override
	public UserDTO registerNewUser(UserDTO userDto) {
		User user=this.modelMapper.map(userDto, User.class);
		
//		encoded password
		user.setPassword(this.passwordEN.encode(user.getPassword()));
		
		//roles
		Role role=this.roleRepo.findById(AppConstant.ROLE_USER).get();
		user.getRoles().add(role);
		 User savedUser=this.userRepo.save(user);
		
		return this.modelMapper.map(savedUser, UserDTO.class);
	}

}
