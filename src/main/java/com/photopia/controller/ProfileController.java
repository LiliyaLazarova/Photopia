package com.photopia.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mockito.cglib.reflect.MethodDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.photopia.model.CommentDAO;
import com.photopia.model.Photo;
import com.photopia.model.Post;
import com.photopia.model.PostDAO;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;

@Controller
public class ProfileController {

	private static final String UPLOAD_LOCATION = "C:\\Lility\\User_Photos\\img\\";

	@Autowired
	UserDAO userDAO;
	@Autowired
	PostDAO postDAO;

	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String showProfile(HttpServletRequest request, Model model) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;
		try {
			int numberOfPosts = userDAO.getNumberOfPosts(id);
			int numberOfFollowers = userDAO.getNumberOfFollowers(id);
			int numberOfFollowings = userDAO.getNumberOfFollowings(id);

			IUser user = userDAO.getUserInfo(id);
			model.addAttribute("user", user);
			model.addAttribute("numberOfPosts", numberOfPosts);
			model.addAttribute("numberOfFollowers", numberOfFollowers);
			model.addAttribute("numberOfFollowings", numberOfFollowings);

			List<Post> allPosts;

			allPosts = postDAO.getAllPostsUrls(id);
			model.addAttribute("allPosts", allPosts);
			model.addAttribute("post", new Photo());
		} catch (ClassNotFoundException | SQLException | PostException | UserException e) {
			e.printStackTrace();
			return "error";
		}
		return "profile";
	}

	@RequestMapping(value = "/profile", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, ModelMap model,
			@ModelAttribute Photo photo, HttpServletRequest request) throws IOException {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;

		String[] path = multipartFile.getOriginalFilename().split(":\\\\");
		String fileName = id + "_" + path[path.length - 1];

		FileCopyUtils.copy(multipartFile.getBytes(), new File(UPLOAD_LOCATION + fileName));

		String url = id + "_" + multipartFile.getOriginalFilename();
		photo.setUrl(url);

		try {
			postDAO.uploadPost(id, photo);
		} catch (ClassNotFoundException | SQLException | PostException e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/profile";
	}

	@RequestMapping(value = "/followingsList", method = RequestMethod.GET)
	public void showFollings(HttpServletRequest request, Model model, HttpServletResponse response) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
			}
			int id = (int) userId;

			List<IUser> allFollowingsList;

			allFollowingsList = userDAO.getFollowingsList(id);
			response.getWriter().println(new Gson().toJson(allFollowingsList));
		} catch (ClassNotFoundException | UserException | SQLException | IOException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/followersList", method = RequestMethod.GET)
	public void showFollowers(HttpServletRequest request, Model model, HttpServletResponse response) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;

			List<IUser> allFollowersList;

			allFollowersList = userDAO.getFollowersList(id);
			response.getWriter().println(new Gson().toJson(allFollowersList));
		} catch (ClassNotFoundException | UserException | SQLException | IOException e) {
			e.printStackTrace();
			try {
				response.sendRedirect("./error");
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	@RequestMapping(value = "/checkUserFollower", method = RequestMethod.GET)
	public void checkFollower(@RequestParam("followerId") int followerId, HttpServletRequest request, Model model,
			HttpServletResponse response) {

		response.setContentType("text/json");
		response.setCharacterEncoding("UTF-8");
		Object userId = request.getSession().getAttribute("userID");
		try {
			if (userId == null) {
				response.sendRedirect("./index");
				return;
			}
			int id = (int) userId;

			String checked;
			checked = userDAO.checkForFollower(id, followerId);
			response.getWriter().println(new Gson().toJson(checked));
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
