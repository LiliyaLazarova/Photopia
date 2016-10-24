package com.photopia.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.photopia.model.Photo;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;

@Controller
public class ChangePasswordController {
	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/changePassword", method = RequestMethod.GET)
	public String showProfile(HttpServletRequest request, Model model) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}

		return "changePassword";
	}

	@RequestMapping(value = "/changing", method = RequestMethod.POST)
	public void changePassword(@RequestParam("oldPass") String oldPass, @RequestParam("newPass") String newPass,
			Model model, HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");

		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {

				response.sendRedirect("./index");

			}
			int id = (int) userId;

			String message;

			message = userDAO.changePassword(newPass, oldPass, id);

			response.getWriter().print(new Gson().toJson(message));
		} catch (IOException | ClassNotFoundException | UserException | SQLException e) {
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

}
