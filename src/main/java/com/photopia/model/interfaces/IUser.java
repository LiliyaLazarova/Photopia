package com.photopia.model.interfaces;

public interface IUser {
	
	public String getName();
	public String getUsername();
	public int getUserId();
	public String getPassword();
	public String getEmail();
	public String getGender();
	public String getBiography();
	public String getWebsite();
	public void setProfilePhotoUrl(String url);
	public String getProfilePhotoUrl();

}
