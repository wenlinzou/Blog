package com.apps.blog.back.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.base.BaseService;
import com.apps.blog.back.bean.Salt;
import com.apps.blog.back.bean.User;
import com.apps.blog.back.dao.SaltDao;
import com.apps.blog.back.service.SaltService;

@Service("saltServiceImpl")
public class SaltServiceImpl<T> extends BaseService<T> implements SaltService<User> {

	private final static Logger log = Logger.getLogger(SaltServiceImpl.class);
	@Autowired
	private SaltDao<T> saltDao;

	@Override
	public SaltDao<T> getDao() {
		return saltDao;
	}

	@Override
	public Salt querySalt(Integer id) {
		return saltDao.querySalt(id);
	}

	@Override
	public void update(Salt salt) {
		
		saltDao.update(salt);
		
		/*try {
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
			random.setSeed(20);
			byte bytes[] = new byte[20];
			random.nextBytes(bytes);
			System.out.println(bytes.toString());
			//	saltDao.update(salt);
		} catch (Exception e) {
		}*/
	}

	@Override
	public void add(Salt salt) {
		saltDao.add(salt);
		
	}
	

}
