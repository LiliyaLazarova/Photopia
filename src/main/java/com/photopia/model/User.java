package com.photopia.model;

import java.util.LinkedList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;



public class User implements IUser {

	private static final int PASSWORD_MIN_LENGTH = 3;
	private int userId;
	@NotNull
	private String username;
	@NotNull
	private String password;
	@Email
	@NotNull
	private String email;
	private String name;
	private String website;
	private String gender;
	private String biography;
	private String profilePhotoUrl;

	private List<Post> userPosts=new LinkedList<Post>();

	public User(String username, String password, String email) throws UserException {
		this(password, email);
		setUsername(username);

	}
	
	public User(int userId, String username, String password, String email, String name, String website, String gender,
			String biography, String profilePhotoUrl) throws UserException {
		super();
		this.userId = userId;
		setUsername(username);
		setPassword(password);
		setEmail(email);
		this.name = name;
		this.website = website;
		this.gender = gender;
		this.biography = biography;
		this.profilePhotoUrl = profilePhotoUrl;
	}

	public User(String password, String email) throws UserException {
		
		setPassword(password);
		setEmail(email);
	}

	public User() {
		
	}
	public User(int userId, String name, String url) throws UserException {
		setUserId(userId);
		setUsername(name);
		setProfilePhotoUrl(url);
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	
//	public void setUsername(String username){
//		this.username=username;
//	}

	public void setUsername(String username) throws UserException {
		if (isValidString(username)) {
			this.username = username;
		} else {
			throw new UserException("Invalid username");
		}

	}

	public String getPassword() {
		return password;
	}
	
	
//public void setPassword(String password){
//	this.password=password;
//}
	public void setPassword(String password) throws UserException {
		if (isValidString(password)&&password.length()>PASSWORD_MIN_LENGTH) {
			this.password = password;
		} else {
			throw new UserException("Invalid password");
		}
	}

	public String getEmail() {
		return email;
	}

	// public void setEmail(String email){
	// this.email=email;
	// }
	
	public void setEmail(String email) throws UserException {
		if (isValidEmail(email)) {
			this.email = email;
		}else {
			throw new UserException("Invalid email!");
		}
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBiography() {
		return biography;
	}

	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public String getProfilePhotoUrl() {
		return profilePhotoUrl;
	}
	public void setProfilePhotoUrl(String profilePhotoUrl) {
		this.profilePhotoUrl = profilePhotoUrl;
	}
	

	private boolean isValidString(String text) {
		return (text != null && !text.equals(""));
	}
	
	public boolean isValidEmail(String email) {

		if (isValidString(email)) {
			String regexEmail = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
			boolean isMatch = email.matches(regexEmail);
			return isMatch;
		} else {
			return false;
		}

	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", name=" + name + ", website=" + website + ", gender=" + gender + ", biography=" + biography
				+ ", profilePhotoUrl=" + profilePhotoUrl + ", userPosts=" + userPosts + "]";
	}
	
	
}
