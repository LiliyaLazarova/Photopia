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

import com.photopia.model.exceptions.CommentException;
import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IPost;
import com.photopia.model.interfaces.IUser;

@Component
@ContextConfiguration(classes = DaoConfiguration.class)
public class PostDAO {

	private static final String INSERT_POST = "Insert into posts values(null,?,?,?,?,?);";
	private static final String GET_ALL_POSTS = "Select post_id,url from posts where user_id=?;";
	private static final String GET_POST_INFO = "SELECT post_id,description,location FROM posts where url=?;";
	private static final String GET_POST_COMMENTS = "select user_id,comment_text from comments where post_id=?;";
	private static final String GET_COMMENTING_USERS = "select user_name from users where user_id=?;";
	private static final String ADD_POST_INFO = "update posts set description=?,location=? where post_id=?";
	private static final String DELETE_POST = "";
	private static final String GET_INFO_TO_SHOW_POST = "SELECT user_name,description,location,url from posts p"
			+ " join users u on(p.user_id=u.user_id)" + " where p.post_id=?";
	private static final String GET_LIKERS_NAMES = "select user_name from users u join likes l on(u.user_id=l.user_id)"
			+ " where post_id=?";
	private static final String GET_ALL_COMMENTS_FOR_POST = "select user_name,comment_text from comments c"
			+ " join users u on(c.user_id=u.user_id)" 
			+ " where post_id=?"
			+ " order by c.time_stamp;";

	public void uploadPost(int userId, IPost post) throws ClassNotFoundException, SQLException {

		Connection connection = new DBConnection().getConnection();

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

	public List<Post> getAllPostsUrls(int userId) throws ClassNotFoundException, SQLException {

		Connection connection = new DBConnection().getConnection();

		// Set<Post> posts=new TreeSet<Post>(new Comparator<Post>() {
		//
		// public int compare(Post o1, Post o2) {
		// return o1.getUploadTimestamp().compareTo(o2.getUploadTimestamp());
		// }
		//
		// });
		List<Post> posts = new LinkedList<Post>();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_ALL_POSTS);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int postId = rs.getInt("post_id");
				String url = rs.getString("url");
				posts.add(new Photo(postId, url));
			}

			return posts;

		} catch (SQLException e) {
			return new LinkedList<Post>();
		}

	}

	public Post getPostInfo(String url) throws ClassNotFoundException, SQLException {

		Connection connection = new DBConnection().getConnection();

		Post post = null;
		try {
			connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(GET_POST_INFO);
			ps.setString(1, url);

			ResultSet rs = ps.executeQuery();
			rs.next();
			int postId = rs.getInt("post_id");
			String description = rs.getString("description");
			String location = rs.getString("location");

			PreparedStatement ps1 = connection.prepareStatement(GET_POST_COMMENTS);
			ps.setInt(1, postId);
			ResultSet rs1 = ps.executeQuery();

			LinkedList<Comment> comments = new LinkedList<Comment>();
			while (rs.next()) {
				// String text=rs.getString(columnLabel)
				// comments.add()
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return post;

	}

	public void deletePost(String url) throws ClassNotFoundException, SQLException {

		Connection connection = new DBConnection().getConnection();
		// PreparedStatement ps=connection.prepareStatement(sql)
	}

	public void updatePostInfo(String description, String location, int postId)
			throws PostException, ClassNotFoundException, SQLException {

		Connection connection = new DBConnection().getConnection();

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

	public Post getInfoToShowPost(int postId) throws ClassNotFoundException, SQLException {
		Connection connection = new DBConnection().getConnection();

		PreparedStatement ps = connection.prepareStatement(GET_INFO_TO_SHOW_POST);
		ps.setInt(1, postId);

		ResultSet rs = ps.executeQuery();
		rs.next();

		String ownerName = rs.getString("user_name");
		String description = rs.getString("description");
		String location = rs.getString("location");
		String url = rs.getString("url");

		//
		// if (rs.next() == false) {
		// throw new UserException("User login failed");
		// }
		return new Photo(ownerName, description, location, url);

	}

	public List<String> getLikersNames(int postId) throws ClassNotFoundException, SQLException {
		Connection connection = new DBConnection().getConnection();

		List<String> names = new LinkedList<>();
		PreparedStatement ps = connection.prepareStatement(GET_LIKERS_NAMES);
		ps.setInt(1, postId);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			String likerName = rs.getString("user_name");
			names.add(likerName);
		}

		//
		// if (rs.next() == false) {
		// throw new UserException("User login failed");
		// }
		return names;

	}
	
	public List<Comment> getCommentsForPost(int postId) throws ClassNotFoundException, SQLException, CommentException {
		Connection connection = new DBConnection().getConnection();

		List<Comment> comments = new LinkedList<Comment>();
		PreparedStatement ps = connection.prepareStatement(GET_ALL_COMMENTS_FOR_POST);
		ps.setInt(1, postId);

		ResultSet rs = ps.executeQuery();
		while (rs.next()) {

			String commentOwner = rs.getString("user_name");
			String text = rs.getString("comment_text");
			
			comments.add(new Comment(text, commentOwner));
		
		}

		return comments;

	}

}
