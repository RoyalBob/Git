package com.mrlimrli.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.mrlimrli.dto.ArticleCommentsDTO;
import com.mrlimrli.dto.ArticleCommentsReplyDTO;
import com.mrlimrli.entities.ArticleComments;

@Repository("articleCommentsDao")
public interface IArticleCommentsDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into article_comments (article_id,nickname,email,comment,status,createtime) values (#{article_id},#{nickname},#{email},#{comment},#{status},SYSDATE())")
	public Integer add(ArticleComments comments)throws Exception;
	
	@Select("select * from v_article_comments where status=#{status} order by createtime desc limit #{begin},#{pageSize}")
	public List<ArticleCommentsDTO> getByStatusAndPage(@Param("status")String status, @Param("begin")Integer begin, @Param("pageSize")Integer pageSize) throws Exception;
	
	@Select("select * from v_article_comments_reply where article_id=#{article_id} and status='1' order by comment_createtime desc")
	public List<ArticleCommentsReplyDTO> getCommentsAndReply(Integer article_id) throws Exception;
	
	@Select("select count(*) from article_comments where status=#{status}")
	public Integer countByStatus(String status) throws Exception;
	
	@Select("select count(*) from article_comments where article_id=#{article_id} and status=#{status}")
	public Integer countByArticleId(@Param("article_id")Integer article_id, @Param("status")String status) throws Exception;
	
	@Select("SELECT count(*) from article_comments where TO_DAYS(createtime) = TO_DAYS(NOW())")
	public Integer countTodayComments() throws Exception;
	
	@Update("update article_comments set status=#{status} where id=#{id}")
	public Integer updateStatusById(@Param("id")Integer id, @Param("status")String status) throws Exception;
	
	@Delete("delete from article_comments where id=#{id}")
	public Integer delById(Integer id) throws Exception;
}
