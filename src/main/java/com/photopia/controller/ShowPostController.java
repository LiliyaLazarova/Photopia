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

import com.photopia.model.Comment;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.photopia.model.Comment;
import com.photopia.model.CommentDAO;
import com.photopia.model.Post;
import com.photopia.model.PostDAO;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.CommentException;
import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;

@Controller
public class ShowPostController {
	
	@Autowired
	PostDAO postDAO;

	@Autowired
	CommentDAO commentDAO;
	
	@RequestMapping(value = "/showPost", method = RequestMethod.GET)
	public String showPost(Model model, HttpServletRequest request) {

		
		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;

		int postId=0;
		String postIdFromUrl = request.getParameter("postId");
		try {
			postId=Integer.parseInt(postIdFromUrl);
		} catch (Exception e) {
			return "redirect:/showPost";
		}
		

		try {
			Post post = postDAO.getInfoToShowPost(postId);
			post.setPostId(postId);
			List<String> likersNames = postDAO.getLikersNames(postId);
		
			StringBuilder names = new StringBuilder();
			for (String string : likersNames) {
				names.append(string+" ");
			}
			
			List<Comment> comments = postDAO.getCommentsForPost(postId);
		
			model.addAttribute("post", post);
			model.addAttribute("names", names);
			model.addAttribute("comments", comments);
		} catch (ClassNotFoundException | SQLException | CommentException e) {
			return "redirect:/wall";
		}
		
		return "showPost";
	}

	
	
	@RequestMapping(value = "/addComment", method = RequestMethod.POST)
	public void addComment(@RequestParam("postId") int postId,@RequestParam("text") String text, @ModelAttribute Comment comment, Model model,
			HttpServletRequest request,HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");

		int id = (int) userId;
		try {
			commentDAO.addCommentToPost(postId, id, text);
			showComments(postId, model, request, response);

		} catch (CommentException | ClassNotFoundException | SQLException e) {
			//TODO
		}

	}
	@RequestMapping(value = "/comment", method = RequestMethod.GET)
	public void showComments(@RequestParam("postId")  int currentPostId,Model model, HttpServletRequest request,HttpServletResponse response) {
		
		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		
		try {
			
			List<Comment> comments = postDAO.getCommentsForPost(currentPostId);
			response.getWriter().println(new Gson().toJson(comments));
		
		} catch (ClassNotFoundException | SQLException | CommentException | IOException e) {
			//TODO
		}

	}


}
