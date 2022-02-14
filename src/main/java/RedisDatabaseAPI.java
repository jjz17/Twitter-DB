import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisDatabaseAPI implements IDatabaseAPI {
  private Jedis jedis;

  public RedisDatabaseAPI() {
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

    // Add tweet id to every follower's timeline bucket
    List<String> followers = this.jedis.lrange("follows_" + user_id, 0 , -1);
    for (String follower : followers) {
      String timeline = "timeline_" + follower;
      // Add the tweet id to the front of the timeline
      this.jedis.lpush(timeline, tweet_id);
    }
  }

  @Override
  public List<Tweet> getTimeline(Integer user_id) {
    List<String> timeline = this.jedis.lrange("timeline_" + user_id, 0, 9);
    List<Tweet> tweets = new ArrayList<>();
    for (String tweet_id : timeline) {
      String tweet_string = this.jedis.get("tweet_" + tweet_id);
      // Parse tweet string to extract attributes, split by |
      String[] args = tweet_string.split("\\|");
      int t_id = Integer.parseInt(args[0]);
      int u_id = Integer.parseInt(args[1]);
      Timestamp timestamp = new Timestamp(Long.parseLong(args[2]));
      String text = args[3];

      // Create tweet object from attributes
      Tweet tweet = new Tweet(t_id, u_id, timestamp, text);
      // Add tweet to the list
      tweets.add(tweet);
    }
    return tweets;
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
//    List<String> followees_strings = this.jedis.lrange("followees_" + user_id, 0 ,-1);
//    List<Integer> followees = new ArrayList<>();
//    for (String follower : followees_strings) {
//      followees.add(Integer.parseInt(follower));
//    }
//    return followees;
    return null;
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
  public void authenticate(String url, String user, String password) {}

  @Override
  public void closeConnection() {}
}
