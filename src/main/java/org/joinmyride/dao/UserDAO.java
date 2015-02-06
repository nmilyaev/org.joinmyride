package org.joinmyride.dao;

import java.util.List;

import org.joinmyride.model.User;

public interface UserDAO {
	public List<User> list();
	public User add();
	public void update(User obj);
	public User getById(int id);
	public void delete(int id);
}
