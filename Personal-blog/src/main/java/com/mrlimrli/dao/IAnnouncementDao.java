package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.Announcement;

@Repository("announcementDao")
public interface IAnnouncementDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into announcement (content,status,createtime) values (#{content},#{status},SYSDATE())")
	public Integer add(Announcement announcement)throws Exception;
	
	@Update("update announcement set content=#{content} where id=#{id}")
	public Integer updateById(@Param("id")Integer id, @Param("content")String content) throws Exception;
	
	@Select("select * from announcement where status=#{status}")
	public Announcement getByStatus(String status) throws Exception;
	
	@Delete("delete from announcement where id=#{id}")
	public Integer delById(Integer id) throws Exception;
	
}
