package bcel.cardcenter.cbs.aarofat.dao;

import java.util.Arrays;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

import bcel.cardcenter.cbs.aarofat.entity.User;

public class UserDaoImp implements UserDao {
	private HibernateTemplate hibernateTemplate;
	
	public void setSessionFactory(SessionFactory sessionFactory){
		hibernateTemplate = new HibernateTemplate(sessionFactory);
	}
	@Override
	public User getUserByIdnPasswd(String userId, String passwd) {
		String[] params = {"userId","passwd"};
		String[] values = {userId,passwd};
		return toList(hibernateTemplate.findByNamedQueryAndNamedParam(getUserByIdnPasswd, params, values)).get(0);
	}

	@Override
	public List<User> getUserById(String userId) {
		String param = "userId";
		String value = userId;
		return toList(hibernateTemplate.findByNamedQueryAndNamedParam(getUserById, param, value));
	}
	
	@Override
	public void saveOrUpdateUser(User user) {
		hibernateTemplate.saveOrUpdate(user);
	}

	@Override
	public void changePasswd(String passwd) {
		// TODO Auto-generated method stub
	}
	
	// Convert Object List to User List
	private static List<User> toList(final List<?> objs){
		if(objs==null)
			return null;
		if(objs.isEmpty())
			return null;
		User[] list = new User[objs.size()];
		list = (User[])objs.toArray(list);
		return Arrays.asList(list);
	}
}
