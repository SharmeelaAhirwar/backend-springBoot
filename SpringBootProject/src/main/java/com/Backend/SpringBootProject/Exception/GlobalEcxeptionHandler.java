package com.Backend.SpringBootProject.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.Backend.SpringBootProject.Playloads.ApiResponse;

@RestControllerAdvice
public class GlobalEcxeptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
	}
}
