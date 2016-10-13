package com.photopia.model;

import com.photopia.model.exceptions.PostException;


public class Photo extends Post{

	public Photo(int ownerId) throws PostException {
		super(ownerId);
	}
	
	public Photo(int postId, String url) {
		super(postId, url);
	}
	
	public Photo(int postId, String ownerName, String url) throws PostException{
		super(postId,ownerName,url);
	}
	
	public Photo(){
		super();
	}
	
	public Photo(String ownerName, String description, String location, String url){
		super(ownerName,description,location,url);
	}

}
