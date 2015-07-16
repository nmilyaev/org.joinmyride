package org.joinmyride.service;

import org.joinmyride.dao.UserDAO;
import org.joinmyride.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.hibernate.exception.ConstraintViolationException;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by nmilyaev on 3/13/15.
 */
@Service("userService")
@Transactional
public class UserService {
//    private UserDAO dao;

    @Autowired
    private UserDAO dao;

    public User add() {
        return new User();
    }

    /**
     * Updates user record
     * @param o
     * @throws DataIntegrityViolationException - that occurs when supplied
     * user email is not unique (constr_email_uniq) gets violated.
     * Throw the error and process it in the controller
     *
     */
    public void update(User o) throws DataIntegrityViolationException, ConstraintViolationException {
        this.dao.update(o);
    }

    public List<User> list() {
        return this.dao.list();
    }

    public User getById(int id) {
        return this.dao.getById(id);
    }

    public void delete(int id) {
        this.dao.delete(id);
    }

}
