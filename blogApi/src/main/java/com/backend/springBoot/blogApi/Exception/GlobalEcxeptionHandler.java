package com.backend.springBoot.blogApi.Exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.springBoot.blogApi.PlayLoads.ApiResponse;



@RestControllerAdvice
public class GlobalEcxeptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse>ResourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse api=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(api,HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String,String>>handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
		Map<String,String>resp=new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fldname=((FieldError)error).getField();
			String msg=error.getDefaultMessage();
			resp.put(fldname, msg);
		});
		return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
	}
}
