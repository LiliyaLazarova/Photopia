package com.photopia.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.UserException;

@Controller
public class LoginController {
	
	private static final int SESSION_EXPIRATION_TIME_IN_SECONDS = 600;

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String showLoginPage(Model model) {
		model.addAttribute("user",new User());
		return "index";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.POST)
	public String login(@ModelAttribute User user,Model model,HttpServletRequest request) {
		UserDAO userDAO = new UserDAO();
		int id;
		try {
			id = userDAO.loginUser(user);
			HttpSession session = request.getSession();
			session.setAttribute("userID", id);
			session.setMaxInactiveInterval(SESSION_EXPIRATION_TIME_IN_SECONDS);
			
		} catch (UserException e) {
			model.addAttribute("errorMessage","Invalid user or password");
			return "index";
		}
		
	
		return "redirect:/wall";
		
	}

}
