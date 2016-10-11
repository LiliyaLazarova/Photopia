package com.photopia.controller;

import java.io.IOException;
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
		Set<NewsFeed> allNewsfeeds=new TreeSet<NewsFeed>();
		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
		//	return "redirect:/index";
		}
		int id = (int) userId;
		
			try {

				allNewsfeeds.addAll(newsfeedDAO.getMyCommentersNames(id));
				allNewsfeeds.addAll(newsfeedDAO.getMyLikersNames(id));
			allNewsfeeds.addAll(newsfeedDAO.getMyFollowersNames(id));

			} catch (NewsfeedException e) {
				//todo
			}

			
		
		try {
			response.getWriter().println(new Gson().toJson(allNewsfeeds));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
