package com.mrlimrli.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlimrli.common.Param;
import com.mrlimrli.dao.IAnnouncementDao;
import com.mrlimrli.entities.Announcement;

@Service("announcementService")
public class AnnouncementService {
	
	@Resource(name = "announcementDao")
	private IAnnouncementDao announcementDao;
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日下午2:59:46
	 * @throws Exception
	 * @return Announcement
	 * @description 获取公告
	 */
	public Announcement getAnnouncement() throws Exception {
		return announcementDao.getByStatus(Param.ANNOUNCEMENT_SHOW);
	}
	
	
	public String saveAnnouncement(String content) throws Exception {
		Announcement announcement = new Announcement();
		announcement.setContent(content);
		announcement.setStatus(Param.ANNOUNCEMENT_SHOW);
		if (announcementDao.add(announcement) > 0) {
			return "200";
		} else {
			return "322";
		}
	}
	/**
	 * @author ljiun
	 * @date 2015年5月12日下午3:03:19
	 * @param id
	 * @param content
	 * @throws Exception
	 * @return String
	 * @description 修改公告
	 */
	public String updateAnnouncement(Integer id, String content) throws Exception {
		if (announcementDao.updateById(id, content) > 0) {
			return "200";
		} else {
			return "323";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日下午3:09:16
	 * @param id
	 * @throws Exception
	 * @return String
	 * @description 删除公告
	 */
	public String delAnnouncement(Integer id) throws Exception {
		if (announcementDao.delById(id) > 0) {
			return "200";
		} else {
			return "322";
		}
	}
}
