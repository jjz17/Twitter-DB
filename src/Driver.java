import java.io.*;
import java.util.List;

public class Driver {
  private static IDatabaseAPI api = new MySQLDatabaseAPI();
  // Environmental variables
  public static final String url = System.getenv("url");
  public static final String user = System.getenv("user");
  public static final String password = System.getenv("password");

  // Driver program to read tweets from tweets.csv and add them to MySQL Database
  public void readTweets() {
    api.authenticate(url, user, password); // DON'T HARDCODE PASSWORDS!

    String csvFilePath = "hw1_data/tweets_sample.csv";

    try {
      BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
      String lineText = null;

      // Skip labels
      lineReader.readLine();

      while ((lineText = lineReader.readLine()) != null) {
        String[] data = lineText.split(",");
        int user_id = Integer.valueOf(data[0]);
        String tweet_text = data[1];
        System.out.println(user_id);
        System.out.println(tweet_text);

        Tweet tweet = new Tweet(user_id, tweet_text);

        // Insert the tweet into the database
        api.postTweet(tweet);
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
    api.closeConnection();
  }

  // Driver program to return a random user's home timeline
  public void randomHomeTimeline() {

    api.authenticate(url, user, password); // DON'T HARDCODE PASSWORDS!


    List<Integer> users = api.getUsers();

    // Running infinite loop, can maybe try using a timer library to run for 10 seconds for example
    while (true) {
      int random_user_id = users.get((int) (Math.random() * users.size()));
      List<Tweet> tweets = api.getTimeline(random_user_id);
      for (Tweet t : tweets) {
        System.out.println(t);
      }
    }

//    api.closeConnection();
  }

  // Main method
  public static void main(String[] args) {
    Driver driver = new Driver();

//    System.out.println(url + password + user);

    api.authenticate(url, user, password); // DON'T HARDCODE PASSWORDS!
////    driver.readTweets();
    List<Integer> followees = api.getFollowees(1);
    for (Integer i : followees) {
      System.out.println(i);
    }
//    List<Integer> followers = api.getFollowers(1);
//    for (Integer i : followers) {
//      System.out.println(i);
//    }
//    List<Tweet> tweets = api.getTimeline(1);
//    for (Tweet t : tweets) {
//      System.out.println(t);
//    }
    api.closeConnection();
  }
}
