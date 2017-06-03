package com.mrlimrli.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mrlimrli.dto.ArticleRecomDTO;
import com.mrlimrli.entities.ArticleRecom;

@Repository("articleRecomDao")
public interface IArticleRecomDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into article_recom (article_id,createtime) values (#{article_id},SYSDATE())")
	public Integer add(ArticleRecom articleRecom)throws Exception;
	
	@Select("select * from article_recom where article_id=#{article_id}")
	public ArticleRecom getRecomArticle(Integer article_id)throws Exception;
	
	@Select("select * from v_article_recom order by createtime desc")
	public List<ArticleRecomDTO> getRecomArticles()throws Exception;
	
	@Select("select count(*) from article_recom")
	public Integer countRecomArticles()throws Exception;
	
	@Delete("delete from article_recom where article_id=#{article_id}")
	public Integer delRecomArticle(Integer article_id)throws Exception;
}
