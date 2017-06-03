package com.mrlimrli.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.Type;

@Repository("typeDao")
public interface ITypeDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into type (name) values (#{name})")
	public Integer add(Type type)throws Exception;
	
	@Select("select * from type")
	public List<Type> getTypes()throws Exception;
	
	@Select("select * from type where id=#{id}")
	public Type getTypeById(Integer id)throws Exception;
	
	@Update("update type set name=#{name} where id=#{id}")
	public Integer updateById(@Param("id")Integer id, @Param("name")String name) throws Exception;
	
	@Delete("delete from type where id=#{id}")
	public Integer delType(Integer id)throws Exception;
}
