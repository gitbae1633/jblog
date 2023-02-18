package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BlogService blogService;
	
	@Autowired
	private CategoryService categoryService;
	
	// 1. 회원가입 페이지 이동하기
	@RequestMapping(value = "/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
		
	// 2. 회원가입
	@RequestMapping(value = "/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		userService.join(vo);
		blogService.newBlog(vo.getId());
		categoryService.newCategory(vo.getId());
		return "user/joinsuccess";
	}
	
	// 3. 로그인 페이지 이동
	@RequestMapping(value = "/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
}
