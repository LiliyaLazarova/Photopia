package com.photopia.model;

import com.photopia.model.exceptions.PostException;

public class Video extends Post{

	public Video(int ownerId) throws PostException {
		super(ownerId);
	}

}
