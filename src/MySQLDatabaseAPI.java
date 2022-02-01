import java.util.ArrayList;
import java.util.List;

import java.sql.*;

// MySQL implementation of IDatabaseAPI
public class MySQLDatabaseAPI implements IDatabaseAPI {

  private DBUtils dbUtils;

  @Override
  public void postTweet(Tweet t) {
    String sql =
        "INSERT INTO tweets (user_id, tweet_text) VALUES"
            + "("
            + t.getUserId()
            + ", \""
            + t.getText()
            + "\")";
    dbUtils.insertOneRecord(sql);
  }

  @Override
  public List<Tweet> getTimeline(Integer user_id) {
    List<Integer> followees = this.getFollowees(user_id);
    // Select a random followee
    int random_followee = followees.get((int) (Math.random() * followees.size()));

    return this.getTweets(random_followee);
  }

  @Override
  public List<Integer> getFollowers(Integer user_id) {
    String sql = "SELECT user_id " + "FROM follows " + "WHERE follows_id = " + user_id;

    List<Integer> followers = new ArrayList<>();

    try {
      // get connection and initialize statement
      Connection con = this.dbUtils.getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        followers.add(rs.getInt("user_id"));
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    return followers;
  }

  @Override
  public List<Integer> getFollowees(Integer user_id) {
    // select follows_id from follows where user_id = user_id
    String sql = "SELECT follows_id " + "FROM follows " + "WHERE user_id = " + user_id;

    List<Integer> followees = new ArrayList<>();

    try {
      // get connection and initialize statement
      Connection con = this.dbUtils.getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        followees.add(rs.getInt("follows_id"));
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    return followees;
  }

  @Override
  public List<Tweet> getTweets(Integer user_id) {

    // Select the full tweet (tweet id, user id, timestamp, and text)
    String sql = "SELECT * " + "FROM tweets " + "WHERE user_id = " + user_id + " LIMIT 10";

    List<Tweet> tweets = new ArrayList<>();

    try {
      // get connection and initialize statement
      Connection con = this.dbUtils.getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        tweets.add(
            new Tweet(
                rs.getInt("tweet_id"),
                user_id,
                rs.getTimestamp("tweet_ts"),
                rs.getString("tweet_text")));
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    return tweets;
  }

  @Override
  public List<Integer> getUsers() {
    String sql = "SELECT DISTINCT(user_id) " + "FROM follows";

    List<Integer> users = new ArrayList<>();

    try {
      // get connection and initialize statement
      Connection con = this.dbUtils.getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next()) {
        users.add(rs.getInt("user_id"));
      }
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    return users;
  }

  @Override
  public void authenticate(String url, String user, String password) {
    this.dbUtils = new DBUtils(url, user, password);
  }

  @Override
  public void closeConnection() {
    this.dbUtils.closeConnection();
  }
}
