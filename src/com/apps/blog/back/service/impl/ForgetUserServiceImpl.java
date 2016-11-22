package com.apps.blog.back.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apps.base.BaseService;
import com.apps.base.utils.DateUtil;
import com.apps.base.utils.PropertiesUtils;
import com.apps.blog.back.bean.ForgetUser;
import com.apps.blog.back.dao.ForgetUserDao;
import com.apps.blog.back.service.ForgetUserService;

@Service("forgetUserServiceImpl")
public class ForgetUserServiceImpl<T> extends BaseService<T> implements
		ForgetUserService<ForgetUser> {

	@Autowired
	private ForgetUserDao<T> forgetUserDao;

	@Override
	public ForgetUserDao<T> getDao() {
		return forgetUserDao;
	}

	@Override
	public void add(String userId) {
		// 获取该用户修改记录数
		ForgetUser user = new ForgetUser();
		user.setUserId(userId);
		user.setCount(1);
		user.setDayCount(1);

		forgetUserDao.add(user);
	}

	@Override
	public List<ForgetUser> queryAll() {

		return forgetUserDao.queryAll();
	}

	@Override
	public ForgetUser queryById(Integer id) {
		if (null != id) {
			return forgetUserDao.queryById(id);
		}
		return null;
	}

	@Override
	public void update(ForgetUser user) {
		if (null != user) {
			forgetUserDao.update(user);
		}
	}

	@Override
	public ForgetUser queryUserByUserId(String userId) {
		return forgetUserDao.queryUserByUserId(userId);
	}

	@Override
	public Integer queryCountByUserId(String userId) {
		return forgetUserDao.queryCountByUserId(userId);
	}

	@Override
	public boolean addOrUpdate(String userId) {
		boolean flag = false;
		Integer count = queryCountByUserId(userId);
		if (null == count || count == 0) {
			// add
			add(userId);
			return true;
		} else {
			// update
			ForgetUser user = new ForgetUser();
			// 获取用户的当天dayCount - 获取用户的总count (修改记录数), 插入修改时间
			Integer dayCount = queryUserByUserId(userId).getDayCount();
			
			user.setUserId(userId);
			user.setCount(queryUserByUserId(userId).getCount() + 1);
			user.setCreateTime(new Date());

			// 每天只可修改3次密码
			// 获取的修改创建时间-当前时间
			Date oldCreateTime = queryUserByUserId(userId).getCreateTime();

			// 如果原修改时间和当前时间为同一天则在dayCount++,不是则dayCount为0
			int sameDay = DateUtil.compareDates(oldCreateTime, new Date());
			if (sameDay == 0) {
				String maxCountStr = PropertiesUtils.getValueByName("constant_value.properties", "MAX_COUNT");
				if(dayCount >= Integer.parseInt(maxCountStr)){
					flag = false;
				}else{
					user.setDayCount(dayCount + 1);
				}
			} else {
				user.setDayCount(1);
			}

			update(user);
			flag = true;
		}
		return flag;
	}

	/**
	 * 用户是否可以修改密码-是否超过单日修改密码数
	 */
	@Override
	public boolean checkUpdatePwd(String userId) {
		boolean flag = true;
		Integer count = queryCountByUserId(userId);
		//用户不存在
		if (null == count || count == 0) {
			flag = true;
		} else {
			// 获取用户的当天dayCount - 获取用户的总count (修改记录数), 插入修改时间
			Integer dayCount = queryUserByUserId(userId).getDayCount();

			// 每天只可修改3次密码
			// 获取的修改创建时间-当前时间
			Date oldCreateTime = queryUserByUserId(userId).getCreateTime();

			// 如果原修改时间和当前时间为同一天则在dayCount++,不是则dayCount为0
			int sameDay = DateUtil.compareDates(oldCreateTime, new Date());
			if (sameDay == 0) {
				String maxCountStr = PropertiesUtils.getValueByName("constant_value.properties", "MAX_COUNT");
				if(dayCount >= Integer.parseInt(maxCountStr)){
					flag = false;
				}
			}
		}
		return flag;
	}

	/**
	 * 修改密码是否已超时
	 * @param userId
	 * @return
	 */
	@Override
	public boolean judgeUpdateTimeOver(String userId) {
		ForgetUser user = queryUserByUserId(userId);
		Date createTime = user.getCreateTime();
		long min = DateUtil.getMinuteByTime(createTime);
		
		String maxCountStr = PropertiesUtils.getValueByName("constant_value.properties", "MAX_COUNT_UPDATE_PWD");
		if(min <= Integer.parseInt(maxCountStr))
			return true;
		else
			return false;
	}
	

}
