package com.backend.springBoot.blogApi.Service;

import java.util.List;

import com.backend.springBoot.blogApi.Entity.Post;
import com.backend.springBoot.blogApi.PlayLoads.PostDTO;
import com.backend.springBoot.blogApi.PlayLoads.PostPagableResponse;

public interface PostSer {
	
	
	PostDTO createPost(PostDTO postDto,Integer userId,Integer categoryId);
	
	PostDTO updatePost(PostDTO postDto,Integer postId);
	
	void deletePost(Integer id);
	
	PostDTO getPostById(Integer id);
	
	List<PostDTO>getAllPost();
	
	List<PostDTO>getPostByUser(Integer  userId);
	
	List<PostDTO>getPostByCategory(Integer  categoryId);
	
	List<PostDTO>searchPost(String keyword);
	
	//pagable
	
	 PostPagableResponse  getAllPostUsingPagging(Integer pageNumber ,Integer pageSize);
	 
	 PostPagableResponse  getAllPostUsingPaggingSorting(Integer pageNumber ,Integer pageSize,String sortBy);
	
	
	
	
	

}
