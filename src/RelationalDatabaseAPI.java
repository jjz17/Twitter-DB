import java.sql.JDBCType;
import java.util.List;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

class RelationalDatabaseAPI implements IDatabaseAPI {

  private Connection connect = null;
  private Statement statement = null;
  private PreparedStatement preparedStatement = null;
  private ResultSet resultSet = null;

  private void close() {
    try {
      if (resultSet != null) {
        resultSet.close();
      }

      if (statement != null) {
        statement.close();
      }

      if (connect != null) {
        connect.close();
      }
    } catch (Exception e) {

    }
  }

  @Override
  public void postTweet(Tweet t) throws Exception {
    try {
      // This will load the MySQL driver, each DB has its own driver
      Class.forName("com.mysql.jdbc.Driver");
      // Setup the connection with the DB
      this.connect =
              DriverManager.getConnection(
                      "jdbc:mysql://localhost/feedback?" + "user=sqluser&password=sqluserpw");

//      // Statements allow to issue SQL queries to the database
//      this.statement = this.connect.createStatement();
//      // Result set get the result of the SQL query
//      this.resultSet = this.statement.executeQuery("select * from twitter.tweets");
//      writeResultSet(this.resultSet);


//      insert into table1 (field1, field3)  values (5,10)

//      insert into table1 values (5, DEFAULT, 10, DEFAULT)

      // PreparedStatements can use variables and are more efficient
      this.preparedStatement =
              this.connect.prepareStatement(
                      "INSERT INTO  twitter.tweets VALUES (NULL, ?, NULL, ?)");
      // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
      // Parameters start with 1
      this.preparedStatement.setNull(1, JDBCType.NULL.getVendorTypeNumber());
      this.preparedStatement.setInt(2, 1);
      this.preparedStatement.setNull(3, JDBCType.NULL.getVendorTypeNumber());
      this.preparedStatement.setString(4, "sample tweet");
      this.preparedStatement.executeUpdate();

//      preparedStatement =
//              connect.prepareStatement(
//                      "SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
//      resultSet = preparedStatement.executeQuery();
//      writeResultSet(resultSet);

      // Remove again the insert comment
//      preparedStatement =
//              connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");
//      preparedStatement.setString(1, "Test");
//      preparedStatement.executeUpdate();
//
//      resultSet = statement.executeQuery("select * from feedback.comments");
//      writeMetaData(resultSet);

    } catch (Exception e) {
      throw e;
    } finally {
      close();
    }
  }

  @Override
  public List<Tweet> getTimeline(Integer user_id) {
    return null;
  }

  @Override
  public List<Integer> getFollowers(Integer user_id) {
    return null;
  }

  @Override
  public List<Integer> getFollowees(Integer user_id) {
    return null;
  }

  @Override
  public List<Tweet> getTweets(Integer user_id) {
    return null;
  }

  public class MySQLAccess {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void readDataBase() throws Exception {
      try {
        // This will load the MySQL driver, each DB has its own driver
        Class.forName("com.mysql.jdbc.Driver");
        // Setup the connection with the DB
        connect =
            DriverManager.getConnection(
                "jdbc:mysql://localhost/feedback?" + "user=sqluser&password=sqluserpw");

        // Statements allow to issue SQL queries to the database
        statement = connect.createStatement();
        // Result set get the result of the SQL query
        resultSet = statement.executeQuery("select * from feedback.comments");
        writeResultSet(resultSet);

        // PreparedStatements can use variables and are more efficient
        preparedStatement =
            connect.prepareStatement(
                "insert into  feedback.comments values (default, ?, ?, ?, ? , ?, ?)");
        // "myuser, webpage, datum, summary, COMMENTS from feedback.comments");
        // Parameters start with 1
        preparedStatement.setString(1, "Test");
        preparedStatement.setString(2, "TestEmail");
        preparedStatement.setString(3, "TestWebpage");
        preparedStatement.setDate(4, new java.sql.Date(2009, 12, 11));
        preparedStatement.setString(5, "TestSummary");
        preparedStatement.setString(6, "TestComment");
        preparedStatement.executeUpdate();

        preparedStatement =
            connect.prepareStatement(
                "SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
        resultSet = preparedStatement.executeQuery();
        writeResultSet(resultSet);

        // Remove again the insert comment
        preparedStatement =
            connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");
        preparedStatement.setString(1, "Test");
        preparedStatement.executeUpdate();

        resultSet = statement.executeQuery("select * from feedback.comments");
        writeMetaData(resultSet);

      } catch (Exception e) {
        throw e;
      } finally {
        close();
      }
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
      //  Now get some metadata from the database
      // Result set get the result of the SQL query

      System.out.println("The columns in the table are: ");

      System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
      for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
        System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
      }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
      // ResultSet is initially before the first data set
      while (resultSet.next()) {
        // It is possible to get the columns via name
        // also possible to get the columns via the column number
        // which starts at 1
        // e.g. resultSet.getSTring(2);
        String user = resultSet.getString("myuser");
        String website = resultSet.getString("webpage");
        String summary = resultSet.getString("summary");
        Date date = resultSet.getDate("datum");
        String comment = resultSet.getString("comments");
        System.out.println("User: " + user);
        System.out.println("Website: " + website);
        System.out.println("summary: " + summary);
        System.out.println("Date: " + date);
        System.out.println("Comment: " + comment);
      }
    }

    // You need to close the resultSet
    private void close() {
      try {
        if (resultSet != null) {
          resultSet.close();
        }

        if (statement != null) {
          statement.close();
        }

        if (connect != null) {
          connect.close();
        }
      } catch (Exception e) {

      }
    }
  }
}
