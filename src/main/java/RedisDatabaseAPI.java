import java.util.List;

public class RedisDatabaseAPI implements IDatabaseAPI {
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
  public List<Tweet> getMostRecentTweets(Integer user_id) {
    return null;
  }

  @Override
  public List<Integer> getUsers() {
    return null;
  }

  @Override
  public void authenticate(String url, String user, String password) {

  }

  @Override
  public void closeConnection() {

  }
}
