package com.mrlimrli.services;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mrlimrli.dao.IUserDao;
import com.mrlimrli.dto.LoggedUser;
import com.mrlimrli.entities.User;
import com.mrlimrli.utils.EncryptUtil;

@Service("userService")
public class UserService {

	@Resource(name="userDao")
	private IUserDao userDao;
	
	public String add(User user) throws Exception {
		if (userDao.add(user) > 0) {
			return "200";
		} else {
			return "321";
		}
		
	}
	
	/**
	 * @author ljiun
	 * @date 2015年5月7日下午8:35:49
	 * @param username
	 * @param password
	 * @throws Exception
	 * @return User
	 * @description 登录
	 */
	public User login(String username, String password) throws Exception {
		password = EncryptUtil.md5(password);
		User user = userDao.getByUsernamePwd(username, password);
		return user;
	}
	
	/**
	 * 设置session
	 * @author ljiun
	 * @date 2015年5月7日下午8:39:11
	 * @param request
	 * @param user
	 * @throws Exception
	 * @return boolean
	 * @description
	 */
	public boolean setSession(HttpServletRequest request, User user) throws Exception{
		LoggedUser loggedUser = new LoggedUser();
		loggedUser.setUsername(user.getUsername());
		loggedUser.setRealname(user.getRealname());
		loggedUser.setHeadImgPath(user.getHeadImgPath());
		loggedUser.setGender(user.getGender());
		loggedUser.setEmail(user.getEmail());
		loggedUser.setAge(user.getAge());
		loggedUser.setValidate(EncryptUtil.sha("AzD3jO84x"));
		request.getSession().setAttribute("loggedUser", loggedUser);
		return true;
	}

}
