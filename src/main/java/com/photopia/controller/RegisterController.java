package com.photopia.controller;

import javax.naming.Binding;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.photopia.model.CommentDAO;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.UserException;

@Controller
public class RegisterController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public String showRegisterPage(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@ModelAttribute @Validated User user, Model model,BindingResult bindingResult) {

		try {
			if(bindingResult.hasErrors()){
				return "register";
			}else {
				userDAO.registerUser(user);
			}
		
			

		} catch (UserException e) {
			model.addAttribute("errorMessage", "Registration failed.");
			return "register";
		}

		return "redirect:/index";

	}

}
