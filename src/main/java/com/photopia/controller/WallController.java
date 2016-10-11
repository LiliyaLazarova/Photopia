package com.photopia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.photopia.model.Comment;
import com.photopia.model.CommentDAO;
import com.photopia.model.Photo;
import com.photopia.model.Post;
import com.photopia.model.PostCommentRelation;
import com.photopia.model.PostDAO;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.CommentException;
import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;


@Controller
public class WallController {

	@Autowired
	UserDAO userDAO;
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	PostDAO postDAO;

	@RequestMapping(value = "/wall", method = RequestMethod.GET)
	public String showUserWall(Model model, HttpServletRequest request) {
		// if(request.getSession() == null) {
		// return "index";
		// }
		model.addAttribute("postid", new Integer(0));
		model.addAttribute("comment", new Comment());
		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;
		try {
			List<Post> allFollowingsPosts = userDAO.getAllUserFollowingsPosts(id);
			model.addAttribute("allFollowingsPosts", allFollowingsPosts);

		} catch (UserException | PostException e) {
			return "redirect:/profile";
		}
		return "wall";
	}

	@RequestMapping(value = "/wall", method = RequestMethod.POST)
	public void addComment(@RequestParam("postId") int postId,@RequestParam("text") String text, @ModelAttribute Comment comment, Model model,
			HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
//			return "redirect:/index";
		}
		int id = (int) userId;
		System.out.println("v comment sum");
		try {
			
			commentDAO.addCommentToPost(postId, id, text);
			

		} catch (CommentException e) {
//			return "redirect:/wall";
		}
		

//		return "redirect:/wall";

	}
	
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public void addLike(@RequestParam("currentPostId")  int currentPostId, @ModelAttribute Comment comment, Model model,
			HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
//			return "redirect:/index";
		}
		int id = (int) userId;
		

		
		try {
			postDAO.addLikeToPost(id, currentPostId);
		} catch (PostException e) {
			//todo
		}

//		return "redirect:/wall";

	}

}
