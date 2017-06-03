package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.User;

@Repository("userDao")
public interface IUserDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into user (user_id) values (#{user_id})")
	public Integer add(User user)throws Exception;
	
	@Select("select * from user where username=#{username} and password=#{password}")
	public User getByUsernamePwd(@Param("username")String username, @Param("password")String password)throws Exception;
}
