package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.ArticleTag;

@Repository("articleTagDao")
public interface IArticleTagDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into article_tag (article_id,tag_id,createtime) values (#{article_id},#{tag_id},SYSDATE())")
	public Integer add(ArticleTag articleTag)throws Exception;
	
	@Select("select * from article_tag where article_id=#{article_id} and tag_id=#{tag_id}")
	public ArticleTag getByArticleIdTagId(@Param("article_id") Integer article_id, @Param("tag_id") Integer tag_id)throws Exception;
		
	@Delete("delete from article_tag where article_id=#{article_id}")
	public Integer delByArticleId(@Param("article_id") Integer article_id)throws Exception;
	
	@Delete("delete from article_tag where tag_id=#{tag_id}")
	public Integer delByTagId(Integer tag_id)throws Exception;
	
	@Delete("delete from article_tag where article_id=#{article_id} and tag_id=#{tag_id}")
	public Integer delByArticleIdTagId(@Param("article_id") Integer article_id, @Param("tag_id") Integer tag_id)throws Exception;

}
