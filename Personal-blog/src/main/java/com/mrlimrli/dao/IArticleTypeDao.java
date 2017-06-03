package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.ArticleType;

@Repository("articleTypeDao")
public interface IArticleTypeDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into article_type (article_id,type_id,createtime) values (#{article_id},#{type_id},SYSDATE())")
	public Integer add(ArticleType articleType)throws Exception;
	
	@Select("select * from article_type where article_id=#{article_id} and type_id=#{type_id}")
	public ArticleType getByArticleIdTypeId(@Param("article_id") Integer article_id, @Param("type_id") Integer type_id)throws Exception;

	@Delete("delete from article_type where article_id=#{article_id}")
	public Integer delByArticleId(@Param("article_id") Integer article_id)throws Exception;
	
	@Delete("delete from article_type where type_id=#{type_id}")
	public Integer delByTypeId(Integer type_id)throws Exception;
	
	@Delete("delete from article_type where article_id=#{article_id} and type_id=#{type_id}")
	public Integer delByArticleIdTypeId(@Param("article_id") Integer article_id, @Param("type_id") Integer type_id)throws Exception;

}
