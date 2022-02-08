import java.sql.Timestamp;

// Represents a tweet that is posted on Twitter
public class Tweet {

  private int tweet_id;
  private int user_id;
  private Timestamp timestamp;
  private String text;

  // Convenience constructor
  public Tweet(int user_id, String text) {
    this.user_id = user_id;
    this.text = text;
  }

  public Tweet(int tweet_id, int user_id, Timestamp timestamp, String text) {
    this.tweet_id = tweet_id;
    this.user_id = user_id;
    this.timestamp = timestamp;
    this.text = text;
  }

  @Override
  public String toString() {
    return "User: " + this.user_id + ", tweet: " + this.text + ", time: " + this.timestamp;
  }

  public int getTweetId() {
    return this.tweet_id;
  }

  public int getUserId() {
    return this.user_id;
  }

  public Timestamp getTimestamp() {
    return this.timestamp;
  }

  public String getText() {
    return this.text;
  }
}
