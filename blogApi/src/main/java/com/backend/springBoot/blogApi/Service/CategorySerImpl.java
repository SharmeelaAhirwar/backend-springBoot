package com.backend.springBoot.blogApi.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.springBoot.blogApi.Entity.Category;
import com.backend.springBoot.blogApi.Entity.User;
import com.backend.springBoot.blogApi.Exception.ResourceNotFoundException;
import com.backend.springBoot.blogApi.PlayLoads.CategoryDTO;
import com.backend.springBoot.blogApi.PlayLoads.UserDTO;
import com.backend.springBoot.blogApi.Repositery.CategoryRepo;

@Service

public class CategorySerImpl implements CategorySer {
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CategoryDTO creat(CategoryDTO catDTO) {
		// TODO Auto-generated method stub
		System.out.println("frombackend ++======"+catDTO.getCategoryTitle());
		Category cat=this.modelMapper.map(catDTO, Category.class);
		Category added=this.categoryRepo.save(cat);
		return this.modelMapper.map(added, CategoryDTO.class);
	}

	@Override
	public CategoryDTO update(CategoryDTO catDto, Integer id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","CategoryId",id));
		
		category.setCategoryDes(catDto.getCategoryDes());
		category.setCategoryTitle(catDto.getCategoryTitle());

		 Category updatedCat= this.categoryRepo.save(category);
		
		return this.modelMapper.map(updatedCat, CategoryDTO.class);
	}

	@Override
	public CategoryDTO getById(Integer id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","CategoryId",id));
		
		return this.modelMapper.map(category, CategoryDTO.class);
	}

	@Override
	public List<CategoryDTO> getAllCategory() {
		List<Category>cats=this.categoryRepo.findAll();
		List<CategoryDTO>catDtos=cats.stream().map((cat)->this.modelMapper.map(cat,CategoryDTO.class)).collect(Collectors.toList());
		return catDtos;
	}

	@Override
	public void delete(Integer id) {
		Category category=this.categoryRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("category","CategoryId",id));
		this.categoryRepo.delete(category);
		
	}

}
