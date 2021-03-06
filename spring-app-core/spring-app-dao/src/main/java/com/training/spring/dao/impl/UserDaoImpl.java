package com.training.spring.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.hibernate.criterion.Restrictions;

import com.training.spring.dao.UserDao;
import com.training.spring.model.User;
import com.training.spring.model.UserStatus;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String username) {
		User user = (User) sessionFactory.getCurrentSession()
			.createCriteria(User.class)
			.add(Restrictions.eq("username",username))
			.uniqueResult();
		return user;	
	}

	@Override
	public void save(User user){
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public List<User> getPendingUsers(){
		List<User> users = (List<User>) sessionFactory.getCurrentSession()
			.createCriteria(User.class)
			.add(Restrictions.eq("userStatus",UserStatus.PENDING))
			.list();
		return users;
	}

	@Override
	public void acceptAccount(int id){
		User user = (User) sessionFactory.getCurrentSession().get(User.class, id);
		user.setEnabled(true);
		user.setUserStatus(UserStatus.ACTIVE);
		sessionFactory.getCurrentSession().update(user);
	}

}
