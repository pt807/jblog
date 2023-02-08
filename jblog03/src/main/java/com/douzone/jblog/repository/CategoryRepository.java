package com.douzone.jblog.repository;

import java.util.List;

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

}
