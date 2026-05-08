package gov.nist.hit.elr.plugin.utils;

import java.time.Instant;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A singleton cache for web service responses with expiration support.
 * <p>
 * This cache stores objects with timestamps and automatically handles expiration
 * based on a configurable time-to-live (TTL) value.
 * Maintains backward compatibility with existing code.
 * </p>
 */
public class WSCache {

    /** Singleton instance */
    private static WSCache single_instance = null;

    /** Default expiration time in seconds (5 minutes) */
    private static final int DEFAULT_EXPIRATION = 300;

    /** Expiration time in seconds */
    private static int expiration = DEFAULT_EXPIRATION;

    /** Cache map storing URI keys to cached values with timestamps */
    public Map<String, Cache<String>> cache = Collections.synchronizedMap(new HashMap<String, Cache<String>>());

    /**
     * Private constructor to prevent instantiation from outside.
     */
    private WSCache() {
        // Private constructor for singleton pattern
    }

    /**
     * Static method to create instance of Singleton class
     * Maintains backward compatibility with existing code
     *
     * @return the WSCache instance
     */
    public static WSCache getInstance() {
        if (single_instance == null) {
            single_instance = new WSCache();
        }
        return single_instance;
    }

    /**
     * Gets the cache map.
     * Maintained for backward compatibility with existing code.
     *
     * @return the cache map
     */
    public Map<String, Cache<String>> getCache() {
        return cache;
    }

    /**
     * Gets the expiration time in seconds.
     *
     * @return the expiration time in seconds
     */
    public static int getExpiration() {
        return expiration;
    }

    /**
     * Sets the expiration time in seconds.
     *
     * @param expirationSeconds the expiration time in seconds
     */
    public static void setExpiration(int expirationSeconds) {
        expiration = expirationSeconds;
    }

    /**
     * Clears the entire cache.
     */
    public void clearCache() {
        cache.clear();
        cache = Collections.synchronizedMap(new HashMap<String, Cache<String>>());
    }

    /**
     * Gets a cached value if it exists and is not expired.
     *
     * @param key the key to look up
     * @return the cached value if found and not expired, null otherwise
     */
    public String get(String key) {
        Cache<String> cached = cache.get(key);
        if (cached == null) {
            return null;
        }

        // Check if expired using the Cache class's method
        if (cached.isExpired(expiration)) {
            // Remove expired entry
            cache.remove(key);
            return null;
        }

        return cached.getCachedObject();
    }

    /**
     * Puts a value into the cache with the specified key.
     *
     * @param key the key to associate with the value
     * @param value the value to cache
     */
    public void put(String key, String value) {
        cache.put(key, new Cache<String>(value));
    }

    /**
     * Checks if the cache contains a non-expired entry for the specified key.
     *
     * @param key the key to check
     * @return true if the cache contains a non-expired entry for the key
     */
    public boolean contains(String key) {
        Cache<String> cached = cache.get(key);
        if (cached == null) {
            return false;
        }

        // Check if expired using the Cache class's method
        if (cached.isExpired(expiration)) {
            // Remove expired entry
            cache.remove(key);
            return false;
        }

        return true;
    }

    /**
     * Removes all expired entries from the cache.
     * This method can be called periodically to clean up the cache.
     */
    public void cleanupExpired() {
        Instant now = Instant.now();
        cache.keySet().removeIf(key -> {
            Cache<String> cached = cache.get(key);
            return cached != null && cached.isExpired(expiration, now);
        });
    }
}