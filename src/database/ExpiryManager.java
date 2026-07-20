package database;

import java.util.concurrent.ConcurrentHashMap;

public class ExpiryManager {
 private static final ConcurrentHashMap<String, Long> expiryMap =
            new ConcurrentHashMap<>();

    public static void expire(String key, int seconds){

           long expireTime =
                System.currentTimeMillis() + (seconds * 1000L);

        expiryMap.put(key, expireTime);
    }

    public static long ttl(String key){
           if (!expiryMap.containsKey(key))
            return -1;

        long remaining =
                expiryMap.get(key) - System.currentTimeMillis();

        if (remaining <= 0)
            return -2;

        return remaining / 1000;
    }

    public static boolean isExpired(String key){
        if (!expiryMap.containsKey(key))
            return false;

        return System.currentTimeMillis() >= expiryMap.get(key);
    }

        public static void remove(String key) {

        expiryMap.remove(key);

    }
    
}