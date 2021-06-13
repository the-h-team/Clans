package com.github.sanctum.clans.util.events.clans;

import com.github.sanctum.clans.construct.DefaultClan;
import com.github.sanctum.clans.construct.api.Clan;
import com.github.sanctum.clans.construct.api.ClansAPI;
import com.github.sanctum.clans.util.events.AsyncClanEventBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class ChatChannelAllyEvent extends AsyncClanEventBuilder {

	private final Player chatting;

	private final Set<Player> receivers;

	private String message;

	private String static1 = "&7[&a&l&nAC&7] ";

	private String static2 = " &7: ";

	private String highlight = "&f&o%s";

	private String playerMeta = "&2&oClan ally &a%s &2&opinged ally chat.";

	private Sound pingSound = Sound.ENTITY_EXPERIENCE_ORB_PICKUP;

	{
		if (Bukkit.getVersion().contains("1.16")) {
			static1 = "&#5b626e[&#16b7db&l&nAC&#5b626e] ";
			static2 = " &#5b626e: &f";
			highlight = "&#1696db&o%s";
		}
	}

	public ChatChannelAllyEvent(Player sender, Set<Player> receivers, String message) {
		this.chatting = sender;
		this.receivers = receivers;
		this.message = message;
	}

	public Player getChatting() {
		return chatting;
	}

	public Clan getClan() {
		return ClansAPI.getInstance().getClan(chatting.getUniqueId());
	}

	public List<Player> getReceivers() {
		List<Player> players = new ArrayList<>();
		for (Player p : receivers) {
			if (DefaultClan.action.getClanID(p.getUniqueId()) != null) {
				Clan other = ClansAPI.getInstance().getClan(p.getUniqueId());
				if (getClan().getAllyList().contains(other.getId().toString()) || Arrays.asList(getClan().getMembersList()).contains(p.getUniqueId().toString())) {
					players.add(p);
				}
			}
		}
		players.addAll(ClansAPI.getData().ALLY_SPY);
		return players;
	}

	public String getMessage() {
		return message;
	}

	public Sound getPingSound() {
		return pingSound;
	}

	public String getPrefix() {
		return static1;
	}

	public String getDivider() {
		return static2;
	}

	public String getHighlight() {
		return highlight;
	}

	public String getHoverMeta() {
		return playerMeta;
	}

	public void setPingSound(Sound pingSound) {
		this.pingSound = pingSound;
	}

	public void setHoverMeta(String playerMeta) {
		this.playerMeta = playerMeta;
	}

	public void setPrefix(String static1) {
		this.static1 = static1;
	}

	public void setDivider(String static2) {
		this.static2 = static2;
	}

	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String getName() {
		return getClass().getSimpleName();
	}
}