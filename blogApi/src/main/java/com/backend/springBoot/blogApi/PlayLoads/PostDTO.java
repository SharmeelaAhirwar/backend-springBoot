package com.backend.springBoot.blogApi.PlayLoads;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;

import com.backend.springBoot.blogApi.Entity.Comments;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostDTO {
	
	

	
	private Integer postId;
	private String postTitle;
	
	
	private String postContent;
	
	private CategoryDTO category;
	
	private String imageName;
	
	private UserDTO user;
	
	private Date addedDate;
	
	private Set<CommentDTO>comment=new HashSet<CommentDTO>();
	
	

}
