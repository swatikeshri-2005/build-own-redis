package database;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class RedisDatabase {

    private static final ConcurrentHashMap<String, String> database =
            new ConcurrentHashMap<>();

    // SET key value
    public static void set(String key, String value) {
        database.put(key, value);
    }

    // GET key
    public static String get(String key) {

        if (ExpiryManager.isExpired(key)) {

            database.remove(key);
            ExpiryManager.remove(key);

            return null;
        }

        return database.get(key);
    }

    // EXISTS key
    public static boolean exists(String key) {

        if (ExpiryManager.isExpired(key)) {

            database.remove(key);
            ExpiryManager.remove(key);

            return false;
        }

        return database.containsKey(key);
    }

    // DEL key
    public static int del(String key) {

        ExpiryManager.remove(key);

        return database.remove(key) != null ? 1 : 0;
    }

    // KEYS *
    public static Set<String> keys() {

        // Remove expired keys before returning
        for (String key : database.keySet()) {

            if (ExpiryManager.isExpired(key)) {

                database.remove(key);
                ExpiryManager.remove(key);
            }
        }

        return database.keySet();
    }

    public static int increment(String key) {
         String value = get(key);

    if (value == null) {
        database.put(key, "1");
        return 1;
    }

    int number = Integer.parseInt(value);

    number++;

    database.put(key, String.valueOf(number));

    return number;
    }

    public static int decrement(String key) {
         String value = get(key);

    if (value == null) {
        database.put(key, "-1");
        return -1;
    }

    int number = Integer.parseInt(value);

    number--;

    database.put(key, String.valueOf(number));

    return number;
    }

}