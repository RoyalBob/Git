package com.mrlimrli.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlimrli.dao.ISystemViewsDao;
import com.mrlimrli.entities.SystemViews;

@Service("systemService")
public class SystemService {
	
	@Resource(name = "systemViewsDao")
	private ISystemViewsDao systemViewsDao;
	
	/**
	 * @author ljiun
	 * @date 2015年5月13日下午5:15:59
	 * @throws Exception
	 * @return Integer
	 * @description 获取今日访问数
	 */
	public Integer getViewsToday() throws Exception {
		return systemViewsDao.getViewsToday();
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月13日下午5:20:37
	 * @throws Exception
	 * @return Integer
	 * @description 获取历史访问次数
	 */
	public Integer getViewsHistory() throws Exception {
		return systemViewsDao.getViewsHistory();
	}
	
	/**
	 * @author ljiun
	 * @date 2015年6月12日上午11:09:18
	 * @param ip
	 * @throws Exception
	 * @return Integer
	 * @description 增加访问次数和ip
	 */
	public void addViews(String ip) throws Exception {
		SystemViews views = new SystemViews();
		views.setViews(1);
		views.setIp(ip);
		systemViewsDao.add(views);
	}
	
}
