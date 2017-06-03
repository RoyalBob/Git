package com.mrlimrli.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.mrlimrli.dto.MessageReplyDTO;
import com.mrlimrli.entities.Message;

@Repository("messageDao")
public interface IMessageDao {
	
	@Options(useGeneratedKeys=true,keyProperty="id")
	@Insert("insert into message (name,email,homepage,message,status,createtime) values (#{name},#{email},#{homepage},#{message},#{status},SYSDATE())")
	public Integer add(Message message)throws Exception;
	
	@Select("select count(*) from message where status=#{status}")
	public Integer countByStatus(String status) throws Exception;
	
	@Select("select * from message where id=#{messageId}")
	public Message getById(Integer messageId) throws Exception;
	
	@Select("select * from message where status=#{status} order by createtime desc	 limit #{pageNum},#{pageSize}")
	public List<Message> getByStatus(@Param("status")String status, @Param("pageNum")Integer pageNum, @Param("pageSize")Integer pageSize) throws Exception;
	
	@Select("SELECT count(*) from message where TO_DAYS(createtime) = TO_DAYS(NOW())")
	public Integer countTodayMessages() throws Exception;
	
	@Select("SELECT count(*) from message")
	public Integer countTotalMessages() throws Exception;
	
	@Select("select * from v_message_reply where status='1' order by message_createtime desc limit #{begin},#{pageSize}")
	public List<MessageReplyDTO> getMessagesAndReply(@Param("begin") Integer begin, @Param("pageSize") Integer pageSize) throws Exception;
	
	@Update("update message set status=#{status} where id=#{id}")
	public Integer updateStatusById(@Param("id")Integer id, @Param("status")String status) throws Exception;
	
	@Delete("delete from message where id=#{id}")
	public Integer delById(Integer id) throws Exception;
}
