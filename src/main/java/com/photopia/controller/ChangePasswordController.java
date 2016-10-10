package com.photopia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.photopia.model.Photo;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;

@Controller
public class ChangePasswordController {
	
	@RequestMapping(value="/changePassword", method = RequestMethod.GET)
	public String showProfile(HttpServletRequest request,Model model) {
//		if(request.getSession() == null) {
//			return "index";
//		}
		Object userId = request.getSession().getAttribute("userID");
		if(userId == null) {	
			return "redirect:/index";
		}
		int id = (int)userId;
		
		
		
		
		return "changePassword";
	}

}
