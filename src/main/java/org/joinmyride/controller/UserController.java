package org.joinmyride.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.joinmyride.model.User;
import org.joinmyride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
	private UserService service;
	private static Logger LOG = Logger.getLogger(UserController.class);

//	@Autowired
//	@Qualifier("userValidator")
//	private Validator validator;
//
//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}
	
	@RequestMapping("/user/list")
	public ModelAndView list() {
		LOG.info("................................In the UserList!");
		List<User> listUsers = service.list();
		ModelAndView model = new ModelAndView("user/list");
		model.addObject("userList", listUsers);
		return model;
	}
	
	@RequestMapping("/user/edit")
	public ModelAndView edit(@RequestParam("id") String id) {
		int idInt = Integer.parseInt(id);
		User user = (User)service.getById(idInt);
		LOG.info(">>>>>>>>>>>>>>>>>> User is:" + user.getClass() + " : " + User.class.equals(user.getClass()));
		ModelAndView model = new ModelAndView("user/edit");
		model.addObject("user", user);
		LOG.info("................................In the UserEdit! : " + user);
		return model;
	}
	
	@RequestMapping("/user/save")
	public ModelAndView save(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
		LOG.info("................................In the SaveUser! : " + user);
		LOG.info("................................bindingResult : " + bindingResult.toString() + " : " + bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			LOG.info("Returning empSave.jsp page");
			return new ModelAndView("redirect:/user/save");
		}
		service.update(user);
		return new ModelAndView("redirect:/do/user/list");
	}

	@RequestMapping("/user/add")
	public ModelAndView add() {
		LOG.info("................................In the AddEdit! : ");
		User user = service.add();
		return new ModelAndView("redirect:/do/user/edit?id=" + user.getId());
	}

	@RequestMapping("/user/delete")
	public ModelAndView delete(@RequestParam("id") String id) {
		LOG.info("................................In the Delete! : ");
		int idInt = Integer.parseInt(id);
		service.delete(idInt);
		return new ModelAndView("redirect:/do/user/list");
	}
	
}
