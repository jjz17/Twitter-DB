public class Tweet {
  private int userId;
  private String text;

  public Tweet(int userId, String text) {
    this.userId = userId;
    this.text = text;
  }

  public int getUserId() {
    return userId;
  }

  public String getText() {
    return this.text;
  }
}
