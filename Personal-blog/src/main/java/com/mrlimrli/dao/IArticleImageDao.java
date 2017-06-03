package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.ArticleImage;

@Repository("articleImageDao")
public interface IArticleImageDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into article_image (article_id,imgPath,quote_name) values (#{article_id},#{imgPath},#{quote_name})")
	public Integer add(ArticleImage articleImage)throws Exception;
	
	@Select("select * from article_image where article_id=#{article_id}")
	public ArticleImage getById(Integer article_id)throws Exception;
	
}
