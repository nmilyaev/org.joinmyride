package org.joinmyride.service;

import org.joinmyride.dao.UserDAO;
import org.joinmyride.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by nmilyaev on 3/13/15.
 */
@Service
public class UserService {
    private UserDAO dao;

    public void setDao(UserDAO dao) {
        this.dao = dao;
    }

    @Transactional
    public User add() {
        return this.dao.add();
    }

    @Transactional
    public void update(User o) {
        this.dao.update(o);
    }

    @Transactional
    public List<User> list() {
        return this.dao.list();
    }

    @Transactional
    public User getById(int id) {
        return this.dao.getById(id);
    }

    @Transactional
    public void delete(int id) {
        this.dao.delete(id);
    }

}
