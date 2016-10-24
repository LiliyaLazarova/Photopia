package com.photopia.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;
import com.photopia.model.NewsFeed;
import com.photopia.model.NewsfeedDAO;
import com.photopia.model.exceptions.NewsfeedException;

@Controller
public class NewsfeedController {

	@Autowired
	NewsfeedDAO newsfeedDAO;

	@RequestMapping(value = "/NewsfeedController", method = RequestMethod.GET)
	public void showNewsFeed(Model model, HttpServletRequest request, HttpServletResponse response) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Set<NewsFeed> allNewsfeeds = new TreeSet<NewsFeed>();
		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;

			allNewsfeeds.addAll(newsfeedDAO.getMyCommentersNames(id));
			allNewsfeeds.addAll(newsfeedDAO.getMyLikersNames(id));
			allNewsfeeds.addAll(newsfeedDAO.getMyFollowersNames(id));

			response.getWriter().println(new Gson().toJson(allNewsfeeds));
		} catch (IOException | ClassNotFoundException | NewsfeedException | SQLException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
