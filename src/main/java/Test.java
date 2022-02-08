import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    MySQLDatabaseAPI api = new MySQLDatabaseAPI();
    ArrayList<Integer> ints = new ArrayList<>(Arrays.asList(-1,100023,124932));
    System.out.println(api.listToString(ints));
//    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
//
//    LocalDateTime start = LocalDateTime.now();
//
//    long endTime = System.currentTimeMillis() + 1000;
//
//    List<Integer> ints = new ArrayList<>();
//    ints.add(1);
//    ints.add(2);
//    ints.add(3);
//
//    while (System.currentTimeMillis() < endTime) {
//      System.out.println(ints.get((int) (Math.random() * ints.size())));
//    }
//
//    LocalDateTime end = LocalDateTime.now();
//
//    System.out.println("Start time: " + dtf.format(start));
//    System.out.println("End time: " + dtf.format(end));
//
//    double seconds = start.until(end, ChronoUnit.SECONDS);
//
//    System.out.println(seconds);
  }
}
