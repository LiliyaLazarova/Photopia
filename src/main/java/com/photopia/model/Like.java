package com.photopia.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.photopia.model.exceptions.LikeException;



public class Like {

	private int likeId;
	private User likeOwner;
	private Post post;
	private Timestamp timestamp;
	
	public Like(User likeOwner, Post post) throws LikeException {
		setLikeOwner(likeOwner);
		setPost(post);
	}
	public int getLikeId() {
		return likeId;
	}
	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}
	public User getLikeOwner() {
		return likeOwner;
	}
	public void setLikeOwner(User likeOwner) throws LikeException {
		if (likeOwner!=null) {
			this.likeOwner = likeOwner;
		}else {
			throw new LikeException("User doesn't exist!");
		}
		
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) throws LikeException {
		if (post!=null) {
			this.post = post;
		}else {
			throw new LikeException("Post doesn't exist!");
		}
	}
	public Timestamp getTimestamp() {
		return Timestamp.valueOf(LocalDateTime.now());
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	

}
