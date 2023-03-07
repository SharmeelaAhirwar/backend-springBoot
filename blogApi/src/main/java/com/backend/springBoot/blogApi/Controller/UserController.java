package com.backend.springBoot.blogApi.Controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springBoot.blogApi.PlayLoads.ApiResponse;
import com.backend.springBoot.blogApi.PlayLoads.UserDTO;
import com.backend.springBoot.blogApi.Service.UserSer;



@RestController
@RequestMapping("/api/users")

public class UserController {
	
	@Autowired
	private UserSer userService;

	
	//post
	@PostMapping("/creat")
	public ResponseEntity<UserDTO>createdUser(  @Valid@RequestBody UserDTO dto){
		UserDTO userDto=this.userService.creatUser(dto);
		return new ResponseEntity<>(userDto,HttpStatus.CREATED);	
	}
	//put
	@PutMapping("/update/{id}")
	public ResponseEntity<UserDTO>updateUsers(@Valid @RequestBody UserDTO dto,@PathVariable("id") Integer id){
		 UserDTO userDto=this.userService.updateUser(dto, id);
		 return ResponseEntity.ok(userDto);
		
	}
	
	//admin can Delete
	//delete
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse>deleteUsers(@PathVariable Integer id){
		this.userService.deleteUserById(id);
//		return ResponseEntity.ok(Map.of("Message","User Deleted Successfully:"));
//		return  new ResponseEntity(Map.of("Message","User Deleted Successfully:"),HttpStatus.OK);
		return  new ResponseEntity(new ApiResponse("User Deleted Successfully:",true),HttpStatus.OK);
		
	}
	@GetMapping("/allUser")
	public ResponseEntity<List<UserDTO>>getAllUsers(){
		List<UserDTO>dtos=this.userService.getAllUser();
		return ResponseEntity.ok(dtos);
	}
	@GetMapping("/getById/{id}")
	public ResponseEntity<UserDTO>getByIdUsers(@PathVariable Integer id){
		UserDTO dtos=this.userService.getUserById(id);
		return ResponseEntity.ok(dtos);
	}
	
	

}
