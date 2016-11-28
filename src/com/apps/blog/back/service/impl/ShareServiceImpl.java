package com.apps.blog.back.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apps.base.BaseService;
import com.apps.base.utils.CodeUtils;
import com.apps.base.utils.MyStringUtils;
import com.apps.blog.back.bean.Share;
import com.apps.blog.back.dao.ShareDao;
import com.apps.blog.back.service.ShareService;

@Service("shareServiceImpl")
public class ShareServiceImpl<T> extends BaseService<T> implements ShareService<T> {

	@Autowired
	private ShareDao<T> shareDao;

	@Override
	public ShareDao<T> getDao() {
		return shareDao;
	}

	@Override
	public List<Share> queryAll() {
		return shareDao.queryAll();
	}

	@Override
	public Share queryById(Integer id) {
		if (null != id) {
			return shareDao.queryById(id);
		}
		return null;
	}
	
	@Override
	public Share queryByArticleId(Integer id){
		if (null != id) {
			return shareDao.queryByArticleId(id);
		}
		return null;
	}

	@Override
	@Transactional
	public void update(Share share) {
		//生成链接密码
		String codeStr = CodeUtils.randomCode(4);
		if (null != share) {
			share.setCode(codeStr);
			share.setShareDate(new Date());
			shareDao.update(share);
		}
	}

	@Override
	@Transactional
	public void add(Share share) {
		if (null != share) {
			String code = CodeUtils.randomCode(4);
			share.setCode(code);
			shareDao.add(share);
		}
	}

	@Override
	public boolean checkShareCode(Integer articleId, String shareCode){
		boolean isRight = false;
		Share share = shareDao.queryByArticleId(articleId);
		String code = share.getCode();
		if(!MyStringUtils.isNull(code) && !MyStringUtils.isNull(shareCode)){
			isRight = MyStringUtils.slowEquals(code.getBytes(), shareCode.getBytes());
		}
		return isRight;
	}
	
	
}
