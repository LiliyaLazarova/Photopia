package com.photopia.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.photopia.model.Comment;
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
	
	@RequestMapping(value = "/showPost", method = RequestMethod.GET)
	public String showUserWall(Model model, HttpServletRequest request) {
		
		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;
		String postIdFromUrl = request.getParameter("postId");
		int postId=Integer.parseInt(postIdFromUrl);
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

}
