package com.photopia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.photopia.model.exceptions.LikeException;


@Component
@ContextConfiguration(classes=DaoConfiguration.class)
public class LikeDAO {
	
	private static final String GET_NUMBER_OF_LIKES="SELECT COUNT(*) FROM likes where post_id=?;";
	private static final String ADD_LIKE="Insert into likes values(null,?,?,?)";
	private static final String REMOVE_LIKE="Delete from likes where post_id=? and user_id=?;";
	
	
	public int showNumberOfLikes(int postId) throws LikeException{

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_NUMBER_OF_LIKES);
			ps.setInt(1, postId);
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new LikeException("Likes missing");
		}
		
	}
	
	public void addLikeToPost(int postId,int userId) throws LikeException{
		Connection connection = DBConnection.getInstance().getConnection();
		
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(ADD_LIKE);
			ps.setInt(1, userId);
			ps.setInt(2, postId);
			ps.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new LikeException("Like is not added");
		}

		
	}
	
	public void removeLikeFromPost(int postId,int userId) throws LikeException{
Connection connection = DBConnection.getInstance().getConnection();
		
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(REMOVE_LIKE);
			ps.setInt(1, postId);
			ps.setInt(2, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new LikeException("You have already liked this post");
		}

		
	}

}
