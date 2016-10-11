package com.photopia.model;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.naming.ldap.Rdn;
import javax.swing.Spring;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.photopia.model.exceptions.PostException;
import com.photopia.model.exceptions.UserException;
import com.photopia.model.interfaces.IUser;
@Component
@ContextConfiguration(classes=DaoConfiguration.class)
public class UserDAO {

	private static final String INSERT_USER_SQL = "INSERT INTO users VALUES (null, ?, md5(?),?,null,null,null,null,null)";
	private static final String FIND_USER = "SELECT user_id FROM users WHERE email=? and password=md5(?)";
	private static final String ADD_PROFILE_PHOTO = "update users set profile_photo=? where user_id=?;";
	private static final String DELETE_PROFILE_PHOTO = "update users set profile_photo=null where user_id=?;";
	private static final String GET_PROFILE_PHOTO = "select profile_photo from users where user_id=?;";
	private static final String GET_PASSWORD = "select password from users where user_id=?";
	private static final String CHANGE_PASSWORD = "update users set password=md5(?)where user_id=? and password=md5(?);";
	private static final String CHANGE_PROFILE_INFO = "update users set name=?,gender=?,biography=?,website=?,profile_photo=? where user_id=?;";
	private static final String GET_PROFILE_INFO = "select * from users where user_id=?";
	private static final String GET_NUMBER_OF_POSTS = "select count(user_id) from posts where user_id=?";
	private static final String GET_NUMBER_OF_FOLLOWINGS = "select count(following_id) from user_followers where follower_id=?;";
	private static final String GET_NUMBER_OF_FOLLOWERS = "select count(follower_id) from user_followers where following_id=?;";
	private static final String GET_USER_FOLLOWINGS_POSTS = "select post_id,user_name,url from user_followers uf"
			+ " join posts p on(uf.following_id= p.user_id)" + " join users u on(p.user_id=u.user_id)"
			+ " where uf.follower_id=?" + " order by p.upload_timestamp DESC;";
	private static final String GET_ALL_UNFOLLOWED_USERS_FOR_CURRENT_USER = "SELECT u.user_id,u.user_name,u.profile_photo FROM users u WHERE"
			+ " NOT EXISTS"
			+ " (SELECT uf.following_id FROM user_followers uf"
			+" WHERE u.user_id = uf.following_id AND uf.follower_id = ?)"
			+" AND u.user_id <> ?"
			+" ORDER by u.user_id;";
	
	private static final String ADD_FOLLOWER = "insert into user_followers values(?,?,?);";

	
	
	public int registerUser(IUser user) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(INSERT_USER_SQL, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			rs.next();
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new UserException("User registration failed!");
		}

	}

	public int loginUser(IUser user) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(FIND_USER);
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());

			ResultSet rs = ps.executeQuery();
			rs.next();
			//
			// if (rs.next() == false) {
			// throw new UserException("User login failed");
			// }
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new UserException("User doesn't exist!");
		}
	}

	public void setProfilePhoto(int userId, String photoUrl) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(ADD_PROFILE_PHOTO);
			ps.setString(1, photoUrl);
			ps.setInt(2, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new UserException("Photo upload failed!");
		}
	}

	public void deleteProfilePhoto(int userId) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(DELETE_PROFILE_PHOTO);
			ps.setInt(1, userId);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new UserException("Profile photo deleting failed!");
		}
	}

	public String showProfilePhoto(int userId) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(GET_PROFILE_PHOTO);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			return rs.getString(1);

		} catch (SQLException e) {
			throw new UserException("Failed to show profile photo!");
		}
	}

	public void changePassword(String newPassword, int userId) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			// connection.setAutoCommit(false);
			PreparedStatement ps = connection.prepareStatement(GET_PASSWORD);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			String oldPassword = rs.getString(1);

			ps = connection.prepareStatement(CHANGE_PASSWORD);
			ps.setString(1, newPassword);
			ps.setInt(2, userId);
			ps.setString(3, oldPassword);

		} catch (SQLException e) {
			throw new UserException("Failed to show profile photo!");
		}

	}

	public int getNumberOfPosts(int userId) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_NUMBER_OF_POSTS);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			//
			// if (rs.next() == false) {
			// throw new UserException("User login failed");
			// }
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new UserException("Posts!");
		}
	}

	public int getNumberOfFollowers(int userId) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_NUMBER_OF_FOLLOWERS);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			//
			// if (rs.next() == false) {
			// throw new UserException("User login failed");
			// }
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new UserException("Followers!");
		}
	}

	public int getNumberOfFollowings(int userId) throws UserException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_NUMBER_OF_FOLLOWINGS);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			rs.next();
			//
			// if (rs.next() == false) {
			// throw new UserException("User login failed");
			// }
			return rs.getInt(1);

		} catch (SQLException e) {
			throw new UserException("Followings!");
		}
	}

	public IUser getUserInfo(int userId) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_PROFILE_INFO);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			rs.next();

			
			String username = rs.getString("user_name");
			String password = rs.getString("password");
			String email = rs.getString("email");
			String name = rs.getString("name");
			String gender = rs.getString("gender");
			String biography = rs.getString("biography");
			String website = rs.getString("website");
			String profilePhotoUrl = rs.getString("profile_photo");

			//
			// if (rs.next() == false) {
			// throw new UserException("User login failed");
			// }
			return new User(userId, username, password, email, name, website, gender, biography, profilePhotoUrl);

		} catch (SQLException e) {
			throw new UserException("Get user Info failed!");
		}
	}

	public List<Post> getAllUserFollowingsPosts(int userId) throws UserException, PostException {
		Connection connection = DBConnection.getInstance().getConnection();

		List<Post> allFollowingsPosts = new LinkedList<Post>();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_USER_FOLLOWINGS_POSTS);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			//rs.next();
			while (rs.next()) {
				int postId = rs.getInt("post_id");
				String ownerName = rs.getString("user_name");
				String url = rs.getString("url");
				allFollowingsPosts.add(new Photo(postId, ownerName, url));
			}

			return allFollowingsPosts;

		} catch (SQLException e) {
			throw new UserException("No photo available");
		}
	}

	public List<IUser> getAllUserFollowers(int currentUserId) throws UserException {
		Connection connection = DBConnection.getInstance().getConnection();

		List<IUser> allUserFollowers = new LinkedList<IUser>();
		// Connection connection = new DBConnection().getConnection();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_ALL_UNFOLLOWED_USERS_FOR_CURRENT_USER);
			ps.setInt(1, currentUserId);
			ps.setInt(2, currentUserId);

			ResultSet rs = ps.executeQuery();
			 rs.next();
			while (rs.next()) {

				int userId = rs.getInt("user_id");
				String name = rs.getString("user_name");
				String url = rs.getString("profile_photo");
				allUserFollowers.add(new User(userId, name, url));
			}
			return allUserFollowers;

		} catch (SQLException e) {
			throw new UserException("No follower available");
		}
	}
	
	
	public void followUser(int currentUserId,int followingUserId) throws UserException  {
		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {
			Timestamp time =Timestamp.valueOf(LocalDateTime.now());
			PreparedStatement ps = connection.prepareStatement(ADD_FOLLOWER);
			ps.setInt(1, followingUserId);
			ps.setInt(2, currentUserId);
			ps.setTimestamp(3, time);
			
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new UserException("Following failed!");
		}

	
	}
	
	public void changeProfileInfo(IUser user) throws UserException {
		
		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();

		try {

			PreparedStatement ps = connection.prepareStatement(CHANGE_PROFILE_INFO);
			ps.setString(1, user.getName());
			ps.setString(2, user.getGender());
			ps.setString(3, user.getBiography());
			ps.setString(4, user.getWebsite());
			ps.setString(5, user.getProfilePhotoUrl());
			ps.setInt(6, user.getUserId());
			ps.executeUpdate();

		} catch (SQLException e) {
			throw new UserException("Cannot change profile info for this user");
		}
		
	}
}
