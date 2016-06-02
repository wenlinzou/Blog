package com.apps.blog.back.service;

import java.util.List;

import com.apps.blog.back.bean.Share;

public interface ShareService<T> {
	public List<Share> queryAll();

	public Share queryById(Integer id);
	
	public Share queryByArticleId(Integer id);

	public void update(Share share);

	public void add(Share share);

	public boolean checkShareCode(Integer articleId, String shareCode);
	
}
