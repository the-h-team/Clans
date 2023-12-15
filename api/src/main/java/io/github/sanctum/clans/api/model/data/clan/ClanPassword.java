package io.github.sanctum.clans.api.model.data.clan;

import io.github.sanctum.clans.api.model.Clan;
import org.intellij.lang.annotations.Pattern;
import org.intellij.lang.annotations.RegExp;

import java.lang.annotation.Documented;

/**
 * Marks a clan password.
 * <p>
 * Passwords are used to prevent players joining a clan. Only using the correct
 * password will allow a player to join a clan.
 *
 * @since 1.6.0
 * @see Clan
 * @author ms5984
 */
@Documented
@Pattern(ClanPassword.FORMAT)
public @interface ClanPassword {
    /**
     * The required format of a clan password (if defined).
     * <p>
     * In general, a clan password may contain any non-whitespace character;
     * additionally, it must not be an empty string. If it is not defined, it
     * will be represented as {@code null}.
     */
    @RegExp String FORMAT = "^\\S+$";
}
