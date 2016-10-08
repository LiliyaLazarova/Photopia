package com.photopia.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.photopia.model.User;
import com.photopia.model.UserDAO;

@Controller
public class RegisterController {
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String showRegisterPage(Model model) {
		model.addAttribute("user",new User());
		return "register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(@ModelAttribute User user,Model model) {
	
		UserDAO userDAO = new UserDAO();
		try {
			int id = userDAO.registerUser(user);
		} catch (Exception e) {
			model.addAttribute("errorMessage","Registration failed.");
			return "register";
		}
	
		return "redirect:/index";
		
	}

}
