import java.util.ArrayList;
import java.util.List;

import java.sql.*;

public class MySQLDatabaseAPI implements IDatabaseAPI {

  private DBUtils dbUtils;

  @Override
  public void postTweet(Tweet t) {
//    String sql = "INSERT INTO tweets (tweet_id, user_id, tweet_ts, tweet_text) VALUES (NULL, ?, NULL, ?)";
    String sql = "INSERT INTO tweets (tweet_id, user_id, tweet_ts, tweet_text) VALUES" +
            "('NULL','"+t.getUserId()+"','NULL','"+t.getText()+"')";
    dbUtils.insertOneRecord(sql);
  }

  @Override
  public List<Tweet> getTimeline(Integer user_id) {
    // select userid and text from tweets where the user id is one of the user ids of the given user's following list
    // SELECT user_id, tweet_text FROM tweets t


//    String sql = "select doctor_id, lastname, firstname, new_patients, specialty, h.name hospital "+
//            "from doctor d join specialty using (specialty_id) " +
//            "join hospital h using (hospital_id) "+
//            "where new_patients = 1 "+
//            "and specialty like '"+specialty.toUpperCase()+"'";
//
//    List<Doctor> doctors = new ArrayList<Doctor>();
//
//
//    try {
//      // get connection and initialize statement
//      Connection con = dbu.getConnection();
//      Statement stmt = con.createStatement();
//      ResultSet rs = stmt.executeQuery(sql);
//      while (rs.next() != false)
//        doctors.add(new Doctor(rs.getInt("doctor_id"), rs.getString("lastname"), rs.getString("firstname"),
//                rs.getBoolean("new_patients"), rs.getString("specialty"), rs.getString("hospital")));
//      rs.close();
//      stmt.close();
//    } catch (SQLException e) {
//      System.err.println(e.getMessage());
//      e.printStackTrace();
//    }
//
//    return doctors;
    return null;
  }

  @Override
  public List<Integer> getFollowers(Integer user_id) {
//    String sql = "select user_id"+
//            "from tweets t join follows f using (specialty_id) " +
//            "join hospital h using (hospital_id) "+
//            "where new_patients = 1 "+
//            "and specialty like '"+specialty.toUpperCase()+"'";
//
//    List<Doctor> doctors = new ArrayList<Doctor>();
//
//
//    try {
//      // get connection and initialize statement
//      Connection con = dbu.getConnection();
//      Statement stmt = con.createStatement();
//      ResultSet rs = stmt.executeQuery(sql);
//      while (rs.next() != false)
//        doctors.add(new Doctor(rs.getInt("doctor_id"), rs.getString("lastname"), rs.getString("firstname"),
//                rs.getBoolean("new_patients"), rs.getString("specialty"), rs.getString("hospital")));
//      rs.close();
//      stmt.close();
//    } catch (SQLException e) {
//      System.err.println(e.getMessage());
//      e.printStackTrace();
//    }
//
//    return doctors;

    return null;
  }

  @Override
  public List<Integer> getFollowees(Integer user_id) {
    // select follows_id from follows where user_id = user_id
    String sql = "SELECT follows_id"+
            "FROM follows" +
            "WHERE user_id = " + user_id;

    List<Integer> followees = new ArrayList<Integer>();

    try {
      // get connection and initialize statement
      Connection con = dbUtils.getConnection();
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery(sql);
      while (rs.next() != false)
        followees.add(rs.getInt("follows_id"));
      rs.close();
      stmt.close();
    } catch (SQLException e) {
      System.err.println(e.getMessage());
      e.printStackTrace();
    }

    return followees;
  }

  @Override
  public List<Tweet> getTweets(Integer user_id) {
    return null;
  }

  @Override
  public void authenticate(String url, String user, String password) {
    this.dbUtils = new DBUtils(url, user, password);
  }

  @Override
  public void closeConnection() {
    this.dbUtils.closeConnection();
  }
}
