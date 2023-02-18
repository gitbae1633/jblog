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

	public void newCategory(String id) {
		CategoryVo vo = new CategoryVo();
		vo.setId(id);
		vo.setName("기본");
		categoryRepository.insert(vo);
	}

	public List<CategoryVo> findAll(String id) {
		return categoryRepository.findAll(id);
	}

	public void addCategory(CategoryVo vo) {
		categoryRepository.insert(vo);
	}

	public CategoryVo findByName(String name) {
		return categoryRepository.findByName(name);
	}

	public Long findCount(Long no, String id) {
		return categoryRepository.findCountByNo(no, id);
	}

	public void deleteCategory(Long no) {
		categoryRepository.delete(no);
	}
	
}
