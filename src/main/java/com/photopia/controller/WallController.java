package com.photopia.controller;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.photopia.model.LikeDAO;
import com.photopia.model.Photo;
import com.photopia.model.Post;
import com.photopia.model.PostCommentRelation;
import com.photopia.model.PostDAO;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.CommentException;
import com.photopia.model.exceptions.LikeException;
import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;


@Controller
public class WallController {

	@Autowired
	UserDAO userDAO;
	@Autowired
	CommentDAO commentDAO;
	@Autowired
	LikeDAO likeDAO;

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

		} catch (UserException | PostException | ClassNotFoundException | SQLException e) {
			return "redirect:/profile";
		}
		return "wall";
	}

	@RequestMapping(value = "/wall", method = RequestMethod.POST)
	public void addComment(@RequestParam("postId") int postId,@RequestParam("text") String text, @ModelAttribute Comment comment, Model model,
			HttpServletRequest request) {

		System.out.println("POST");
		System.out.println(text);
		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
//			return "redirect:/index";
		}
		int id = (int) userId;
		try {
			
			commentDAO.addCommentToPost(postId, id, text);
			

		} catch (CommentException | ClassNotFoundException | SQLException e) {
			
			System.out.println("v catch");
//			return "redirect:/wall";
		}
		

//		return "redirect:/wall";

	}
	
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public void addLike(@RequestParam("currentPostId")  int currentPostId, Model model,
			HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userID");
		
		int id = (int) userId;
		

		
		try {
			likeDAO.addLikeToPost(id, currentPostId);
		} catch (PostException | ClassNotFoundException | SQLException e) {
			//todo
		}


	}

	@RequestMapping(value = "/unlike", method = RequestMethod.POST)
	public void addUnlike(@RequestParam("currentPostId")  int currentPostId, Model model,
			HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userID");
		
		int id = (int) userId;
		
			try {
				likeDAO.removeLikeFromPost(currentPostId,id);
			} catch (ClassNotFoundException | LikeException | SQLException e) {
				//todo
			}
		

	}
}
