package com.mrlimrli.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.Tag;

@Repository("tagDao")
public interface ITagDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into tag (name) values (#{name})")
	public Integer add(Tag tag)throws Exception;
	
	@Select("select * from tag")
	public List<Tag> getTags()throws Exception;
	
	@Select("select * from tag where id=#{id}")
	public Tag getTagById(Integer id)throws Exception;
	
	@Update("update tag set name=#{name} where id=#{id}")
	public Integer updateById(@Param("id")Integer id, @Param("name")String name) throws Exception;
	
	@Delete("delete from tag where id=#{id}")
	public Integer delTag(Integer id)throws Exception;
}
