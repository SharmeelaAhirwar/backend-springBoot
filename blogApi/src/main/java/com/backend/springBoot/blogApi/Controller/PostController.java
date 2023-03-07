package com.backend.springBoot.blogApi.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backend.springBoot.blogApi.Config.AppConstant;
import com.backend.springBoot.blogApi.PlayLoads.ApiResponse;
import com.backend.springBoot.blogApi.PlayLoads.PostDTO;
import com.backend.springBoot.blogApi.PlayLoads.PostPagableResponse;
import com.backend.springBoot.blogApi.Service.FileSer;
import com.backend.springBoot.blogApi.Service.PostSer;


@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostSer postService;
	
	@Autowired
	FileSer fileSer;
	
	@Value("${project.image}")
	private String path;

	
	//post
	@PostMapping("/user/{userId}/category/{categoryId}/createPost")
	public ResponseEntity<PostDTO>createdPost(@RequestBody PostDTO dto,@PathVariable Integer userId,
			@PathVariable Integer categoryId){
		PostDTO postDto=this.postService.createPost(dto,userId,categoryId);
		return new ResponseEntity<>(postDto,HttpStatus.CREATED);	
	}
	//put
	@PutMapping("/updatePost/{postId}")
	public ResponseEntity<PostDTO>updatePosts(@RequestBody PostDTO dto,@PathVariable Integer postId){
		PostDTO postDto=this.postService.updatePost(dto, postId);
		return new ResponseEntity<>(postDto,HttpStatus.CREATED);	
	}
	
	//delete
	
	@DeleteMapping("/deletePost/{postId}")
	public ResponseEntity<ApiResponse>deletePosts(@PathVariable Integer postId){
		this.postService.deletePost(postId);
		return  new ResponseEntity(new ApiResponse("User Deleted Successfully:",true),HttpStatus.OK);
	}
	
	@GetMapping("/getByUser/{userId}")
	public ResponseEntity<List<PostDTO>>getPostByUser(@PathVariable Integer userId){
		List<PostDTO> postDto=this.postService.getPostByUser(userId);
		return new ResponseEntity<>(postDto,HttpStatus.OK);	
	}

	
	@GetMapping("/getByCategory/{categoryId}")
	public ResponseEntity<List<PostDTO>>getPostByCategory(@PathVariable Integer categoryId){
		List<PostDTO> postDto=this.postService.getPostByCategory(categoryId);
		return new ResponseEntity<>(postDto,HttpStatus.OK);	
	}
	
	@GetMapping("/getAllPost")
	public ResponseEntity<List<PostDTO>>getAllPosts(){
		List<PostDTO> postDto=this.postService.getAllPost();
		return new ResponseEntity<>(postDto,HttpStatus.OK);	
	}
	
	@GetMapping("/getById/{postId}")
	public ResponseEntity<PostDTO>getPostByPostID(@PathVariable Integer postId){
		PostDTO postDto=this.postService.getPostById(postId);
		return new ResponseEntity<>(postDto,HttpStatus.OK);	
	}
	
	@GetMapping("/getPagablePost")
	public ResponseEntity<PostPagableResponse>getAllPostsByPagagble(@RequestParam(value="pageNumber",
	defaultValue = "0",required = false)Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5",
	required = false)Integer pageSize){
		PostPagableResponse postPagableResponse=this.postService.getAllPostUsingPagging(pageNumber, pageSize);
		return new ResponseEntity<>(postPagableResponse,HttpStatus.OK);	
	}
	
//	@GetMapping("/getPagingSortingPost")
//	public ResponseEntity<PostPagableResponse>getAllPostsByPaggingSorting(@RequestParam(value="pageNumber",
//	defaultValue = "0",required = false)Integer pageNumber,@RequestParam(value = "pageSize",defaultValue = "5",
//	required = false)Integer pageSize,@RequestParam(value = "sortBy",defaultValue = "postId",required = false)
//	String sortBy){
//		PostPagableResponse postPagableResponse=this.postService.getAllPostUsingPaggingSorting(pageNumber, pageSize,sortBy);
//		return new ResponseEntity<>(postPagableResponse,HttpStatus.OK);	
//	}
	
	@GetMapping("/getPagingSortingPost")
	public ResponseEntity<PostPagableResponse>getAllPostsByPaggingSorting(@RequestParam(value="pageNumber",
	defaultValue = AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,@RequestParam(value = "pageSize",
	defaultValue = AppConstant.PAGE_SIZE,
	required = false)Integer pageSize,@RequestParam(value = "sortBy",defaultValue = AppConstant.SORT_BY,required = false)
	String sortBy){
		PostPagableResponse postPagableResponse=this.postService.getAllPostUsingPaggingSorting(pageNumber, pageSize,sortBy);
		return new ResponseEntity<>(postPagableResponse,HttpStatus.OK);	
	}
	
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<PostDTO>>searchPosts(@PathVariable String keyword){
		List<PostDTO> postDto=this.postService.searchPost(keyword);
		return new ResponseEntity<>(postDto,HttpStatus.OK);	
	}
	
	//post image upload
	
	@PostMapping("/uploadImage/{postId}")
	public ResponseEntity<PostDTO>uploadImage( @RequestParam("image")MultipartFile file,@PathVariable Integer postId) throws IOException{
		
		PostDTO pstDto=this.postService.getPostById(postId);
		String fileName=this.fileSer.uploadImage(path, file);
		
		pstDto.setImageName(fileName);
		PostDTO updatePost=this.postService.updatePost(pstDto, postId);
		return new ResponseEntity<>(updatePost,HttpStatus.OK);	
	}
	
	//serve image
	@GetMapping( value="/serveImage/images/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downLoadImage(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException {
		InputStream resource=this.fileSer.getResource(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resource,response.getOutputStream());
	}
	
	

}
