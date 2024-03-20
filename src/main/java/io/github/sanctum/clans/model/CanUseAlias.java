package io.github.sanctum.clans.model;

import io.github.sanctum.clans.interfacing.MutableEntity;
import io.github.sanctum.clans.interfacing.MutableEntityEdits;
import io.github.sanctum.clans.interfacing.StagedUpdate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * An object that can have a separate alias.
 * <p>
 * Aliases are nicknames/display names.
 *
 * @since 1.6.1
 * @author ms5984
 */
public interface CanUseAlias extends MutableEntity {
    /**
     * Gets the nickname or alternate display name of this object.
     *
     * @return the nickname, display name or null
     */
    @Nullable String getAlias();

    /**
     * An alias editing utility.
     *
     * @since 1.6.1
     */
    interface Edits extends MutableEntityEdits, CanUseAlias {
        @Override
        @NotNull CanUseAlias getMutating();

        /**
         * Sets the new nickname/alternate display name for the object.
         * <p>
         * Use {@code null} to clear.
         *
         * @param newName the nickname, display name or null
         * @return this element
         */
        @NotNull Update setAlias(@Nullable String newName);
    }

    /**
     * A staged alias update.
     *
     * @since 1.6.1
     */
    interface Update extends StagedUpdate {
        @Override
        @NotNull CanUseAlias getReferenceObject();

        /**
         * Gets the nickname/alternative display proposed by this element.
         *
         * @return the proposed nickname, display name or null
         */
        @Nullable String getProposedNickname();
    }
}