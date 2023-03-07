package com.backend.springBoot.blogApi.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileSer {

	String uploadImage(String path,MultipartFile multipartFile)throws IOException;
	
	InputStream getResource(String path,String fileName)throws FileNotFoundException;
}
