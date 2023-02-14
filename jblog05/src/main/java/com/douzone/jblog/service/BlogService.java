package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	public void addBlog(String id) {
		blogRepository.insert(id);
	}

	public BlogVo getBlog(String id) {
		return blogRepository.findBlog(id);
	}

	public void update(String id, String url, String title) {
		blogRepository.updateImage(id, url, title);
	}
	
	public List<String> getBlogList(){
		return blogRepository.findAll();
	}
	
}
