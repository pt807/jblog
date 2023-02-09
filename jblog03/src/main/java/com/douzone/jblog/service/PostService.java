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

	public List<PostVo> getPosts(Long categoryNum) {
		return postRepository.findByList(categoryNum);
	}

	public PostVo getPost(Long categoryNum, Long postNum) {
		return postRepository.findPost(categoryNum, postNum);
	}

	public void addPost(PostVo postVo) {
		postRepository.insertPost(postVo);
	}

}
