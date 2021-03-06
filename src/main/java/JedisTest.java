import java.util.List;

import redis.clients.jedis.Jedis;

public class JedisTest {
  public static void main(String[] args) {
    // Jedis client object
    Jedis jedis = new Jedis();

    jedis.flushAll();

    jedis.set("next_tweet_id", "0");

    // First post by user 0
    String tweet_name = "tweet_" + jedis.get("next_tweet_id");
    jedis.set(tweet_name, "First tweet");
    jedis.rpush("timeline_1", jedis.get(tweet_name));
    for (String tweet : jedis.lrange("timeline_1", 0, 9)) {
      System.out.println(tweet);
    }
//    jedis.set("hello", "world");
//    jedis.set("counter", "1");
//    jedis.incr("counter");
//
//    jedis.lpush("friends", "joe");
//    jedis.lpush("friends", "mary");
//    jedis.rpush("friends", "Thomas Aquinas");
//    // Query is inclusive of indices
//    List<String> friends = jedis.lrange("friends", 0, 2);
//
//    String counter_val = jedis.get("counter");
//    int i_counter_val = Integer.parseInt(counter_val);
//    System.out.println(i_counter_val);
//
//    long N = 100000;
//    long startTime = System.nanoTime();
//    for (int i = 0; i < N; i++) {
//      jedis.set("key_" + i, "" + i);
//    }
//    long endTime = System.nanoTime();
//    long duration = (endTime - startTime) / 1000000;
//    System.out.println((float) N * 1000.0 / duration + " record inserts per second");
  }
}
