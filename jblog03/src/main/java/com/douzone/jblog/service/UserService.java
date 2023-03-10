package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.UserVo;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public void join(UserVo userVo) {
		userRepository.insert(userVo);
	}

	public UserVo getUser(UserVo userVo) {
		return userRepository.findByIdAndPassword(userVo);
	}

}
