package com.photopia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.CommentException;
import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;


@Controller
public class WallController {

	@RequestMapping(value="/wall", method = RequestMethod.GET)
	public String showUserWall(Model model,HttpServletRequest request) {
		System.out.println(request.getSession());
//		if(request.getSession() == null) {
//			return "index";
//		}
		model.addAttribute("postid", new Integer(0));
		model.addAttribute("comment",new Comment());
		Object userId = request.getSession().getAttribute("userID");
		if(userId == null) {	
			return "redirect:/index";
		}
		int id = (int)userId;
		try {
			List<Post> allFollowingsPosts=new UserDAO().getAllUserFollowingsPosts(id);
			System.out.println(allFollowingsPosts);
			model.addAttribute("allFollowingsPosts",allFollowingsPosts);
			for (Post post : allFollowingsPosts) {
				System.out.println(post.getUrl());
			}
		} catch (UserException | PostException e) {
			return "redirect:/profile";
		}
		return "wall";
	}
	
	@RequestMapping(value="/wall", method = RequestMethod.POST)
	public String login(@RequestParam Integer postId, @ModelAttribute Comment comment,Model model,HttpServletRequest request) {
		CommentDAO commentDAO = new CommentDAO();
		
			Object userId = request.getSession().getAttribute("userID");
			if(userId == null) {	
				return "redirect:/index";
			}
			int id = (int)userId;
			
			System.out.println(postId);
			String text = comment.getText();
			int commentPostId=postId;
			try {
				commentDAO.addCommentToPost(commentPostId, id, text);
				
			} catch (CommentException e) {
				return "redirect:/wall";
			}
			
		
	
		return "redirect:/wall";
		
	}
	
	
}
