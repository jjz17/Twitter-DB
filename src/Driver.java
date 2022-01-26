import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
  private static IDatabaseAPI api = new MySQLDatabaseAPI();

//  public Driver(IDatabaseAPI api) {
//    this.api = api;
//  }
//
//  public Driver() {}

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

        api.postTweet(tweet);
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
    api.closeConnection();
  }

  // Driver program to return a random user's home timeline
  public void randomHomeTimeline() {}

  // Main method
  public static void main(String[] args) {
    Driver driver = new Driver();
    driver.readTweets();
  }
}
