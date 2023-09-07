import redis.clients.jedis.Jedis;

import java.util.Date;

public class RedisStorage {

    private static Jedis jedis;
    private static final String KEY = "UsersQueue";

    private double getTs() {
        return new Date().getTime() / 1000;
    }

    public void init() {
        jedis = new Jedis("localhost", 6379);
    }

    public void deleteAllUsers() {
        jedis.flushDB();
    }

    public void addUser(int user) {
        jedis.zadd(KEY, getTs(), String.valueOf(user));
    }

    public void showAllUsers() {
        System.out.println(jedis.zrange(KEY, 0, -1));
    }
}
