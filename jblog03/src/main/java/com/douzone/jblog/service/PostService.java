package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVo;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;

	public void addPost(PostVo vo) {
		postRepository.insert(vo);
	}

	public List<PostVo> findAll(String id) {
		return postRepository.findAll(id);
	}

	public PostVo findOne(String id) {
		return postRepository.findOne(id);
	}
	
}
