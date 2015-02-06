package org.joinmyride.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.joinmyride.dao.UserDAO;
import org.joinmyride.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {
	
	@Autowired
	private UserDAO userDao;
	private static Logger LOG = Logger.getLogger(UserController.class);
	
	@RequestMapping("/user/list")
	public ModelAndView list() {
		LOG.info("................................In the UserList!");
		List<User> listUsers = userDao.list();
		ModelAndView model = new ModelAndView("user/list");
		model.addObject("userList", listUsers);
		return model;
	}
	
	@RequestMapping("/user/edit")
	public ModelAndView edit(@RequestParam("id") String id) {
		int idInt = Integer.parseInt(id);
		User user = userDao.getById(idInt);
		ModelAndView model = new ModelAndView("user/edit");
		model.addObject("user", user);
		LOG.info("................................In the UserEdit! : " + user);
		return model;
	}
	
	@RequestMapping("/user/save")
	public ModelAndView save(@ModelAttribute("user") User user) {
		LOG.info("................................In the SaveUser! : " + user);
		userDao.update(user);
		return new ModelAndView("redirect:/do/user/list");
	}
	
	@RequestMapping("/user/add")
	public ModelAndView add() {
		LOG.info("................................In the AddEdit! : ");
		User user = userDao.add();
		return new ModelAndView("redirect:/do/user/edit?id=" + user.getId());
	}

	@RequestMapping("/user/delete")
	public ModelAndView delete(@RequestParam("id") String id) {
		LOG.info("................................In the Delete! : ");
		int idInt = Integer.parseInt(id);
		userDao.delete(idInt);
		return new ModelAndView("redirect:/do/user/list");
	}
	
}
