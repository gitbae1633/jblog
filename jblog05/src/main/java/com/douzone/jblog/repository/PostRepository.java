package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;

	public void insert(PostVo vo) {
		sqlSession.insert("post.insert", vo);
	}

	public List<PostVo> findAll(String id) {
		return sqlSession.selectList("post.findAll", id);
	}

	public PostVo findOne(String id) {
		return sqlSession.selectOne("post.findOne", id);
	}

}
