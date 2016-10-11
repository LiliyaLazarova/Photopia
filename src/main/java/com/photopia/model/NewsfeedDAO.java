package com.photopia.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import com.photopia.model.exceptions.NewsfeedException;

@Component
@ContextConfiguration(classes = DaoConfiguration.class)
public class NewsfeedDAO {

	private static final String GET_FOLLOWERS_NAMES_AND_TIME = "SELECT user_name,time_stamp from user_followers uf"
			+ " join users u on(uf.follower_id=u.user_id)" + " where following_id=?";

	private static final String GET_COMMENTERS_NAMES_AND_TIME = "select user_name,comment_text,time_stamp from users u"
			+ " join comments c on (u.user_id=c.user_id)" + " join posts p on(c.post_id=p.post_id)"
			+ " where p.user_id=?";

	private static final String GET_LIKERS_NAMES_AND_TIME = "select user_name,timestamp from users u"
			+ " join likes l on (u.user_id=l.user_id)"
			+ " join posts p on(l.post_id=p.post_id)"
			+ " where p.user_id=?;";

	public Set<NewsFeed> getMyFollowersNames(int currentUserId) throws NewsfeedException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();
		Set<NewsFeed> newsfeeds = new TreeSet<NewsFeed>();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_FOLLOWERS_NAMES_AND_TIME);
			ps.setInt(1, currentUserId);

			ResultSet rs = ps.executeQuery();
			// rs.next();
			while (rs.next()) {
				String name = rs.getString("user_name");
				Timestamp time = rs.getTimestamp("time_stamp");
				newsfeeds.add(new NewsFeed(name, "started following you", time));
			}

		} catch (SQLException e) {
			throw new NewsfeedException("Invalid newsfeed for followers");
		}
		
		return newsfeeds;
	}

	public Set<NewsFeed> getMyCommentersNames(int currentUserId) throws NewsfeedException {
		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();
		Set<NewsFeed> newsfeeds = new TreeSet<NewsFeed>();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_COMMENTERS_NAMES_AND_TIME);
			ps.setInt(1, currentUserId);
			ResultSet rs = ps.executeQuery();

			
			while (rs.next()) {
				String name = rs.getString("user_name");
				String text = rs.getString("comment_text");
				Timestamp time = rs.getTimestamp("time_stamp");
				newsfeeds.add(new NewsFeed(name, "commented: " + text + " your photo", time));
			}

		} catch (SQLException e) {
			throw new NewsfeedException("Invalid newsfeed for comments");
		}

		return newsfeeds;
	}

	public Set<NewsFeed> getMyLikersNames(int currentUserId) throws NewsfeedException {

		Connection connection = DBConnection.getInstance().getConnection();
		// Connection connection = new DBConnection().getConnection();
		Set<NewsFeed> newsfeeds = new TreeSet<NewsFeed>();

		try {
			PreparedStatement ps = connection.prepareStatement(GET_LIKERS_NAMES_AND_TIME);
			ps.setInt(1, currentUserId);

			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				String name = rs.getString("user_name");
				Timestamp time = rs.getTimestamp("timestamp");
				newsfeeds.add(new NewsFeed(name, "liked your photo", time));
			}

		} catch (SQLException e) {
			throw new NewsfeedException("Invalid newsfeed for likes");
		}
		
		return newsfeeds;
	}

}
