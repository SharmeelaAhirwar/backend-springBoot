package com.backend.springBoot.blogApi.Repositery;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springBoot.blogApi.Entity.Category;
import com.backend.springBoot.blogApi.Entity.Post;
import com.backend.springBoot.blogApi.Entity.User;

public interface PostRepo extends JpaRepository<Post, Integer> {
	List<Post>findByUser(User user);
	List<Post>findByCategory(Category category);
	List<Post>findByPostTitleContaining(String keyword);

}
