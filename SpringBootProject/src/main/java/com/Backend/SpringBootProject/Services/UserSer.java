package com.Backend.SpringBootProject.Services;

import java.util.List;

import com.Backend.SpringBootProject.Playloads.UserDTO;

public interface UserSer {
	
	 UserDTO creatUser(UserDTO user);
	 UserDTO updateUser(UserDTO user,Integer id);
	 UserDTO getUserById(Integer id);
	 List<UserDTO> getAllUser();
	 void deleteUserById(Integer id);

}
