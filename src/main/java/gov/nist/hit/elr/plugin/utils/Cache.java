package gov.nist.hit.elr.plugin.utils;

import java.time.Instant;
import java.util.Objects;

/**
 * A simple cache wrapper that associates a value with a timestamp.
 * <p>
 * This class is immutable after construction, making it thread-safe for reading.
 * It's designed to be used with a caching mechanism that handles expiration logic.
 * </p>
 *
 * @param <T> the type of the cached object
 */
public class Cache<T> {

    /** The timestamp when this object was cached */
    private final Instant instant;

    /** The cached object */
    private final T cachedObject;

    /**
     * Creates a new Cache instance with the current timestamp.
     *
     * @param cachedObject the object to cache
     */
    public Cache(T cachedObject) {
        this.cachedObject = cachedObject;
        this.instant = Instant.now();
    }

    /**
     * Creates a new Cache instance with a specified timestamp.
     * <p>
     * This constructor is primarily useful for testing.
     * </p>
     *
     * @param cachedObject the object to cache
     * @param instant the timestamp when the object was cached
     */
    public Cache(T cachedObject, Instant instant) {
        this.cachedObject = cachedObject;
        this.instant = instant;
    }

    /**
     * Gets the timestamp when this object was cached.
     *
     * @return the instant when the object was cached
     */
    public Instant getInstant() {
        return instant;
    }

    /**
     * Gets the cached object.
     *
     * @return the cached object
     */
    public T getCachedObject() {
        return cachedObject;
    }

    /**
     * Checks if this cache entry has expired based on the specified time-to-live.
     *
     * @param ttlSeconds the time-to-live in seconds
     * @return true if the cache entry has expired, false otherwise
     */
    public boolean isExpired(int ttlSeconds) {
        return isExpired(ttlSeconds, Instant.now());
    }

    /**
     * Checks if this cache entry has expired based on the specified time-to-live
     * and reference time.
     * <p>
     * This method is primarily useful for testing with a fixed reference time.
     * </p>
     *
     * @param ttlSeconds the time-to-live in seconds
     * @param now the reference time to check against
     * @return true if the cache entry has expired, false otherwise
     */
    public boolean isExpired(int ttlSeconds, Instant now) {
        Instant expiryTime = instant.plusSeconds(ttlSeconds);
        return now.isAfter(expiryTime);
    }

    /**
     * Gets the time-to-live remaining for this cache entry in seconds.
     *
     * @param ttlSeconds the total time-to-live in seconds
     * @return the remaining time-to-live in seconds, or 0 if already expired
     */
    public long timeToLiveSeconds(int ttlSeconds) {
        Instant expiryTime = instant.plusSeconds(ttlSeconds);
        long secondsUntilExpiry = expiryTime.getEpochSecond() - Instant.now().getEpochSecond();
        return Math.max(0, secondsUntilExpiry);
    }

    /**
     * Returns a string representation of this cache entry.
     *
     * @return a string representation of this cache entry
     */
    @Override
    public String toString() {
        return "Cache{" +
                "instant=" + instant +
                ", cachedObject=" + cachedObject +
                '}';
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * <p>
     * Two Cache objects are considered equal if their cached objects are equal
     * according to Object.equals().
     * </p>
     *
     * @param o the reference object with which to compare
     * @return true if this object is the same as the obj argument; false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cache<?> cache = (Cache<?>) o;
        return Objects.equals(cachedObject, cache.cachedObject);
    }

    /**
     * Returns a hash code value for the object.
     * <p>
     * This method is supported for the benefit of hash tables such as those provided by
     * {@link java.util.HashMap}.
     * </p>
     *
     * @return a hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(cachedObject);
    }
}