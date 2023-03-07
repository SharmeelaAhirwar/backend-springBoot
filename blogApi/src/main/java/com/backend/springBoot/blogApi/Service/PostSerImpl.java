package com.backend.springBoot.blogApi.Service;


import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.backend.springBoot.blogApi.Entity.Category;
import com.backend.springBoot.blogApi.Entity.Post;
import com.backend.springBoot.blogApi.Entity.User;
import com.backend.springBoot.blogApi.Exception.ResourceNotFoundException;
import com.backend.springBoot.blogApi.PlayLoads.PostDTO;
import com.backend.springBoot.blogApi.PlayLoads.PostPagableResponse;
import com.backend.springBoot.blogApi.Repositery.CategoryRepo;
import com.backend.springBoot.blogApi.Repositery.PostRepo;
import com.backend.springBoot.blogApi.Repositery.UserRepo;

@Service
public class PostSerImpl implements PostSer{
	
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo catRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	

	
	@Override
	public PostDTO createPost(PostDTO postDto, Integer userId, Integer categoryId) {
		
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		Category category=this.catRepo.findById(categoryId).orElseThrow(()-> 
		new ResourceNotFoundException("category", "categoryId", categoryId));
		
		Post post=this.modelMapper.map(postDto, Post.class);
		post.setAddedDate(new Date());
		post.setCategory(category);
		post.setUser(user);
		post.setImageName("default.png");
		Post newPost=this.postRepo.save(post);
		
		return this.modelMapper.map(newPost, PostDTO.class);
	}
	
	@Override
	public PostDTO updatePost(PostDTO postDto, Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
		post.setPostContent(postDto.getPostContent());
		post.setPostTitle(postDto.getPostTitle());
		post.setImageName(postDto.getImageName());
		 Post updated=this.postRepo.save(post);
		return this.modelMapper.map(updated, PostDTO.class);
	}

	@Override
	public void deletePost(Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
		this.postRepo.delete(post);
		
	}

	@Override
	public PostDTO getPostById(Integer id) {
		Post post=this.postRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("post", "id", id));
		return this.modelMapper.map(post, PostDTO.class);
	}

	@Override
	public List<PostDTO> getAllPost() {
		List<Post>allPost=this.postRepo.findAll();
		
		List<PostDTO>postDto=allPost.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDTO> getPostByUser(Integer userId) {
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", 
				"userId", userId));
		
		List<Post>posts=this.postRepo.findByUser(user);
		
		List<PostDTO>postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDTO> getPostByCategory(Integer categoryId) {
		Category cat=this.catRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category", 
				"categoryId", categoryId));
		
		List<Post>posts=this.postRepo.findByCategory(cat);
		
		List<PostDTO>postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());
		return postDto;
	}

	@Override
	public List<PostDTO> searchPost(String keyword) {
		List<Post>posts=this.postRepo.findByPostTitleContaining(keyword);
		List<PostDTO>postDto=posts.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());
		return postDto;
	}

	
//	pagging 
	@Override
	public PostPagableResponse getAllPostUsingPagging(Integer pageNumber, Integer pageSize) {
		Pageable p=PageRequest.of(pageNumber, pageSize);
		Page<Post>pagePost=this.postRepo.findAll(p);
		List<Post>postList=pagePost.getContent();
		
		List<PostDTO>postDto=postList.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());
		
		PostPagableResponse postPagableResponse=new PostPagableResponse();
		postPagableResponse.setContent(postDto);
		postPagableResponse.setPageNumber(pagePost.getNumber());
		postPagableResponse.setPageSize(pagePost.getSize());
		postPagableResponse.setTotalElement(pagePost.getNumberOfElements());
		postPagableResponse.setTotalPages(pagePost.getTotalPages());
		postPagableResponse.setLastPage(pagePost.isLast());
		return postPagableResponse;
	}

	
//	pagging and sorting
	@Override
	public PostPagableResponse getAllPostUsingPaggingSorting(Integer pageNumber, Integer pageSize, String sortBy) {
		Pageable p=PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		Page<Post>pagePost=this.postRepo.findAll(p);
		List<Post>postList=pagePost.getContent();
		
		List<PostDTO>postDto=postList.stream().map((post)->this.modelMapper.map(post, PostDTO.class)).
				collect(Collectors.toList());
		
		PostPagableResponse postPagableResponse=new PostPagableResponse();
		postPagableResponse.setContent(postDto);
		postPagableResponse.setPageNumber(pagePost.getNumber());
		postPagableResponse.setPageSize(pagePost.getSize());
		postPagableResponse.setTotalElement(pagePost.getNumberOfElements());
		postPagableResponse.setTotalPages(pagePost.getTotalPages());
		postPagableResponse.setLastPage(pagePost.isLast());
		return postPagableResponse;
	}

	

}
