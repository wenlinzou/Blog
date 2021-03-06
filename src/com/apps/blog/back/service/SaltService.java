package com.apps.blog.back.service;

import com.apps.blog.back.bean.Salt;

public interface SaltService<T> {
	public void add(Salt salt);
	public Salt querySalt(String id);
	public void update(Salt salt);
}
