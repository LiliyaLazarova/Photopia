package com.photopia.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.photopia.model.Comment;
import com.photopia.model.CommentDAO;
import com.photopia.model.Post;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.CommentException;
import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;


@Controller
public class SuggestionController {

	@Autowired
	UserDAO userDAO;
	
	
	@RequestMapping(value = "/suggestions", method = RequestMethod.GET)
	public String showSuggestionPage(Model model, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;

		try {
			List<IUser> allUserFollowers = userDAO.getAllUserFollowers(id);
			model.addAttribute("allUserFollowers", allUserFollowers);
			model.addAttribute("user", new User());
		} catch (UserException e) {
			return "redirect:/suggestions";
		}
		return "suggestions";
	}

	@RequestMapping(value = "/suggestions", method = RequestMethod.POST)
	public String follow(@ModelAttribute User user, Model model, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;

		try {
			userDAO.followUser(id, user.getUserId());
		} catch (UserException e) {
			return "redirect:/suggestions";
		}
		return "redirect:/suggestions";

	}


}
