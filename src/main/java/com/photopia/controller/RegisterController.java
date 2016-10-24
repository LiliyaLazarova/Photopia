package com.photopia.controller;

import java.sql.SQLException;

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
	public String register(@ModelAttribute @Valid User user, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("errorMessage", "Registration failed.");
			return "register";
		} else {
			try {
				userDAO.registerUser(user);

			} catch (ClassNotFoundException | UserException | SQLException e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "Registration failed.");
				return "register";
			}
		}
		return "redirect:/index";
	}
}
