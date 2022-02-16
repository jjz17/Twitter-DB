import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

import redis.clients.jedis.Jedis;

// Driver class to run performance testing
public class Driver {
  private IDatabaseAPI api;
  // Environmental variables
  public static final String url = System.getenv("url");
  public static final String user = System.getenv("user");
  public static final String password = System.getenv("password");

  public Driver(IDatabaseAPI api) {
    this.api = api;
  }

  // Read tweets from tweets.csv and posts them (inserts them into database)
  public void readTweets() {
    String csvFilePath = "data/tweet.csv";
//    String csvFilePath = "data/tweets_sample.csv";
    try {
      BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
      String lineText = null;

      // Skip first line of labels
      lineReader.readLine();

      while ((lineText = lineReader.readLine()) != null) {
        String[] data = lineText.split(",");
        int user_id = Integer.parseInt(data[0]);
        String tweet_text = data[1];

        // Create the tweet object
        Tweet tweet = new Tweet(user_id, tweet_text);

        // Insert the tweet into the database
        api.postTweet(tweet);
      }
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  // Retrieves random home timelines for the given number of milliseconds and returns average
  // # retrieved/second
  public double randomHomeTimeline(int run_time_ms) {
    List<Integer> users = api.getUsers();
    int count = 0;
    long endTime = System.currentTimeMillis() + run_time_ms;

    while (System.currentTimeMillis() < endTime) {
      int random_user_id = users.get((int) (Math.random() * users.size()));
      // Retrieve timeline (list of tweets)
      List<Tweet> tweets = api.getTimeline(random_user_id);

//       Checks if tweet objects are being returned correctly
//      for (Tweet tweet : tweets) {
//        System.out.println(tweet);
//      }

      count++;
    }

    // Return average retrieval rate per second
    return count / (run_time_ms / 1000.0);
  }

  public static void redisImportFollows() {
    //     Jedis driver
    Jedis jedis = new Jedis();

    jedis.flushAll();

    // Load the followers information into redis server
//    String csvFilePath = "data/follows_sample.csv";
    String csvFilePath = "data/follows.csv";

    try {
      BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
      String lineText = null;

      // Skip first line of labels
      lineReader.readLine();

      while ((lineText = lineReader.readLine()) != null) {
        String[] data = lineText.split(",");
        int user_id = Integer.parseInt(data[0]);
        int follows_id = Integer.parseInt(data[1]);

        // Create the follows lists
        String follows_list_key = "follows_" + follows_id;
        // Add the following user to the list
        jedis.rpush(follows_list_key, "" + user_id);

        // Create the followees lists
        String followees_list_key = "followees_" + user_id;
        // Add the followed user to the list
        jedis.rpush(followees_list_key, "" + follows_id);

        // Add user to set of users
        jedis.sadd("users", "" + user_id);
      }
    } catch (IOException ex) {
      System.err.println(ex.getMessage());
    }
  }

  public static void MySQLTest() {
    // Driver code setup to performance test MySQL API
    Driver driver = new Driver(new MySQLDatabaseAPI());
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    driver.api.authenticate(url, user, password);

    // Performance test starting dialogue
    System.out.println("Starting performance test...");

    LocalDateTime start = LocalDateTime.now();

//    driver.readTweets();
    double retrieval_rate = driver.randomHomeTimeline(1000);

    LocalDateTime end = LocalDateTime.now();
    driver.api.closeConnection();

    // Log start and end time of performance test
    System.out.println("Start time: " + dtf.format(start));
    System.out.println("End time: " + dtf.format(end));
    double total_runtime_seconds = start.until(end, ChronoUnit.SECONDS);
    System.out.println("Total runtime: " + total_runtime_seconds + " seconds");

    // Output for profiling rate of tweet posting
//    System.out.println("Average posts/second: " + 1000000.0/total_runtime_seconds);

    // Output for profiling rate of home timeline retrieval
    System.out.println("Average home timelines retrieved/second: " + retrieval_rate);
  }

  public static void RedisTest() {
    // Import follows information into redis
//    redisImportFollows();

    Driver driver = new Driver(new RedisDatabaseAPI());

    long startTime = System.nanoTime();

//    driver.readTweets();
    double retrievalRate = driver.randomHomeTimeline(120000);

    long endTime = System.nanoTime();
    // Duration in milliseconds
    long duration = (endTime - startTime) / 1000000;
//    System.out.println((float) 1000000 * 1000.0 / duration + " record inserts per second");
    System.out.println("Average home timelines retrieved/second: " + retrievalRate);
    System.out.println(duration);
  }

  // Main method
  public static void main(String[] args) {
//    MySQLTest();
    RedisTest();
  }
}
