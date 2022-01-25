import java.io.*;
import java.util.Scanner;

public class Driver {
  private ITwitterAPI api;

  public Driver(ITwitterAPI api) {
    this.api = api;
  }

  public Driver() {}

  // Driver program to read tweets from tweets.csv and add them to MySQL Database
  public void readTweets() throws FileNotFoundException {
    //parsing a CSV file into Scanner class constructor
    Scanner sc = new Scanner(new File("hw1_data/tweets_sample.csv"));
//    Run line below when testing is done
//    Scanner sc = new Scanner(new File("hw1_data/tweets.csv"));
    sc.useDelimiter(",");   //sets the delimiter pattern
    while (sc.hasNext())  //returns a boolean value
    {
      System.out.print(sc.next());  //find and returns the next complete token from this scanner
    }
    sc.close();  //closes the scanner
  }

  // Driver program to return a random user's home timeline
  public void randomHomeTimeline() {

  }

  // Main method
  public static void main(String[] args) throws FileNotFoundException {
    Driver testDriver = new Driver();
    testDriver.readTweets();
  }
}
