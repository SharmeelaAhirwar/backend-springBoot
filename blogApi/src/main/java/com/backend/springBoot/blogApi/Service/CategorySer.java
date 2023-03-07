package com.backend.springBoot.blogApi.Service;

import java.util.List;

import com.backend.springBoot.blogApi.PlayLoads.CategoryDTO;

public interface CategorySer {
	public CategoryDTO creat(CategoryDTO catDTO);
	public CategoryDTO update(CategoryDTO catDto,Integer id);
	public CategoryDTO getById(Integer id);
	public List<CategoryDTO> getAllCategory();
	public void delete(Integer id);
	

}
