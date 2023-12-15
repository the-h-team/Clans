package io.github.sanctum.clans.api.model.data;

import org.intellij.lang.annotations.Pattern;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Documented;

/**
 * Marks a (bio) biography.
 * <p>
 * This meta-annotation is intended to be used on a {@link String} method
 * return to indicate that that string is a bio.
 *
 * @since 1.6.0
 * @author ms5984
 */
@Documented
@Pattern(Bio.MINIMUM_FORMAT)
public @interface Bio {
    /**
     * The minimum required format of a bio (if defined).
     * <p>
     * In general, a bio may contain any character; additionally, it must not
     * be an empty string. If it is not defined, it will be represented as
     * {@code null}.
     */
    @RegExp String MINIMUM_FORMAT = ".+";

    /**
     * An object that can have a bio.
     */
    interface Target {
        /**
         * Gets the bio of this object if one is set.
         *
         * @return a bio or null
         */
        @Bio @Nullable String getBio();

        /**
         * An object that can update its bio.
         */
        interface Mutable extends Target {
            /**
             * Sets the bio of this object.
             * <p>
             * If {@code bio} is null the bio is removed.
             *
             * @param bio a new bio or null
             * @throws IllegalArgumentException if the bio is not valid
             */
            void setBio(@Nullable String bio) throws IllegalArgumentException;
        }
    }
}
