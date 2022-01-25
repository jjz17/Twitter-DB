public class Tweet {
  private int user_id;
  private String text;

  public Tweet(int user_id, String text) {
    this.user_id = user_id;
    this.text = text;
  }

  public int getUserId() {
    return this.user_id;
  }

  public String getText() {
    return this.text;
  }
}
