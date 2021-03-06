package com.apps.blog.back.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.base.BaseService;
import com.apps.blog.back.bean.Salt;
import com.apps.blog.back.dao.SaltDao;
import com.apps.blog.back.service.SaltService;

@Service("saltServiceImpl")
public class SaltServiceImpl<T> extends BaseService<T> implements SaltService<Salt> {

	@Autowired
	private SaltDao<T> saltDao;

	@Override
	public SaltDao<T> getDao() {
		return saltDao;
	}

	@Override
	public Salt querySalt(String id) {
		return saltDao.querySalt(id);
	}

	@Override
	@Transactional
	public void update(Salt salt) {
		saltDao.update(salt);
	}

	@Override
	@Transactional
	public void add(Salt salt) {
		saltDao.add(salt);
		
	}
	

}
