package com.photopia.controller;

import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
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

import com.google.gson.Gson;
import com.photopia.model.Comment;
import com.photopia.model.CommentDAO;
import com.photopia.model.LikeDAO;
import com.photopia.model.Photo;
import com.photopia.model.Post;
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

		model.addAttribute("postid", new Integer(0));
		model.addAttribute("comment", new Comment());
		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;

		List<Post> allFollowingsPosts;
		try {
			allFollowingsPosts = userDAO.getAllUserFollowingsPosts(id);
			model.addAttribute("allFollowingsPosts", allFollowingsPosts);
		} catch (ClassNotFoundException | UserException | PostException | SQLException e) {
			e.printStackTrace();
			return "redirect:/profile";
		}
		return "wall";
	}

	@RequestMapping(value = "/wall", method = RequestMethod.POST)
	public void addComment(@RequestParam("postId") int postId, @RequestParam("text") String text,
			@ModelAttribute Comment comment, Model model, HttpServletRequest request, HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;
			commentDAO.addCommentToPost(postId, id, text);
		} catch (ClassNotFoundException | CommentException | SQLException | IOException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public void addLike(@RequestParam("currentPostId") int currentPostId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;

			likeDAO.addLikeToPost(id, currentPostId);
		} catch (ClassNotFoundException | PostException | SQLException | IOException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/unlike", method = RequestMethod.POST)
	public void addUnlike(@RequestParam("currentPostId") int currentPostId, Model model, HttpServletRequest request,
			HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;
			likeDAO.removeLikeFromPost(currentPostId, id);
		} catch (ClassNotFoundException | LikeException | SQLException | IOException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/numberOfLikes", method = RequestMethod.GET)
	public void getNumberOfLikes(@RequestParam("currentPostId") int currentPostId, Model model,
			HttpServletRequest request, HttpServletResponse response) {

		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			response.setContentType("text/json");
			response.setCharacterEncoding("UTF-8");

			List<Integer> likesAndComments = new LinkedList<>();

			Integer numberOfLikes = likeDAO.showNumberOfLikes(currentPostId);
			Integer numberOfComments;

			numberOfComments = commentDAO.getNumberOfComments(currentPostId);
			likesAndComments.add(numberOfLikes);
			likesAndComments.add(numberOfComments);
			response.getWriter().println(new Gson().toJson(likesAndComments));
		} catch (ClassNotFoundException | SQLException | CommentException | IOException | LikeException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
