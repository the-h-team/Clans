package io.github.sanctum.clans.api.model.data.clan;

import org.jetbrains.annotations.ApiStatus;

/**
 * The base model for clan data.
 *
 * @since 1.6.0
 * @author ms5984
 */
@ApiStatus.NonExtendable
public interface ClanModel {
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
