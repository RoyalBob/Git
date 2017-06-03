package com.mrlimrli.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.mrlimrli.entities.MessageReply;

@Repository("messageReplyDao")
public interface IMessageReplyDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into message_reply (message_id,reply,createtime) values (#{message_id},#{reply},SYSDATE())")
	public Integer add(MessageReply messageReply) throws Exception;
	
	@Select("select * from message_reply where message_id=#{message_id}")
	public MessageReply getReplyById(Integer message_id) throws Exception;
}
