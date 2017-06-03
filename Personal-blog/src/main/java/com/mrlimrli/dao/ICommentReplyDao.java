package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.CommentReply;

@Repository("commentReplyDao")
public interface ICommentReplyDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into comment_reply (comment_id,reply,createtime) values (#{comment_id},#{reply},SYSDATE())")
	public Integer add(CommentReply commentReply)throws Exception;
	
}
