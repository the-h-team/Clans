package io.github.sanctum.clans.api.model.data.clan;

import io.github.sanctum.clans.api.model.Clan;
import org.intellij.lang.annotations.Pattern;
import org.intellij.lang.annotations.RegExp;
import org.jetbrains.annotations.ApiStatus;

import java.lang.annotation.Documented;

/**
 * The base model for clan data.
 *
 * @since 1.6.0
 * @see Clan
 * @author ms5984
 */
@ApiStatus.NonExtendable
public interface ClanModel {
    /**
     * Marks a clan tag String representation.
     * <p>
     * Tags are used to identify clans in player chat and other contexts.
     *
     * @see Clan
     */
    @Documented
    @Pattern(ClanTag.FORMAT)
    @interface ClanTag {
        /**
         * The required format of a clan tag.
         * <p>
         * A tag may only contain letters, numbers, underscores and hyphens; it
         * must not begin or end with a hyphen and must not be an empty string.
         */
        @RegExp String FORMAT = "^\\w(?:[\\w-]*\\w)?$";
    }

    /**
     * Marks a clan password.
     * <p>
     * Passwords are used to prevent players joining a clan. Only using the
     * correct password will allow a player to join a clan.
     *
     * @see Clan
     */
    @Documented
    @Pattern(ClanPassword.FORMAT)
    @interface ClanPassword {
        /**
         * The required format of a clan password (if defined).
         * <p>
         * In general, a clan password may contain any non-whitespace character;
         * additionally, it must not be an empty string. If it is not defined,
         * it will be represented as {@code null}.
         */
        @RegExp String FORMAT = "^\\S+$";
    }

    /**
     * Represents boolean settings for a clan.
     */
    enum Flag {
        /**
         * True if the clan is in war mode; false in peacetime.
         */
        WAR_MODE,
        /**
         * True if friendly fire is allowed; false if not.
         */
        FRIENDLY_FIRE,
    }

    /**
     * Represents a clan's relationship with another clan.
     */
    enum Stance {
        /**
         * The clan is neutral with another clan.
         * <p>
         * This is the default stance.
         */
        NEUTRAL,
        /**
         * The clan is allied with another clan.
         */
        ALLY,
        /**
         * The clan is enemies with another clan.
         */
        ENEMY,
    }
}
