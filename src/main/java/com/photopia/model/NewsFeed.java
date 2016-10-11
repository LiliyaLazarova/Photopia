package com.photopia.model;

import java.sql.Timestamp;
import java.util.Comparator;

public class NewsFeed implements Comparable<NewsFeed>{
	private String userName;
	private String message;
	private Timestamp time;

	

	public NewsFeed(String userName,String message,Timestamp time) {
		this.userName=userName;
		this.message = message;
		this.time=time;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "NewsFeed [userName=" + userName + ", message=" + message + ", time=" + time + "]";
	}

	@Override
	public int compareTo(NewsFeed o) {
	
		return this.time.compareTo(o.time);
	}



	

}
