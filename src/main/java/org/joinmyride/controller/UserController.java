package org.joinmyride.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.joinmyride.model.User;
import org.joinmyride.service.UserService;
import org.joinmyride.validation.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
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

	@Autowired
	private UserFormValidator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/user/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("users", service.list());
		return "/user/list";
	}

	@RequestMapping("/user/edit")
	public String edit(@RequestParam("id") String id, Model model) {
		int idInt = Integer.parseInt(id);
		User user = (User)service.getById(idInt);
		model.addAttribute("user", user);
		return "user/edit";
	}

	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	public String saveDo(@ModelAttribute("user") @Validated User user, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			LOG.debug("Returning empSave.jsp page");
			return "/user/edit";
		}
        try{
		    service.update(user);
        }
        catch (DataIntegrityViolationException e){
            LOG.debug("...................: " + e.getMessage());
            validator.setValidationError("email", bindingResult);
            return "/user/edit";
        }
		return "redirect:/do/user/list";
	}

	@RequestMapping(value = "/user/add", method = RequestMethod.GET)
	public String add(Model model) {
		User user = service.add();
        model.addAttribute("user", user);
        return "redirect:/do/user/edit?id=" + user.getId();
    }

	@RequestMapping(value = "/user/delete", method = RequestMethod.GET )
	public String delete(@RequestParam("id") String id) {
		int idInt = Integer.parseInt(id);
		service.delete(idInt);
		return "redirect:/do/user/list";
	}

}
