package com.photopia.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mockito.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;

@Controller
public class SearchController {

	@Autowired
	UserDAO userDAO;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String showSearchPage(Model model, HttpServletRequest request, HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		return "search";
	}

	@RequestMapping(value = "/searchUsers", method = RequestMethod.GET)
	public void showSearchResult(@RequestParam("prefix") String prefix, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
			}
			List<IUser> usersFound;
			usersFound = userDAO.searchForUser(prefix);
			response.getWriter().print(new Gson().toJson(usersFound));
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
