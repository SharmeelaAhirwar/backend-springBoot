package com.backend.springBoot.blogApi.Repositery;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.springBoot.blogApi.Entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer>{

}
