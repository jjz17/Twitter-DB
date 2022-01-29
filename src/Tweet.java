import java.sql.Timestamp;

public class Tweet {

  private int tweet_id;
  private int user_id;
  private String text;
  private Timestamp timestamp;

  public Tweet(int user_id, String text) {
    this.user_id = user_id;
    this.text = text;
  }

  public Tweet(int tweet_id, int user_id, String text, Timestamp timestamp) {
    this.tweet_id = tweet_id;
    this.user_id = user_id;
    this.text = text;
    this.timestamp = timestamp;
  }

  @Override
  public String toString() {
    return "User: " + this.user_id + ", tweet: " + this.text;
  }

  public int getUserId() {
    return this.user_id;
  }

  public String getText() {
    return this.text;
  }
}
