package com.github.sanctum.clans.construct.impl;

import com.github.sanctum.clans.construct.api.Clan;
import com.github.sanctum.clans.construct.api.ClanCooldown;
import com.github.sanctum.clans.construct.api.ClansAPI;
import com.github.sanctum.labyrinth.library.HUID;

public class CooldownClaim extends ClanCooldown {

	private final String clanId;

	public CooldownClaim(String clanId) {
		this.clanId = clanId;
	}

	@Override
	public String getId() {
		return clanId;
	}

	@Override
	public String getAction() {
		return "Clans:unclaim-limit";
	}

	@Override
	public void setCooldown() {
		abp("unclaim-limit", ClansAPI.getDataInstance().getConfigInt("Clans.land-claiming.over-powering.cooldown.length"));
	}

	@Override
	public long getCooldown() {
		Clan c = ClansAPI.getInstance().getClanManager().getClan(HUID.fromString(clanId));
		if (c.getMemorySpace().isPresent()) {
			return c.getMemorySpace().get().getNode("cooldown").getNode("unclaim-limit").toPrimitive().getLong();
		}
		return 0L;
	}

	@Override
	public String fullTimeLeft() {
		return Clan.ACTION.format(Clan.ACTION.format(Clan.ACTION.format(Clan.ACTION.format(ClansAPI.getDataInstance().getMessageResponse("cooldown-active"), "%d", String.valueOf(getDaysLeft())), "%h", String.valueOf(getHoursLeft())), "%m", String.valueOf(getMinutesLeft())), "%s", String.valueOf(getSecondsLeft()));
	}

	@Override
	public ClanCooldown getAttached() {
		return this;
	}
}
