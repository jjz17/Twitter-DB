import java.util.List;

public interface ITwitterAPI {

  void postTweet(Tweet t);
  List<Tweet> getTimeline(Integer user_id);

  List<Integer> getFollowers(Integer user_id);  // who is following user_id
  List<Integer> getFollowees(Integer user_id); // who is user_id following?
  List<Tweet> getTweets(Integer user_id); // tweets posted by user_id
}
