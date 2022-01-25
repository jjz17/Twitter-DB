import java.sql.JDBCType;
import java.util.List;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MySQLDatabaseAPI implements IDatabaseAPI {

  private DBUtils utils;

  @Override
  public void postTweet(Tweet t) {
    String sql = "INSERT INTO tweets (tweet_id, user_id, tweet_ts, tweet_text) VALUES (NULL, ?, NULL, ?)";

    int key = -1;
    try {

      // get connection and initialize statement
      Connection con = this.utils.getConnection(); // get the active connection
      PreparedStatement statement = con.prepareStatement(sql);
      statement.setInt(2, t.getUserId());
      statement.setString(4, t.getText());

      int rowsInserted = statement.executeUpdate();
      if (rowsInserted > 0) {
        System.out.println("A new user was inserted successfully!");
      }
      Statement stmt = con.createStatement();
      stmt.executeUpdate(sql);

      // Cleanup
      stmt.close();

    } catch (SQLException e) {
      System.err.println("ERROR: Could not insert record: "+sql);
      System.err.println(e.getMessage());
      e.printStackTrace();
    }
  }

  @Override
  public List<Tweet> getTimeline(Integer user_id) {
    return null;
  }

  @Override
  public List<Integer> getFollowers(Integer user_id) {
    return null;
  }

  @Override
  public List<Integer> getFollowees(Integer user_id) {
    return null;
  }

  @Override
  public List<Tweet> getTweets(Integer user_id) {
    return null;
  }

  @Override
  public void authenticate(String url, String user, String password) {

  }

  @Override
  public void closeConnection() {

  }
}
