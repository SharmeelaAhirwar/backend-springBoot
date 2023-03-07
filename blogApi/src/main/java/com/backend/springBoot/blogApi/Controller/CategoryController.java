package com.backend.springBoot.blogApi.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.springBoot.blogApi.PlayLoads.ApiResponse;
import com.backend.springBoot.blogApi.PlayLoads.CategoryDTO;
import com.backend.springBoot.blogApi.Service.CategorySer;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
	private CategorySer catSer;
	
	
	
	//post
	@PostMapping("/create")
	public ResponseEntity<CategoryDTO>createCategory(@RequestBody CategoryDTO catDto){
		System.out.println("hhhhhhh&&&&&7"+catDto.getCategoryTitle());
		CategoryDTO categoryDt=this.catSer.creat(catDto);
		
		return new ResponseEntity<CategoryDTO>(categoryDt,HttpStatus.CREATED);
		
	}
	
	//put
	@PutMapping("/update/{id}")
	public ResponseEntity<CategoryDTO>updateCategory(@RequestBody CategoryDTO catDto,@PathVariable Integer id){
		CategoryDTO cat=this.catSer.update(catDto, id);
		return new ResponseEntity<CategoryDTO>(cat,HttpStatus.ACCEPTED);
	}
	
	//delete
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<ApiResponse>deleteCategory(@PathVariable Integer id){
			this.catSer.delete(id);
			return new ResponseEntity<>(new ApiResponse("Category deleted succesfully !!",true),HttpStatus.OK);
		}
		
		//get
		@GetMapping("/getAllCategory")
		public ResponseEntity<List<CategoryDTO>>updateCategory(){
			List<CategoryDTO>allCat=this.catSer.getAllCategory();
			return new ResponseEntity<List<CategoryDTO>>(allCat,HttpStatus.OK);
		}
		
		@GetMapping("/getByID/{id}")
		public ResponseEntity<CategoryDTO>getCategoryByID(@PathVariable Integer id){
			 CategoryDTO cat=this.catSer.getById(id);
			return new ResponseEntity<CategoryDTO>(cat,HttpStatus.OK);
		}
	

}
