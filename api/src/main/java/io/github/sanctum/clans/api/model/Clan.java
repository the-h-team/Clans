package io.github.sanctum.clans.api.model;

import com.github.sanctum.panther.util.HUID;
import io.github.sanctum.clans.api.model.data.*;
import io.github.sanctum.clans.api.model.data.clan.*;
import io.github.sanctum.labyrinth.loci.location.WorldPerspective;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The interface for clan data.
 *
 * @since 1.6.0
 * @author ms5984
 */
public interface Clan extends ClanModel, Nickname.Target, Bio.Target {
    /**
     * Gets the HUID of this clan.
     *
     * @return the HUID of this clan
     * @see HUID
     */
    @NotNull HUID getId();

    /**
     * Gets the tag of this clan.
     * <p>
     * Tags are used to identify clans in chat and other contexts.
     *
     * @return the tag of this clan
     */
    @ClanTag @NotNull String getTag(); // moved from getName

    /**
     * Gets the nickname of this clan if defined.
     *
     * @return the nickname of this clan or null
     * @see Nickname#MINIMUM_FORMAT
     */
    @Override
    @Nickname @Nullable String getNickname();

    // TODO palette

    /**
     * Gets the clan's description if defined.
     *
     * @return the description of this clan or null
     * @see Bio#MINIMUM_FORMAT
     */
    @Override
    @Bio @Nullable String getBio(); // FIXME? need getDescription !null overload?

    /**
     * Gets the join password of this clan (if any).
     *
     * @return the join password of this clan or null
     */
    @ClanPassword @Nullable String getPassword();

    // getOwner

    /**
     * Gets the location of the clan base.
     *
     * @return the location or null if not set
     */
    @Nullable WorldPerspective getBase();

    // TODO bring clearancelog into api
    // getPermissions

    /**
     * Gets the amount of power held by this clan.
     *
     * @return this clan's power as a double
     */
    double getPower();

    // TODO move associate factories (at least off of instance)

    // TODO add cooldowns to api
    // getCooldown(String)

    // TODO move pdc, rename system
    // getValue(Class, String)
    // getValue(TypeAdapter, String)
    // getValue(String)
    // setValue(String, R, boolean)

    // TODO remove isValid, replace associated checks

    /**
     * Gets the state of a boolean setting flag for this clan.
     *
     * @param flag the flag type
     * @return true or false
     * @see Flag
     */
    boolean getFlag(@NotNull Clan.Flag flag); // replaces isPeaceful (WAR_MODE), isFriendlyFire (FRIENDLY_FIRE)

    // TODO move member management
    // kick(Associate)

    // TODO move chunk owner testing
    // isOwner(Chunk)

    // TODO move ownership transfer
    // transferOwnership(Associate); use Owner.Target instead

    // TODO move cooldown checks
    // isCooldown(String); isOnCooldown

    // TODO move pdc
    // removeValue(String)

    // TODO Move all setters to a mutable sub-interface
    // setTag(String) (moved from setName)
    // setNickname(String)
    // setBio(String) (moved from setDescription)
    // setPassword(String)
    // setColor(String) (remove?)
    // setFlag(Flag: Flag.WAR_MODE, !boolean) (moved from setPeaceful(boolean))
    // setFlag(Flag: Flag.FRIENDLY_FIRE, boolean) (moved from setFriendlyFire(boolean))
    // setBase(WorldPerspective) (from setBase(Location))

    // TODO pull broadcast methods (probably use Audience)
    // broadcast(String)
    // broadcast(BaseComponent...)
    // broadcast(Message...)
    // broadcast(Predicate<Associate>, String)

    // TODO move mut ops to mutable sub-interface; return resulting value
    // addPower(double) (moved from givePower(double)); add possible failure
    // subtractPower(double) (moved from takePower(double)); add possible failure
    // addClaims(int) (moved from giveChunks(int)) fix doc; add possible failure
    // subtractClaims(int) (moved from takeChunks(int)) fix doc; add possible failure
    // addWins(int) (moved from giveWins(int)); add possible failure; TODO is this ephemeral? move to dedicated api if so
    // subtractWins(int) (moved from takeWins(int)); add possible failure; TODO is this ephemeral? move to dedicated api if so

    // TODO move pdc
    // getKeys

    // TODO move clan stats
    // getClanInfo (is String[], TODO make dedicated api? map?)

    // getMembers TODO members as flyweights?

    /**
     * Gets the number of wars this clan has won.
     *
     * @return the number of wars this clan has won
     */
    int getWins(); // TODO move to war api

    /**
     * Gets the number of wars this clan has lost.
     *
     * @return the number of wars this clan has lost
     */
    int getLosses(); // TODO move to war api

    // TODO move cooldowns
    // getCooldowns

    // TODO move save op to mutable sub-interface
    // save

    // TODO remove remove()

    // TODO move size() to members api

    /**
     * Checks if this clan is owned by the server.
     *
     * @return true if this clan is owned by the server
     */
    boolean isConsole();

    // TODO add Rank api
}
