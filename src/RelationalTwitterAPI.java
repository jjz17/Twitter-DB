import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

class RelationalTwitterAPI implements ITwitterAPI{

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
}