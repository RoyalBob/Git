package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.SystemViews;

@Repository("systemViewsDao")
public interface ISystemViewsDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into system_views (views,ip,createtime) values (#{views},#{ip},SYSDATE())")
	public Integer add(SystemViews systemViews)throws Exception;
	
	@Select("SELECT count(*) from system_views where TO_DAYS(createtime) = TO_DAYS(NOW())")
	public Integer getViewsToday() throws Exception;
	
	@Select("SELECT count(*) from system_views")
	public Integer getViewsHistory() throws Exception;
}
