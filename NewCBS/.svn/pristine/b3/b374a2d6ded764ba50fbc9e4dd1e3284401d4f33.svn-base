package bcel.cardcenter.cbs.aarofat.dao;

import java.util.List;

import bcel.cardcenter.cbs.aarofat.entity.User;

public interface UserDao {
	
	public static final String getUserByIdnPasswd ="getUserByIdnPasswd";
	public static final String getUserById = "getUserById";
	public static final String changePassd = "changePasswd";
	
	public User getUserByIdnPasswd(String userId, String passwd);
	public List<User> getUserById(String userId);
	public void saveOrUpdateUser(User user);
	public void changePasswd(String passwd);
}
