package io.github.sanctum.clans.api.model.data.clan;

import io.github.sanctum.clans.api.model.Clan;
import org.intellij.lang.annotations.Pattern;
import org.intellij.lang.annotations.RegExp;

import java.lang.annotation.Documented;

/**
 * Marks a clan tag String representation.
 * <p>
 * Tags are used to identify clans in player chat and other contexts.
 *
 * @since 1.6.0
 * @see Clan
 * @author ms5984
 */
@Documented
@Pattern(Tag.FORMAT)
public @interface Tag {
    /**
     * The required format of a clan tag.
     * <p>
     * A tag may only contain letters, numbers, underscores and hyphens; it
     * must not begin or end with a hyphen and must not be an empty string.
     */
    @RegExp String FORMAT = "^\\w(?:[\\w-]*\\w)?$";
}
