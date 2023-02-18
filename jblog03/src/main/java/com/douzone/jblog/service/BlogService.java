package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVo;

@Service
public class BlogService {
	
	@Autowired
	private BlogRepository blogRepository;

	public void newBlog(String id) {
		BlogVo vo = new BlogVo();
		vo.setId(id);
		vo.setTitle("환영합니다.");
		vo.setProfile("/assets/images/spring-logo.jpg");
		blogRepository.insert(vo);
	}

	public BlogVo getBlog(String id) {
		return blogRepository.find(id);
	}

	public void updateBlog(BlogVo vo) {
		blogRepository.update(vo);
	}

}
