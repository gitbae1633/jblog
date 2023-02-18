package com.douzone.jblog.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVo;

@Repository
public class CategoryRepository {

	@Autowired
	private SqlSession sqlSession;
	
	public void insert(CategoryVo vo) {
		sqlSession.insert("category.insert", vo);
	}

	public List<CategoryVo> findAll(String id) {
		return sqlSession.selectList("category.findAll", id);
	}

	public CategoryVo findByName(String name) {
		return sqlSession.selectOne("category.findByName", name);
	}

	public Long findCountByNo(Long no, String id) {
		Map<String, Object> map = Map.of("no", no, "id", id);
		return sqlSession.selectOne("category.findCountByNo", map);
	}

	public void delete(Long no) {
		sqlSession.delete("category.delete", no);
	}
	
}
