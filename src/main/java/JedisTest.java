import java.util.List;

import redis.clients.jedis.Jedis;

public class JedisTest {
  public static void main(String[] args) {
    // Jedis client object
    Jedis jedis = new Jedis();

    jedis.flushAll();
    jedis.set("hello", "world");
    jedis.set("counter", "1");
    jedis.incr("counter");

    jedis.lpush("friends", "joe");
    jedis.lpush("friends", "mary");
    jedis.rpush("friends", "Thomas Aquinas");
    // Query is inclusive of indices
    List<String> friends = jedis.lrange("friends", 0, 2);
  }
}
