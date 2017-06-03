package com.mrlimrli.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlimrli.dao.IArticleTagDao;
import com.mrlimrli.dao.ITagDao;
import com.mrlimrli.entities.Tag;
import com.mrlimrli.utils.StringTools;

@Service("tagService")
public class TagService {
	
	@Resource(name = "tagDao")
	private ITagDao tagDao;
	
	@Resource(name = "articleTagDao")
	private IArticleTagDao articleTagDao;
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午2:11:44
	 * @throws Exception
	 * @return List<Tag>
	 * @description 获取所有标签
	 */
	public List<Tag> geTags() throws Exception {
		return tagDao.getTags();
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午10:11:04
	 * @throws Exception
	 * @return Tag
	 * @description 获取单个标签
	 */
	public Tag getTag(Integer tagId) throws Exception {
		return tagDao.getTagById(tagId);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午10:16:40
	 * @param tagName
	 * @throws Exception
	 * @return String
	 * @description 增加标签
	 */
	public String addTag(String tagName) throws Exception {
		Tag tag = new Tag();
		tag.setName(tagName);
		if (tagDao.add(tag) > 0) {
			return "200";
		} else {
			return "322";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午10:19:57
	 * @param tagId
	 * @param tagName
	 * @throws Exception
	 * @return String
	 * @description 修改标签
	 */
	public String updateTag(Integer tagId, String tagName) throws Exception {
		if (tagDao.updateById(tagId, tagName) > 0) {
			return "200";
		} else {
			return "323";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午10:22:47
	 * @param tagIds
	 * @throws Exception
	 * @return Integer
	 * @description 删除标签
	 */
	public Integer deleteTag(String tagIds) throws Exception {
		String[] idArr = tagIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int tagId = Integer.parseInt(idArr[i]);
			int code = tagDao.delTag(tagId);
			if (code > 0) {
				articleTagDao.delByTagId(tagId);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
}
