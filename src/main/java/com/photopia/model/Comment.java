package com.photopia.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.photopia.model.exceptions.CommentException;


public class Comment {
	
	private int commentId;
	private String text;
	private String commentOwner;
	private Post post;
	private Timestamp timestamp;
	
	
	public Comment(String text, String commentOwnerName) throws CommentException {
		setText(text);
		setCommentOwner(commentOwnerName);
		
	}
	public Comment() {
	}
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) throws CommentException {
		if (isValidString(text)) {
			this.text=text;
		}else {
			throw new CommentException("Text is empty.");
		}
	}
	public String getCommentOwner() {
		return commentOwner;
	}
	
	public void setCommentOwner(String commentOwner) throws CommentException {
		
		if (commentOwner!=null) {
			this.commentOwner=commentOwner;
		}else {
			throw new CommentException("No valid user!");
		}
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) throws CommentException {
		if (post!=null) {
			this.post = post;
		}else {
			throw new CommentException("Post doesn't exist.");
		}
		
	}
	public Timestamp getTimestamp() {
		timestamp=timestamp.valueOf(LocalDateTime.now());
		return timestamp.valueOf(LocalDateTime.now());
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	private boolean isValidString(String text) {
		return (text != null && !text.equals(""));
	}
	

}
