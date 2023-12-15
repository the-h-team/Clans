package io.github.sanctum.clans.api.model.data;

import org.intellij.lang.annotations.Pattern;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.Nullable;

import java.lang.annotation.Documented;

/**
 * Marks a nickname.
 * <p>
 * This meta-annotation is intended to be used on a {@link String} method
 * return to indicate that that string is a nickname.
 *
 * @since 1.6.0
 * @author ms5984
 */
@Documented
@Pattern(Nickname.MINIMUM_FORMAT)
public @interface Nickname {
    /**
     * The minimum required format of a nickname (if defined).
     * <p>
     * In general, a nickname may contain any character; additionally, it must
     * not be an empty string. If it is not defined, it will be represented as
     * {@code null}.
     */
    @RegExp String MINIMUM_FORMAT = ".+";

    /**
     * An object that can have a nickname.
     */
    interface Target {
        /**
         * Gets the nickname of this object if one is set.
         *
         * @return a nickname or null
         */
        @Nickname @Nullable String getNickname();

        /**
         * An object that can update its nickname.
         */
        interface Mutable extends Target {
            /**
             * Sets the nickname of this object.
             * <p>
             * If {@code nickname} is null the nickname is removed.
             *
             * @param nickname a new nickname or null
             * @throws IllegalArgumentException if the nickname is not valid
             */
            void setNickname(@Nullable String nickname) throws IllegalArgumentException;
        }
    }
}
