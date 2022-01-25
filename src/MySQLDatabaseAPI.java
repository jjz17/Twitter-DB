import java.sql.JDBCType;
import java.util.List;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class MySQLDatabaseAPI implements IDatabaseAPI {

  @Override
  public void postTweet(Tweet t) {

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
