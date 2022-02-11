import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisDatabaseAPI implements IDatabaseAPI {

  private Jedis jedis = new Jedis();

  @Override
  public void postTweet(Tweet t) {
    // Tweet attributes
    int user_id = t.getUserId();
    String text = t.getText();

    // Necessary values
    String tweet_id = jedis.get("next_tweet_id");
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());


    // Create tweet key
    String tweet_name = "tweet_" + tweet_id;
    // Create the tweet value representation
    jedis.set(tweet_name, "First tweet");

    // Add tweet to every follower's timeline bucket
    List<String> followers = jedis.lrange("follows_" + user_id, 0 , -1);
    for (String follower : followers) {
      String timeline = "timeline_" + follower;
      // Add the tweet id to the front of the timeline
      jedis.lpush(tweet_id);
      // Remove last tweet if timeline is longer than 10
      if (jedis.llen(timeline) > 10) {
        jedis.rpop(timeline);
      }

    }

    jedis.rpush("timeline_1", jedis.get(tweet_name));
    for (String tweet : jedis.lrange("timeline_1", 0, 9)) {
      System.out.println(tweet);
    }
  }

  @Override
  public List<Tweet> getTimeline(Integer user_id) {
    List<String> timeline = jedis.lrange("timeline_" + user_id, 0, -1);
    List<Tweet> tweets = new ArrayList<>();
    for (String tweet_id : timeline) {
      String tweet = jedis.get("tweet_" + tweet_id);
      // Parse tweet string to extract attributes

    }
    return tweets;
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
