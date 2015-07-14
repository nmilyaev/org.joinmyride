package org.joinmyride.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.joinmyride.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("userDao")
public class UserDAOImpl implements UserDAO {
	private SessionFactory sessionFactory;
	private static Logger LOG = Logger.getLogger(UserDAOImpl.class);

	@Override
	public List<User> list() {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<User> listUser = (List<User>) session.createCriteria(User.class).list();
		return listUser;
	}

	@Override
	public void update(User obj) {
		Session session = this.sessionFactory.getCurrentSession();
		session.saveOrUpdate(obj);
		session.saveOrUpdate(obj);
		LOG.info("User updated successfully, Person Details=" + obj);
	}

	@Override
	public User getById(int id) {
		Session session = this.sessionFactory.openSession();
        User u = null;
        //needed for creating a user
        if (id == 0)
            u = new User();
        else
		    u = (User) session.get(User.class, new Integer(id));
		return u;
	}

	@Override
	public void delete(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User obj = (User) session.get(User.class, new Integer(id));
        if(null != obj){
            session.delete(obj);
        }
	}

	@Override
	public User add() {
		Session session = this.sessionFactory.getCurrentSession();
		User obj = new User();
		session.persist(obj);
		LOG.info("Person saved successfully, Person Details=" + obj);
		return obj;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
