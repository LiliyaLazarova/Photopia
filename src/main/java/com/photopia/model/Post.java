package com.photopia.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.photopia.model.exceptions.PostException;
import com.photopia.model.interfaces.IPost;

public abstract class Post implements IPost {

	private int postId;
	private int ownerId;
	private String ownerName;
	private String description;
	private String location;
	private String url;
	private Timestamp uploadTimestamp;
	private List<Comment> comments = Collections.synchronizedList(new LinkedList<Comment>());
	private AtomicInteger numberOfLikes = new AtomicInteger(0);

	public Post(){
		
	}
	
	public Post(int ownerId) throws PostException {
		if (ownerId != 0) {
			this.ownerId = ownerId;
		} else {
			throw new PostException("User doesn't exist");
		}
	}

	
	public Post(int postId, String ownerName, String url) throws PostException {
		if (isValidString(url) && isValidString(ownerName)) {
			this.ownerName = ownerName;
			this.url = url;
		} else {
			throw new PostException("No valid url!");
		}
		if (postId > 0) {
			this.postId = postId;
		} else {
			throw new PostException("No valid postId!");
		}
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public int getPostOwner() {
		return ownerId;
	}

	public void setPostOwner(int ownerId) {
		this.ownerId = ownerId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		String str = url.substring(url.indexOf('_'), url.length()-1);
		if(str.equals("")) {
			this.url=null;
		}else {
			this.url=url;
		}
				
	}

	public Timestamp getUploadTimestamp() {
		return Timestamp.valueOf(LocalDateTime.now());
	}

	public void setUploadTimestamp(Timestamp uploadTimestamp) {
		this.uploadTimestamp = uploadTimestamp;
	}

	private boolean isValidString(String text) {
		return (text != null && !text.equals(""));
	}
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

}
