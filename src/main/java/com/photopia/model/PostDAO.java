package com.photopia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.photopia.model.exceptions.PostException;
import com.photopia.model.interfaces.IPost;


@Component
@ContextConfiguration(classes=DaoConfiguration.class)
public class PostDAO {

	private static final String INSERT_POST = "Insert into posts values(null,?,?,?,?,?);";
	private static final String GET_ALL_POSTS = "Select url from posts where user_id=?;";
	private static final String GET_POST_INFO = "SELECT post_id,description,location FROM posts where url=?;";
	private static final String GET_POST_COMMENTS = "select user_id,comment_text from comments where post_id=?;";
	private static final String GET_COMMENTING_USERS = "select user_name from users where user_id=?;";
	private static final String ADD_POST_INFO = "update posts set description=?,location=? where post_id=?";
	private static final String DELETE_POST = "";
	private static final String ADD_LIKE_TO_POST="INSERT into likes values(null,?,?,?);";

	public void uploadPost(int userId, IPost post) {

		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(INSERT_POST);

			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, post.getDescription());
			preparedStatement.setString(3, post.getLocation());
			preparedStatement.setString(4, post.getUrl());
			preparedStatement.setTimestamp(5, post.getUploadTimestamp());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public List<String> getAllPostsUrls(int userId) {

		Connection connection = DBConnection.getInstance().getConnection();

		// Set<Post> posts=new TreeSet<Post>(new Comparator<Post>() {
		//
		// public int compare(Post o1, Post o2) {
		// return o1.getUploadTimestamp().compareTo(o2.getUploadTimestamp());
		// }
		//
		// });
		List<String> postUrls = new LinkedList<String>();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_ALL_POSTS);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				postUrls.add(rs.getString(1));
			}

			return postUrls;

		} catch (SQLException e) {
			return new LinkedList<String>();
		}

	}

	public Post getPostInfo(String url){
		
		Connection connection = DBConnection.getInstance().getConnection();
		Post post=null;
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps=connection.prepareStatement(GET_POST_INFO);
			ps.setString(1, url);
			
			ResultSet rs=ps.executeQuery();
			rs.next();
			int postId=rs.getInt("post_id");
			String description=rs.getString("description");
			String location=rs.getString("location");
			
			PreparedStatement ps1=connection.prepareStatement(GET_POST_COMMENTS);
			ps.setInt(1, postId);
			ResultSet rs1=ps.executeQuery();
			
			LinkedList<Comment> comments=new LinkedList<Comment>();
			while (rs.next()) {
//				String text=rs.getString(columnLabel)
	//			comments.add()
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;
		
	}

	public void deletePost(String url){
		
		Connection connection=DBConnection.getInstance().getConnection();
//		PreparedStatement ps=connection.prepareStatement(sql)
	}

	public void updatePostInfo(String description,String location,int postId) throws PostException {

		Connection connection = DBConnection.getInstance().getConnection();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement(ADD_POST_INFO);

			
			preparedStatement.setString(1, description);
			preparedStatement.setString(2, location);
			preparedStatement.setInt(3, postId);
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new PostException("Updating post's info failed");
		}

	}
	
	public void addLikeToPost(int userId, int postId) throws PostException {

		Connection connection = DBConnection.getInstance().getConnection();

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

}
