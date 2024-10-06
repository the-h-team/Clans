package com.github.sanctum.clans.event.associate;

import com.github.sanctum.clans.model.Claim;
import com.github.sanctum.clans.model.Clan;
import com.github.sanctum.clans.model.ClansAPI;
import com.github.sanctum.clans.event.claim.ClaimEvent;
import org.bukkit.entity.Player;

/**
 * Called when a clan associate has successfully claimed new land.
 */
public class AssociateObtainLandEvent extends ClaimEvent {

	private final Player claimer;

	public AssociateObtainLandEvent(Claim land) {
		super(dummy, land);
		this.claimer = null;
	}

	public AssociateObtainLandEvent(Player claimer, Claim land) {
		super(ClansAPI.getInstance().getAssociate(claimer).get(), land);
		this.claimer = claimer;
	}

	@Override
	public Player getPlayer() {
		return claimer;
	}

	@Override
	public Clan getClan() {
		return ((Clan)getClaim().getHolder());
	}

}
