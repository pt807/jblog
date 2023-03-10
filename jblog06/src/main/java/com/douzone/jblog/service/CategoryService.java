package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVo;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVo> getcategoryList(String id){
		return categoryRepository.findById(id);
	}
	
	public Long getcategoryNo(String id) {
		return categoryRepository.findNo(id);
	}

	public void addCategory(String id, String name) {
		categoryRepository.insertCategory(id, name);
	}

	public List<CategoryVo> getcategoryListAndPostCount(String id) {
		return categoryRepository.findListAndPostCount(id);
	}

	public void removeCategory(Long no) {
		categoryRepository.deleteCategory(no);
	}
	
}
