package com.photopia.controller;


import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.photopia.model.DaoConfiguration;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.UserException;


@Controller
public class LoginController {
	
	private static final int SESSION_EXPIRATION_TIME_IN_SECONDS = 600;
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value="/index", method = RequestMethod.GET)
	public String showLoginPage(Model model) {
		model.addAttribute("user",new User());
		return "index";
	}
	
	@RequestMapping(value="/index", method = RequestMethod.POST)
	
	public String login(@ModelAttribute @Valid User user,BindingResult bindingResult,Model model,HttpServletRequest request) {

		int id;
		try {
//			if(bindingResult.hasErrors()){
//				model.addAttribute("errorMessage", "Login failed.");
//				return "index";
//			}
			id = userDAO.loginUser(user);
			HttpSession session = request.getSession();
			session.setAttribute("userID", id);
			session.setMaxInactiveInterval(SESSION_EXPIRATION_TIME_IN_SECONDS);
			
		} catch (UserException | ClassNotFoundException | SQLException e) {
			model.addAttribute("errorMessage","Invalid email or password");
			return "index";
		}
		
	
		return "redirect:/wall";
		
	}

}
