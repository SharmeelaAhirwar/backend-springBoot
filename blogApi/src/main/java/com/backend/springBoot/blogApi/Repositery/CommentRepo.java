package com.backend.springBoot.blogApi.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springBoot.blogApi.Entity.Comments;

public interface CommentRepo extends JpaRepository<Comments, Integer> {

}
