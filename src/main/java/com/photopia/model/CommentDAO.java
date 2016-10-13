package com.photopia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.photopia.model.exceptions.CommentException;

@Component
@ContextConfiguration(classes=DaoConfiguration.class)
public class CommentDAO {
	
	public static final String ADD_COMMENT="Insert into comments values(null,?,?,?,?)";
	
	public void addCommentToPost(int postId,int userId,String text) throws CommentException, ClassNotFoundException, SQLException {
		
		
		Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(ADD_COMMENT);
			ps.setString(1, text);
			ps.setInt(2, userId);
			ps.setInt(3, postId);
			ps.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new CommentException("Comment is not added");
		}

	}
}
