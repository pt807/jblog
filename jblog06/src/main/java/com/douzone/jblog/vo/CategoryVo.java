package com.douzone.jblog.vo;

import javax.validation.constraints.NotEmpty;

public class CategoryVo {
	private Long no;
	
	@NotEmpty
	private String name;
	private String id;
	private Long postCount;

	public Long getNo() {
		return no;
	}

	public void setNo(Long no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getPostCount() {
		return postCount;
	}

	public void setPostCount(Long postCount) {
		this.postCount = postCount;
	}

	@Override
	public String toString() {
		return "CategoryVo [no=" + no + ", name=" + name + ", id=" + id + ", postCount=" + postCount + "]";
	}

}
