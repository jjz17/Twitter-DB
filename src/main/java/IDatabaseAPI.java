import java.util.List;

// Interface for Database APIs
public interface IDatabaseAPI {

  /**
   * Post one tweet
   * @param t tweet to post
   */
  void postTweet(Tweet t);

  /**
   * Retrieve the timeline of a given user
   * @param user_id target user
   * @return the user's home timeline
   */
  List<Tweet> getTimeline(Integer user_id);

  /**
   * Find followers of a given user
   * @param user_id target user
   * @return a list of user id's
   */
  List<Integer> getFollowers(Integer user_id);

  /**
   * Find followees of a given user (who the given user follows)
   * @param user_id target user
   * @return a list of user id's
   */
  List<Integer> getFollowees(Integer user_id);

  /**
   * Find tweets posted by a given user
   * @param user_id target user
   * @return a list of tweets
   */
  List<Tweet> getMostRecentTweets(Integer user_id);

  /**
   * Find all distinct users
   * @return a list of user id's
   */
  List<Integer> getUsers();

  /**
   * Set connection settings
   * @param url target connection url
   * @param user database user
   * @param password user password
   */
  void authenticate(String url, String user, String password);

  /**
   * Close the connection when application finishes
   */
  void closeConnection();
}
