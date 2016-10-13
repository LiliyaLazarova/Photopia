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
import com.photopia.model.exceptions.PostException;


@Component
@ContextConfiguration(classes=DaoConfiguration.class)
public class LikeDAO {
	
	private static final String GET_NUMBER_OF_LIKES="SELECT COUNT(*) FROM likes where post_id=?;";
	private static final String ADD_LIKE_TO_POST="INSERT into likes values(null,?,?,?);";
	private static final String REMOVE_LIKE="Delete from likes where post_id=? and user_id=?;";
	
	
	public int showNumberOfLikes(int postId) throws LikeException, ClassNotFoundException, SQLException{

		 Connection connection = new DBConnection().getConnection();

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
	
	public void addLikeToPost(int userId, int postId) throws PostException, ClassNotFoundException, SQLException {

		Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_LIKE_TO_POST);

			preparedStatement.setInt(1, userId);
			preparedStatement.setInt(2, postId);
			Timestamp time = Timestamp.valueOf(LocalDateTime.now());
			preparedStatement.setTimestamp(3, time);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new PostException("Invalid like to post");
		}

	}

	
	public void removeLikeFromPost(int postId,int userId) throws LikeException, ClassNotFoundException, SQLException{
		
		 Connection connection = new DBConnection().getConnection();

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
