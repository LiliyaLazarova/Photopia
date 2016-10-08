package com.photopia.model.interfaces;

import java.sql.Timestamp;

public interface IPost {
	
	public Timestamp getUploadTimestamp();
	public String getDescription();
	public String getLocation();
	public String getUrl();

}
