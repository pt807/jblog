package com.douzone.jblog.repository;

import java.util.HashMap;
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
	
	public List<CategoryVo> findById(String id) {
		return sqlSession.selectList("category.findById", id);
	}

	public Long findNo(String id) {
		return sqlSession.selectOne("category.findNo", id);
	}

	public void insertCategory(String id, String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		map.put("name", name);
		sqlSession.insert("category.insertCategory", map);
	}

	public List<CategoryVo> findListAndPostCount(String id) {
		return sqlSession.selectList("category.findListAndPostCount", id);
	}

	public void deleteCategory(Long no) {
		sqlSession.delete("category.deleteCategory", no);
	}

}
