import java.io.*;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Driver {
  private static IDatabaseAPI api = new MySQLDatabaseAPI();

  // Driver program to read tweets from tweets.csv and add them to MySQL Database
  public void readTweets() {
    String url = "jdbc:mysql://localhost:3306/twitter?serverTimezone=EST5EDT";
    String user = "root";
    String password = "jiajia2002";

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
    List<Integer> users = api.getUsers();

    // Running infinite loop, can maybe try using a timer library to run for 10 seconds for example
    while (true) {
    // select a random user... NEEDS TO BE IMPROVED
    int random_user_id = users.get((int) (Math.random() * users.size()));
    List<Integer> followees = api.getFollowees(random_user_id);
    // Select a random followee
    int random_followee = followees.get((int) (Math.random() * followees.size()));
    }
  }

  // Main method
  public static void main(String[] args) {
    Driver driver = new Driver();
//    driver.readTweets();
  }
}
