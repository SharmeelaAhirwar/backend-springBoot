package com.Backend.SpringBootProject.Services;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Backend.SpringBootProject.Entity.User;
import com.Backend.SpringBootProject.Exception.ResourceNotFoundException;
import com.Backend.SpringBootProject.Playloads.UserDTO;
import com.Backend.SpringBootProject.repositery.UserRepo;
@Service
public class UserSerImpl implements UserSer {
	@Autowired
	 private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;


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

}
