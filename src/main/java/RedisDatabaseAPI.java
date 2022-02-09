import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisDatabaseAPI implements IDatabaseAPI {

  private Jedis jedis = new Jedis();

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
