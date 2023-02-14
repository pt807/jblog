package com.douzone.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.Auth;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileuploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVo;
import com.douzone.jblog.vo.CategoryVo;
import com.douzone.jblog.vo.PostVo;
import com.douzone.jblog.vo.UserVo;

@RequestMapping("/{id:(?!assets).*}")
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

	@RequestMapping({ "", "/{categoryNo}", "/{categoryNo}/{postNo}" })
	public String index(@PathVariable("id") String id, @PathVariable("categoryNo") Optional<Long> categoryNo,
			@PathVariable("postNo") Optional<Long> postNo, Model model) {
		Long categoryNum = 0L;
		Long postNum = 0L;
		
		List<String> blogList = blogService.getBlogList();
		if(! blogList.contains(id)) {
			return "error/404";
		}
		
		// 다 있을때
		if (postNo.isPresent()) {
			categoryNum = categoryNo.get();
			postNum = postNo.get();
		// categoryNo 까지 있을때
		} else if (categoryNo.isPresent()) {
			categoryNum = categoryNo.get();
			postNum = postService.getMinNo(categoryNum);
		// 없을때
		} else {
			categoryNum = categoryService.getcategoryNo(id);
			postNum = postService.getMinNo(categoryNum);
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

		return "blog/main";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.GET)
	public String adminBasic(@PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		return "blog/admin-basic";
	}

	@Auth
	@RequestMapping(value = "/admin/basic", method = RequestMethod.POST)
	public String adminBasic(@PathVariable("id") String id, @RequestParam("title") String title,
			@RequestParam("logo-file") MultipartFile file, Model model) {

		String url = fileuploadService.restore(file);
		blogService.update(id, url, title);

		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);
		model.addAttribute("url", url);

		return "blog/admin-basic";
	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.GET)
	public String adminCategory(
			@ModelAttribute CategoryVo vo, 
			@PathVariable("id") String id, 
			Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> categoryList = categoryService.getcategoryListAndPostCount(id);
		model.addAttribute("list", categoryList);
		return "blog/admin-category";
	}

	@Auth
	@RequestMapping(value = "/admin/category", method = RequestMethod.POST)
	public String adminCategory(
			@ModelAttribute @Valid CategoryVo vo, 
			BindingResult result, 
			@PathVariable("id") String id, 
			@RequestParam("name") String name, 
			Model model) {
		
		if (result.hasErrors()) {
			BlogVo blogVo = blogService.getBlog(id);
			model.addAttribute("blogVo", blogVo);
			List<CategoryVo> categoryList = categoryService.getcategoryListAndPostCount(id);
			model.addAttribute("list", categoryList);
			return "blog/admin-category";
		}
		
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);

		categoryService.addCategory(id, name);
		return "redirect:/" + id + "/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/category/delete/{no}")
	public String deleteCategory(@PathVariable("id") String id, @PathVariable("no") Long no) {
		
		categoryService.removeCategory(no);
		return "redirect:/" + id + "/admin/category";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.GET)
	public String writeCategory(@ModelAttribute PostVo vo, @PathVariable("id") String id, Model model) {
		BlogVo blogVo = blogService.getBlog(id);
		model.addAttribute("blogVo", blogVo);

		List<CategoryVo> categoryVo = categoryService.getcategoryList(id);
		model.addAttribute("categoryVo", categoryVo);
		return "blog/admin-write";
	}

	@Auth
	@RequestMapping(value = "/admin/write", method = RequestMethod.POST)
	public String writeCategory(@ModelAttribute @Valid PostVo vo, 
			BindingResult result, 
			@PathVariable("id") String id,
			Model model) {

		if (result.hasErrors()) {
			model.addAllAttributes(result.getModel());
			BlogVo blogVo = blogService.getBlog(id);
			model.addAttribute("blogVo", blogVo);
			List<CategoryVo> categoryVo = categoryService.getcategoryList(id);
			model.addAttribute("categoryVo", categoryVo);
			return "blog/admin-write";
		}
		
		postService.addPost(vo);

		return "redirect:/" + id + "/admin/category";
	}
}
