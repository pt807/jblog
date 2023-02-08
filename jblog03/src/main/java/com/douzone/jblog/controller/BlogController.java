package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;

@RequestMapping("/jblog")
@Controller
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileuploadService fileuploadService;
	
	@RequestMapping({"/{id}", "/{id}/{categoryNo}", "/{id}/{categoryNo}/{postNo}"})
	public String index(
			@PathVariable("id") String id, 
			@PathVariable("categoryNo") Optional<Long> categoryNo,
			@PathVariable("postNo") Optional<Long> postNo,
			Model model) {
		Long categoryNum = 0L;
		Long postNum = 1L;
		
		if(postNo.isPresent()) {
			categoryNum = categoryNo.get();
			postNum = postNo.get();
		}else if(categoryNo.isPresent()){
			categoryNum = categoryNo.get();
		}else {
			// 모든값이 있을때
			categoryNum = categoryService.getcategoryNo(id);
		}
		
		PostVo post = postService.getPost(categoryNum, postNum);
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("post", post);
		model.addAttribute("blogVo", blogVo);
		
		List<PostVo> postList = postService.getPosts(categoryNum);
		List<CategoryVo> categoryList = categoryService.getcategoryList(id);
		model.addAttribute("postList", postList);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("categoryNo", categoryNum);
		
		return"blog/main";
	}
	
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return"blog/admin-basic";
	}
	
	@RequestMapping(value = "/{id}/admin/basic", method = RequestMethod.POST)
	public String adminBasic(
			@PathVariable("id") String id,
			@RequestParam("title") String title, 
			@RequestParam("logo-file") MultipartFile file,
			Model model) {
		
		String url = fileuploadService.restore(file);
		blogService.update(id, url, title);
		
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		
		model.addAttribute("url", url);
		
		return"blog/admin-basic";
	}

}
