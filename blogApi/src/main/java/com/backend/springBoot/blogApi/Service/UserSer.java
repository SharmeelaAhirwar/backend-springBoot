package com.backend.springBoot.blogApi.Service;

import java.util.List;

import com.backend.springBoot.blogApi.PlayLoads.UserDTO;



public interface UserSer {
	
	UserDTO registerNewUser(UserDTO userDto);
	UserDTO creatUser(UserDTO user);
	 UserDTO updateUser(UserDTO user,Integer id);
	 UserDTO getUserById(Integer id);
	 List<UserDTO> getAllUser();
	 void deleteUserById(Integer id);
}
