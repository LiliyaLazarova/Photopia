package com.photopia.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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

		List<IUser> allUserFollowers;
		try {
			allUserFollowers = userDAO.getAllUserFollowers(id);
			model.addAttribute("allUserFollowers", allUserFollowers);
			model.addAttribute("user", new User());
		} catch (ClassNotFoundException | UserException | SQLException e) {
			e.printStackTrace();
			return "error";
		}
		return "suggestions";
	}

	@RequestMapping(value = "/follow", method = RequestMethod.POST)
	public void follow(@RequestParam("followingId") int followingId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;
			userDAO.followUser(id, followingId);
		} catch (ClassNotFoundException | UserException | SQLException | IOException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/unfollow", method = RequestMethod.POST)
	public void unfollow(@RequestParam("followingId") int followingId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;
			userDAO.unfollowUser(id, followingId);
		} catch (ClassNotFoundException | UserException | SQLException | IOException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
