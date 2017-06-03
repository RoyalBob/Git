package com.mrlimrli.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlimrli.dao.IArticleTypeDao;
import com.mrlimrli.dao.ITypeDao;
import com.mrlimrli.entities.Type;
import com.mrlimrli.utils.StringTools;

@Service("typeService")
public class TypeService {
	
	@Resource(name = "typeDao")
	private ITypeDao typeDao;
	
	@Resource(name = "articleTypeDao")
	private IArticleTypeDao articleTypeDao;
	
	/**
	 * @author ljiun
	 * @date 2015年5月8日下午2:14:32
	 * @throws Exception
	 * @return List<Type>
	 * @description 获取所有类型
	 */
	public List<Type> getTypes() throws Exception {
		return typeDao.getTypes();
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午11:44:13
	 * @param typeId
	 * @throws Exception
	 * @return Type
	 * @description 获取单个类型
	 */
	public Type getType(Integer typeId) throws Exception {
		return typeDao.getTypeById(typeId);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午11:46:02
	 * @param typeName
	 * @throws Exception
	 * @return String
	 * @description 增加类型
	 */
	public String addType(String typeName) throws Exception {
		Type type = new Type();
		type.setName(typeName);
		if (typeDao.add(type) > 0) {
			return "200";
		} else {
			return "322";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午11:46:43
	 * @param typeId
	 * @param typeName
	 * @throws Exception
	 * @return String
	 * @description 修改类型
	 */
	public String updateType(Integer typeId, String typeName) throws Exception {
		if (typeDao.updateById(typeId, typeName) > 0) {
			return "200";
		} else {
			return "323";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午11:47:44
	 * @param typeIds
	 * @throws Exception
	 * @return Integer
	 * @description 删除类型
	 */
	public Integer deleteType(String typeIds) throws Exception {
		String[] idArr = typeIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int typeId = Integer.parseInt(idArr[i]);
			int code = typeDao.delType(typeId);
			if (code > 0) {
				articleTypeDao.delByTypeId(typeId);
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
}
