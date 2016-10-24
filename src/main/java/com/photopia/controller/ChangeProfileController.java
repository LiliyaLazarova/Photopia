package com.photopia.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

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

import com.photopia.model.Photo;
import com.photopia.model.PostDAO;
import com.photopia.model.User;
import com.photopia.model.UserDAO;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;

@Controller
public class ChangeProfileController {

	@Autowired
	UserDAO userDAO;
	@Autowired
	PostDAO postDAO;

	private static final String UPLOAD_LOCATION = "C:\\Lility\\User_Photos\\img\\";

	@RequestMapping(value = "/changeProfile", method = RequestMethod.GET)
	public String showChangeProfileMenu(HttpServletRequest request, Model model) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;

		IUser user;
		try {
			user = userDAO.getUserInfo(id);
			model.addAttribute("user", user);
		} catch (ClassNotFoundException | UserException | SQLException e) {
			e.printStackTrace();
			return "error";
		}
		return "changeProfile";
	}

	@RequestMapping(value = "/changeProfile", method = RequestMethod.POST)
	public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile, ModelMap model,
			@ModelAttribute User user, HttpServletRequest request) {

		Object userId = request.getSession().getAttribute("userID");
		if (userId == null) {
			return "redirect:/index";
		}
		int id = (int) userId;

		String[] path = multipartFile.getOriginalFilename().split(":\\\\");
		String fileName = id + "_" + path[path.length - 1];

		try {
			FileCopyUtils.copy(multipartFile.getBytes(), new File(UPLOAD_LOCATION + fileName));
			String url = id + "_" + multipartFile.getOriginalFilename();
			if (!url.endsWith("_")) {
				user.setProfilePhotoUrl(url);
			}
			userDAO.changeProfileInfo(user);
		} catch (IOException | ClassNotFoundException | UserException | SQLException e) {
			e.printStackTrace();
			return "error";
		}
		return "redirect:/profile";
	}
}
