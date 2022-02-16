import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisDatabaseAPI2 implements IDatabaseAPI {
  private Jedis jedis;

  public RedisDatabaseAPI2() {
    this.jedis = new Jedis();

    // Set up unique tweet id counter
    this.jedis.set("next_tweet_id", "0");
  }

  @Override
  public void postTweet(Tweet t) {
    // Tweet attributes
    int user_id = t.getUserId();
    String text = t.getText();

    // Necessary values
    String tweet_id = this.jedis.get("next_tweet_id");
    long timestamp = System.currentTimeMillis();

    // Increment next_tweet_id
    this.jedis.incr("next_tweet_id");

    // Create tweet key
    String tweet_name = "tweet_" + tweet_id;

    // Create the tweet value representation
    String tweet_info = tweet_id + "|" + user_id + "|" + timestamp + "|" + text;

    // Put the tweet key, value pair into Redis server
    this.jedis.set(tweet_name, tweet_info);
  }

  @Override
  public List<Tweet> getTimeline(Integer user_id) {

    return null;
  }

  @Override
  public List<Integer> getFollowers(Integer user_id) {
    List<String> followers_strings = this.jedis.lrange("follows_" + user_id, 0 ,-1);
    List<Integer> followers = new ArrayList<>();
    for (String follower : followers_strings) {
      followers.add(Integer.parseInt(follower));
    }
    return followers;
  }

  @Override
  public List<Integer> getFollowees(Integer user_id) {
    List<String> followees_strings = this.jedis.lrange("followees_" + user_id, 0 ,-1);
    List<Integer> followees = new ArrayList<>();
    for (String follower : followees_strings) {
      followees.add(Integer.parseInt(follower));
    }
    return followees;
  }

  @Override
  public List<Integer> getUsers() {
    Set<String> user_strings = this.jedis.smembers("users");
    List<Integer> users = new ArrayList<>();
    for (String user_string : user_strings) {
      users.add(Integer.parseInt(user_string));
    }
    return users;
  }

  @Override
  public void authenticate(String url, String user, String password) {

  }

  @Override
  public void closeConnection() {

  }
}
