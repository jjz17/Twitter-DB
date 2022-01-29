import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Driver {
  private static IDatabaseAPI api = new MySQLDatabaseAPI();
  // Environmental variables
  public static final String url = System.getenv("url");
  public static final String user = System.getenv("user");
  public static final String password = System.getenv("password");

  // Driver program to read tweets from tweets.csv and add them to MySQL Database
  public void readTweets() {

//    String csvFilePath = "hw1_data/tweets_sample.csv";
    String csvFilePath = "hw1_data/tweet.csv";

    try {
      BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
      String lineText = null;

      // Skip labels
      lineReader.readLine();

      while ((lineText = lineReader.readLine()) != null) {
        String[] data = lineText.split(",");
        int user_id = Integer.parseInt(data[0]);
        String tweet_text = data[1];

        Tweet tweet = new Tweet(user_id, tweet_text);

        // Insert the tweet into the database
        api.postTweet(tweet);
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  // Driver program to return a random user's home timeline
  public double randomHomeTimeline() {

    List<Integer> users = api.getUsers();

    int run_time_ms = 10000;

    int count = 0;
    long endTime = System.currentTimeMillis() + run_time_ms;
    while (System.currentTimeMillis() < endTime) {
      int random_user_id = users.get((int) (Math.random() * users.size()));
      List<Tweet> tweets = api.getTimeline(random_user_id);
      for (Tweet t : tweets) {
        System.out.println(t);
      }
      count++;
    }


    return count / (run_time_ms / 1000.0);
  }

  // Main method
  public static void main(String[] args) {
    Driver driver = new Driver();

    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    LocalDateTime start = LocalDateTime.now();
    System.out.println("Start time: " + dtf.format(start));

//    System.out.println(url + password + user);

    api.authenticate(url, user, password); // DON'T HARDCODE PASSWORDS!
//    driver.readTweets();
//    List<Integer> followees = api.getFollowees(1);
//    for (Integer i : followees) {
//      System.out.println(i);
//    }
//    List<Integer> followers = api.getFollowers(1);
//    for (Integer i : followers) {
//      System.out.println(i);
//    }
//    List<Tweet> tweets = api.getTimeline(1);
//    for (Tweet t : tweets) {
//      System.out.println(t);
//    }
    System.out.println(driver.randomHomeTimeline());
    api.closeConnection();
    LocalDateTime end = LocalDateTime.now();
    System.out.println("End time: " + dtf.format(end));
  }
}
