import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class Driver {
  private static IDatabaseAPI api = new MySQLDatabaseAPI();

  public Driver(IDatabaseAPI api) {
    this.api = api;
  }

  public Driver() {}

  // Driver program to read tweets from tweets.csv and add them to MySQL Database
  public void readTweets() throws FileNotFoundException {

    // Authenticate your access to the server.
    //    String url =  "jdbc:mysql://localhost:3306/twitter?serverTimezone=EST5EDT";
    String url = "jdbc:mysql://localhost:3306/twitter";
    String user = "root";
    String password = "jiajia2002";

    //    this.api.authenticate(url, user, password); // DON'T HARDCODE PASSWORDS!

    // parsing a CSV file into Scanner class constructor
    Scanner sc = new Scanner(new File("hw1_data/tweets_sample.csv"));
    //    Run line below when testing is done
    //    Scanner sc = new Scanner(new File("hw1_data/tweets.csv"));
    sc.useDelimiter(","); // sets the delimiter pattern
    while (sc.hasNext()) // returns a boolean value
    {
      String tweet = sc.next();
      System.out.print(tweet); // find and returns the next complete token from this scanner
      System.out.println(tweet.toCharArray()[0]);
      String[] tokens = tweet.trim().split("\\s+");

      //      if (tokens.length > 1) {
      //        String user_id = tokens[0];
      //        String tweet_text = String.join(" ", Arrays.copyOfRange(tokens, 1, tokens.length -
      // 1));
      //        System.out.println(user_id);
      //        System.out.println(tweet_text);
      //      }
    }
    sc.close(); // closes the scanner
  }

  // Driver program to return a random user's home timeline
  public void randomHomeTimeline() {}

  // Main method
  public static void main(String[] args) throws IOException {
    String csvFilePath = "hw1_data/tweets_sample.csv";
    int batchSize = 5;

    try {
      BufferedReader lineReader = new BufferedReader(new FileReader(csvFilePath));
      String lineText = null;

      int count = 0;

      // Skip labels
      lineReader.readLine();

      while ((lineText = lineReader.readLine()) != null) {
        String[] data = lineText.split(",");
        int user_id = Integer.valueOf(data[0]);
        String tweet_text = data[1];
        System.out.println(user_id);
        System.out.println(tweet_text);
      }
    } catch (IOException ex) {
      System.err.println(ex);
    }
  }

  //  private static DPDatabaseAPI api = new DPDatabaseMysql();
  //
  //  public static void main(String[] args) throws Exception {
  //
  //    // Authenticate your access to the server.
  //    String url =  "jdbc:mysql://localhost:3306/doctorpatient?serverTimezone=EST5EDT";
  //    String user = System.getenv("DOCPAT_USER");
  //    String password = System.getenv("DOCPAT_PASSWORD");
  //
  //    api.authenticate(url, user, password); // DON'T HARDCODE PASSWORDS!
  //
  //    List<Doctor> doctors = api.acceptingNewPatients("oncology");
  //    System.out.println("Number of matches: " + doctors.size());
  //    if (doctors.size()>0)
  //      System.out.println("\n\nAvailable oncologists");
  //    for (Doctor d : doctors)
  //      System.out.println(d.toString());
  //
  //
  //    // Insert a test patient
  //    // Exception thrown if patient already exists
  //
  //
  //    SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
  //    System.out.println("\n\nRegistering a patient");
  //    Patient p = new Patient("Gates", "Billy", 'M', sdf.parse("10-28-1955"));
  //    int pid = api.registerPatient(p);
  //
  //    if (pid != -1)
  //      System.out.println("Patient " + p.getFirstName() + " " + p.getLastName() + " registered
  // (ID = "+pid+").");
  //
  //
  //
  //
  //    //	     Test adding specialities
  //    //		 Note the repeats!
  ////	    System.out.println("Adding specialties");
  ////	    int sid1 = api.getOrInsertSpecialty("oncology");
  ////		int sid2 = api.getOrInsertSpecialty("cardiology");
  ////		int sid3 = api.getOrInsertSpecialty("pediatrics");
  ////		int sid4 = api.getOrInsertSpecialty("ent");            // This one doesn't yet exist
  ////		int sid5 = api.getOrInsertSpecialty("cardiology");     // repeat request
  ////		System.out.println("Specialty IDs: "+sid1+" "+sid2+" "+sid3+" "+sid4+" "+sid5);
  //
  //    // add some doctors to a list collection and then add them all to the database
  //
  ////		List<Doctor> drlist = new ArrayList<Doctor>();
  ////		drlist.add(new Doctor("House", "Gregory", true, "diagnostics", "MGH"));
  ////		drlist.add(new Doctor("No", "Dr.", false, "oncology", "MGH"));
  ////		drlist.add(new Doctor("Holmes", "Sherlock", true, "ent", "MGH"));
  ////		drlist.add(new Doctor("Rachlin", "John", true, "brain surgery", "Mayo Clinic"));
  ////		api.insertDoctors(drlist);
  //
  //    api.closeConnection();
  //
  //  }
}
