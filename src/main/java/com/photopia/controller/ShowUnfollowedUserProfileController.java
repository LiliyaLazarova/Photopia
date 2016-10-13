package com.photopia.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.photopia.model.Photo;
import com.photopia.model.Post;
import com.photopia.model.PostDAO;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;

@Controller
public class ShowUnfollowedUserProfileController {
	
	@Autowired
	UserDAO userDAO;
	@Autowired
	PostDAO postDAO;
	
	
	@RequestMapping(value="/showUnfollowedUserProfile", method = RequestMethod.GET)
	public String showUnfollowedProfile(@RequestParam("userId") int id,HttpServletRequest request,Model model) {

		
		try {
			int numberOfPosts=userDAO.getNumberOfPosts(id);
			int numberOfFollowers=userDAO.getNumberOfFollowers(id);
			int numberOfFollowings=userDAO.getNumberOfFollowings(id);
			
			IUser user=userDAO.getUserInfo(id);
			model.addAttribute("user", user);
			model.addAttribute("numberOfPosts", numberOfPosts);
			model.addAttribute("numberOfFollowers", numberOfFollowers);
			model.addAttribute("numberOfFollowings", numberOfFollowings);
			
			List<Post> allPosts=postDAO.getAllPostsUrls(id);
			model.addAttribute("allPosts",allPosts);
			model.addAttribute("post",new Photo());
			
		} catch (UserException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return "showUnfollowedUserProfile";
	}

}
