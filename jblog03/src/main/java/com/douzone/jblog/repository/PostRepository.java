package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVo;

@Repository
public class PostRepository {
	@Autowired
	private SqlSession sqlSession;
	
	public List<PostVo> findByList(Long categoryNo) {
		return sqlSession.selectList("post.findByList", categoryNo);
	}

	public PostVo findPost(Long categoryNum, Long postNum) {
		Map<String, Object> map = new HashMap<>();
		map.put("categoryNo", categoryNum);
		map.put("no", postNum);
		return sqlSession.selectOne("post.findPost", map);
	}

}
