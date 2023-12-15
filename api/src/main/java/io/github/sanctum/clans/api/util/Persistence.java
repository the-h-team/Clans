package io.github.sanctum.clans.api.util;

import com.github.sanctum.panther.util.TypeAdapter;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Provides access to an object's persistence layer.
 *
 * @since 1.6.0
 * @author Hempfest
 */
public interface Persistence {
    /**
     * Retrieves a value of the specified type.
     *
     * @param key the delimiting key
     * @param type the type of the value
     * @return the value if found or null
     */
    <R> R getValue(@NotNull String key, @NotNull Class<R> type);

    /**
     * Retrieves a value of the specified type.
     *
     * @param key the delimiting key
     * @param flag the type of the value
     * @return the value if found or null
     */
    <R> R getValue(@NotNull String key, @NotNull TypeAdapter<R> flag);

    /**
     * Retrieves a value.
     *
     * @param key the delimiting key
     * @return the value if found or null
     */
    default <R> R getValue(@NotNull String key) {
        return getValue(key, TypeAdapter.get());
    }

    /**
     * Stores a value.
     *
     * @param key the delimiting key
     * @param value the value to store
     * @param temporary whether the value should be persisted
     * @return the supplied value
     */
    @Contract("_, _, _ -> param2")
    @SuppressWarnings("UnusedReturnValue")
    <R> R setValue(@NotNull String key, R value, boolean temporary);
}
