import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class Test {
  public static void main(String[] args) {
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    LocalDateTime start = LocalDateTime.now();

    long endTime = System.currentTimeMillis() + 70000;

    while (System.currentTimeMillis() < endTime) {

    }

    LocalDateTime end = LocalDateTime.now();

    System.out.println("Start time: " + dtf.format(start));
    System.out.println("End time: " + dtf.format(end));

    double seconds = start.until(end, ChronoUnit.SECONDS);

    System.out.println(seconds);
  }
}
