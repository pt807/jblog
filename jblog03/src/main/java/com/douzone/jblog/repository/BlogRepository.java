package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVo;

@Repository
public class BlogRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public void insert(String id) {
		sqlSession.insert("blog.insert", id);
	}

	public BlogVo findBlog(String id) {
		return sqlSession.selectOne("blog.findBlog", id);
	}

	public void updateImage(String id, String url, String title) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("profile", url);
		map.put("title", title);
		sqlSession.insert("blog.updateImage" ,map);
	}

	public List<String> findAll() {
		return sqlSession.selectList("blog.findAll");
	}

}
