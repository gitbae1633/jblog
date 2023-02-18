package com.douzone.jblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private PostService postService;
	
	@Autowired
	private FileuploadService fileuploadService;
	

	@RequestMapping("") 
	public String main(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("vo", vo);
		
		List<CategoryVo> list = categoryService.findAll(id);
		model.addAttribute("list", list);
		
		List<PostVo> postList = postService.findAll(id);
		model.addAttribute("postList", postList);
		
		PostVo postVo = postService.findOne(id);
		model.addAttribute("postVo", postVo);
		
		return "blog/main";
	}
	
	@Auth
	@RequestMapping("/adminbasic")	
	public String adminBasic(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("vo", vo);
		return "blog/admin-basic";
	}
	

	@Auth
	@RequestMapping("/admincategory")
	public String admincategory(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("vo", vo);
		
		List<CategoryVo> list = categoryService.findAll(id);

		for (CategoryVo categoryVo: list) {
			categoryVo.setCount(categoryService.findCount(categoryVo.getNo(), id));
		}
		
		model.addAttribute("list", list);
		return "blog/admin-category";
	}

	@Auth
	@RequestMapping("/adminwrite")
	public String adminwrite(@PathVariable("id") String id, Model model) {
		BlogVo vo = blogService.getBlog(id);
		model.addAttribute("vo", vo);
		
		List<CategoryVo> list = categoryService.findAll(id);
		model.addAttribute("list", list);
		
		return "blog/admin-write";
	}
	

	@Auth
	@RequestMapping("/categoryadd")
	public String categoryadd(@PathVariable("id") String id, @RequestParam("name") String name) {
		CategoryVo vo = new CategoryVo();
		vo.setId(id);
		vo.setName(name);
		categoryService.addCategory(vo);
		
		return "redirect:/" + id + "/admincategory";
	}
	
	@Auth
	@RequestMapping("/categorydelete/{no}")
	public String categorydelete(@PathVariable("id") String id, @PathVariable("no") Long no) {
		categoryService.deleteCategory(no);
		return "redirect:/" + id + "/admincategory";
	}
	
	@Auth
	@RequestMapping("/write")
	public String write(@PathVariable("id") String id, 
			@RequestParam("category") String categoryName, PostVo vo) {
		
		CategoryVo categoryVo = categoryService.findByName(categoryName);
		vo.setCategoryNo(categoryVo.getNo());
		postService.addPost(vo);
		
		return "redirect:/" + id;
	}
	
	@Auth
	@RequestMapping("/update")
	public String mainupdate(@PathVariable("id") String id, BlogVo vo, MultipartFile file) {
		String profile = fileuploadService.restore(file);
		if(profile != null) {
			vo.setProfile(profile);
		}
		
		vo.setId(id);
		blogService.updateBlog(vo);
		return "redirect:/" + id;
	}
}
