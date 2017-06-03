package com.mrlimrli.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.mrlimrli.dto.ArticleDTO;
import com.mrlimrli.dto.ArticleTagDTO;
import com.mrlimrli.dto.ArticleTypeDTO;
import com.mrlimrli.entities.Article;

@Repository("articleDao")
public interface IArticleDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into article (title,content,author,views,status,createtime) values (#{title},#{content},#{author},#{views},#{status},SYSDATE())")
	public Integer add(Article article)throws Exception;
	
	@Update("update article set views=views+1 where id=#{article_id}")
	public Integer addViews(Integer article_id) throws Exception;
	
	@Insert("update article set title=#{title},content=#{content},status=#{status} where id=#{id}")
	public Integer update(Article article)throws Exception;
	
	@Select("select * from article where id=#{articleId}")
	public Article getArticle(Integer articleId)throws Exception;
	
	@Select("select * from v_article_type where type_id=#{typeId} limit #{begin},#{pageSize}")
	public List<ArticleTypeDTO> getArticleByTypeId(@Param("typeId")Integer typeId, @Param("begin")Integer begin, @Param("pageSize")Integer pageSize)throws Exception;
	
	@Select("select * from v_article_tag where tag_id=#{tagId} limit #{begin},#{pageSize}")
	public List<ArticleTagDTO> getArticleByTagId(@Param("tagId")Integer tagId, @Param("begin")Integer begin, @Param("pageSize")Integer pageSize)throws Exception;
	
	@Select("select * from article where status=#{status} order by createtime desc limit #{begin},#{pageSize}")
	public List<Article> getArticles(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize, @Param("status") String status)throws Exception;
	
	@Select("select * from article where status=#{status} limit #{begin},#{pageSize}")
	public List<ArticleDTO> getIndexArticles(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize, @Param("status") String status)throws Exception;
	
	@Select("select * from article where status=#{status} order by createtime desc")
	public List<Article> getArticleByStatus(@Param("status") String status)throws Exception;
	
	@Select("select * from article where status=#{status} order by createtime desc limit 7")
	public List<Article> getRecentArticles(@Param("status") String status)throws Exception;
	
	@Select("select count(*) from article where status=#{status}")
	public Integer countArticles(String status) throws Exception;
	
	@Select("select count(*) from v_article_type where type_id=#{typeId} and status=#{status}")
	public Integer countTypeArticles(@Param("typeId")Integer typeId, @Param("status")String status) throws Exception;
	
	@Select("select count(*) from v_article_tag where tag_id=#{tagId} and status=#{status}")
	public Integer countTagArticles(@Param("tagId")Integer tagId, @Param("status")String status) throws Exception;
	
	@Update("update article set status=#{status} where id=#{id}")
	public Integer updateStatusById(@Param("id")Integer id, @Param("status")String status) throws Exception;
	
	@Delete("delete from article where id=#{id}")
	public Integer delById(@Param("id")Integer id) throws Exception;
	
}
