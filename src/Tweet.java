public class Tweet {

  private int user_id;
  private String text;

  public Tweet(int user_id, String text) {
    this.user_id = user_id;
    this.text = text;
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
