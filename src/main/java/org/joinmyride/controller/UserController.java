package org.joinmyride.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.joinmyride.model.User;
import org.joinmyride.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.ui.Model;

/**
 * Handles requests for the application home page.
 */
@Controller
public class UserController {

    @Autowired(required=true)
	private UserService service;

	private static Logger LOG = Logger.getLogger(UserController.class);

//	@Autowired
//	@Qualifier("userValidator")
//	private Validator validator;

//	@InitBinder
//	private void initBinder(WebDataBinder binder) {
//		binder.setValidator(validator);
//	}

    @ModelAttribute("user")
    public User createUserModel(){
        return new User();
    }

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String list(Model model) {
		LOG.debug("................................In the UserList!");
		model.addAttribute("users", service.list());
		return "/user/list";
	}

	@RequestMapping("/user/edit")
	public String edit(@RequestParam("id") String id, Model model) {
		int idInt = Integer.parseInt(id);
		User user = (User)service.getById(idInt);
		LOG.debug(">>>>>>>>>>>>>>>>>> User is:" + user.getClass() + " : " + User.class.equals(user.getClass()));
		model.addAttribute("user", user);
		LOG.debug("................................In the UserEdit! : " + user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String saveDo(@ModelAttribute("user") @Validated User user, BindingResult bindingResult) {
		LOG.debug("................................In the SaveUser! : " + user);
		LOG.debug("................................bindingResult : " + bindingResult.toString() + " : " + bindingResult.hasErrors());
		if (bindingResult.hasErrors()) {
			LOG.debug("Returning empSave.jsp page");
			return "redirect:/do/user/edit?id="+user.getId();
		}
		service.update(user);
		return "redirect:/do/user/list";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String add(Model model) {
		LOG.debug("................................In the Add! : ");
		User user = service.add();
        model.addAttribute("user", user);
        return "redirect:/do/user/edit?id=" + user.getId();
    }

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET )
	public String delete(@RequestParam("id") String id) {
		LOG.debug("................................In the Delete! : ");
		int idInt = Integer.parseInt(id);
		service.delete(idInt);
		return "redirect:/do/user/list";
	}

}
