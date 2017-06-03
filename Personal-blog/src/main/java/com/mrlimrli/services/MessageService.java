package com.mrlimrli.services;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mrlimrli.common.Param;
import com.mrlimrli.dao.IMessageDao;
import com.mrlimrli.dao.IMessageReplyDao;
import com.mrlimrli.dto.MessageReplyDTO;
import com.mrlimrli.entities.Message;
import com.mrlimrli.entities.MessageReply;
import com.mrlimrli.utils.StringTools;

@Service("messageService")
public class MessageService {
	
	@Resource(name = "messageDao")
	private IMessageDao messageDao;
	
	@Resource(name = "messageReplyDao")
	private IMessageReplyDao messageReplyDao;
	
	/**
	 * @author ljiun
	 * @date 2015年5月20日下午11:20:15
	 * @param name
	 * @param email
	 * @param homepage
	 * @param content
	 * @throws Exception
	 * @return String
	 * @description 增加留言
	 */
	public String addMessage(String name, String email, String homepage, String content) throws Exception {
		Message message = new Message();
		message.setName(name);
		message.setEmail(email);
		message.setHomepage(homepage);
		message.setMessage(content);
		message.setStatus(Param.MESSAGE_UNCHECK);
		if (messageDao.add(message) > 0) {
			return "200";
		} else {
			return "321";
		}
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午11:26:14
	 * @param messageId
	 * @throws Exception
	 * @return Message
	 * @description 获取留言
	 */
	public Message getMessage(Integer messageId) throws Exception {
		return messageDao.getById(messageId);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月20日下午11:43:58
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<MessageReplyDTO>
	 * @description 获取留言和回复
	 */
	public List<MessageReplyDTO> getMessagesAndReply(Integer pageNum, Integer pageSize) throws Exception {
		if (pageNum < 1) {
			pageNum = 1;
		}
		int begin = (pageNum - 1) * pageSize;
		return messageDao.getMessagesAndReply(begin, pageSize);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午12:16:51
	 * @param messageId
	 * @throws Exception
	 * @return MessageReply
	 * @description 获取留言回复
	 */
	public MessageReply getReply(Integer messageId) throws Exception {
		return messageReplyDao.getReplyById(messageId);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午9:36:08
	 * @param messageIds
	 * @throws Exception
	 * @return Integer
	 * @description 审核通过留言
	 */
	public Integer pass(String messageIds) throws Exception {
		String[] idArr = messageIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int message_id = Integer.parseInt(idArr[i]);
			int code = messageDao.updateStatusById(message_id, Param.MESSAGE_PASS);
			if (code > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午9:36:08
	 * @param messageIds
	 * @throws Exception
	 * @return Integer
	 * @description 审核不通过留言
	 */
	public Integer unPass(String messageIds) throws Exception {
		String[] idArr = messageIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int message_id = Integer.parseInt(idArr[i]);
			int code = messageDao.updateStatusById(message_id, Param.MESSAGE_UNPASS);
			if (code > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午9:36:08
	 * @param messageIds
	 * @throws Exception
	 * @return Integer
	 * @description 删除留言
	 */
	public Integer delete(String messageIds) throws Exception {
		String[] idArr = messageIds.split(",");
		int setTotal = 0;
		for (int i = 0; i < idArr.length; i++) {
			if (StringTools.isEmpty(idArr[i])) {
				continue;
			}
			int message_id = Integer.parseInt(idArr[i]);
			int code = messageDao.delById(message_id);
			if (code > 0) {
				setTotal = setTotal + 1;
			}
		}
		return setTotal;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午9:39:18
	 * @param status
	 * @param pageNum
	 * @param pageSize
	 * @throws Exception
	 * @return List<Message>
	 * @description 分类获取留言
	 */
	public List<Message> getByStatus(String status, Integer pageNum, Integer pageSize) throws Exception {
		int begin = (pageNum - 1) * pageSize;
		List<Message> messages = messageDao.getByStatus(status, begin, pageSize);
		for (Message message :messages) {
			String content = message.getMessage();
			content = StringTools.removeHtmlTag(content);
			if (content.length() > 105) {
				content = content.substring(0, 105) + "...";
			}
			message.setMessage(content);
		}
		return messages;
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月11日下午9:43:06
	 * @param status
	 * @throws Exception
	 * @return Integer
	 * @description 分类获取留言数量
	 */
	public Integer countByStatus(String status) throws Exception {
		return messageDao.countByStatus(status);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月12日上午12:45:32
	 * @param messageId
	 * @param replyMessage
	 * @throws Exception
	 * @return Integer
	 * @description 回复留言
	 */
	public Integer replyMessage(Integer messageId, String replyMessage) throws Exception {
		MessageReply messageReply = new MessageReply();
		messageReply.setMessage_id(messageId);
		messageReply.setReply(replyMessage);
		return messageReplyDao.add(messageReply);
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月13日下午6:30:33
	 * @throws Exception
	 * @return Integer
	 * @description 获取今日留言数
	 */
	public Integer countTodayMessages() throws Exception {
		return messageDao.countTodayMessages();
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月22日下午2:15:21
	 * @throws Exception
	 * @return Integer
	 * @description 获取总的留言条数
	 */
	public Integer countTotalMessages() throws Exception {
		return messageDao.countTodayMessages();
	}
}
