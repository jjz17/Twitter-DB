import java.util.List;


public interface IDatabaseAPI {

  /**
   * Post one tweet
   * @param t
   * @return
   */
  void postTweet(Tweet t);

  /**
   *
   * @param user_id
   * @return
   */
  List<Tweet> getTimeline(Integer user_id);

  /**
   * Find followers of a given user
   * @param user_id
   * @return a list of user id's
   */
  List<Integer> getFollowers(Integer user_id);

  /**
   * Find followees of a given user (who the given user follows)
   * @param user_id
   * @return a list of user id's
   */
  List<Integer> getFollowees(Integer user_id);

  /**
   * Find tweets posted by a given user
   * @param user_id
   * @return a list of tweets
   */
  List<Tweet> getTweets(Integer user_id);

  /**
   * Set connection settings
   * @param url
   * @param user
   * @param password
   */
  void authenticate(String url, String user, String password);

  /**
   * Close the connection when application finishes
   */
  void closeConnection();
}
