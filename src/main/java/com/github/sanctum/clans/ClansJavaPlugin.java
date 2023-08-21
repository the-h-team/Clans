package com.github.sanctum.clans;

import com.github.sanctum.clans.bridge.ClanAddon;
import com.github.sanctum.clans.bridge.ClanAddonQueue;
import com.github.sanctum.clans.construct.ArenaManager;
import com.github.sanctum.clans.construct.ClaimManager;
import com.github.sanctum.clans.construct.ClanManager;
import com.github.sanctum.clans.construct.CommandManager;
import com.github.sanctum.clans.construct.DataManager;
import com.github.sanctum.clans.construct.ShieldManager;
import com.github.sanctum.clans.construct.api.Claim;
import com.github.sanctum.clans.construct.api.Clan;
import com.github.sanctum.clans.construct.api.ClansAPI;
import com.github.sanctum.clans.construct.api.LogoGallery;
import com.github.sanctum.clans.construct.util.AsynchronousLoanableTask;
import com.github.sanctum.clans.construct.util.ClansUpdate;
import com.github.sanctum.clans.construct.util.FileTypeCalculator;
import com.github.sanctum.clans.construct.util.MessagePrefix;
import com.github.sanctum.clans.construct.util.ReservedLogoCarrier;
import com.github.sanctum.clans.construct.util.Reservoir;
import com.github.sanctum.clans.construct.util.StartProcedure;
import com.github.sanctum.clans.construct.impl.DefaultArena;
import com.github.sanctum.clans.construct.impl.DefaultClaimFlag;
import com.github.sanctum.clans.construct.impl.entity.EntityAssociate;
import com.github.sanctum.clans.listener.PlayerEventListener;
import com.github.sanctum.labyrinth.LabyrinthProvider;
import com.github.sanctum.labyrinth.api.Service;
import com.github.sanctum.labyrinth.api.TaskService;
import com.github.sanctum.labyrinth.data.FileList;
import com.github.sanctum.labyrinth.data.FileManager;
import com.github.sanctum.labyrinth.data.container.KeyedServiceManager;
import com.github.sanctum.labyrinth.data.container.PersistentContainer;
import com.github.sanctum.labyrinth.formatting.FancyMessageChain;
import com.github.sanctum.labyrinth.formatting.Message;
import com.github.sanctum.labyrinth.library.NamespacedKey;
import com.github.sanctum.labyrinth.library.StringUtils;
import com.github.sanctum.panther.annotation.AnnotationDiscovery;
import com.github.sanctum.panther.annotation.Ordinal;
import com.github.sanctum.panther.event.Vent;
import com.github.sanctum.panther.file.Configurable;
import com.github.sanctum.panther.file.Node;
import com.github.sanctum.panther.paste.PasteManager;
import com.github.sanctum.panther.paste.type.Hastebin;
import com.github.sanctum.panther.paste.type.Pastebin;
import com.github.sanctum.panther.util.OrdinalProcedure;
import com.github.sanctum.panther.util.Task;
import com.github.sanctum.skulls.CustomHead;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

/**
 * <pre>
 *    ████████╗███████╗████████╗██╗  ██╗███████╗██████╗
 *    ╚══██╔══╝██╔════╝╚══██╔══╝██║  ██║██╔════╝██╔══██╗
 *       ██║   █████╗     ██║   ███████║█████╗  ██████╔╝
 *       ██║   ██╔══╝     ██║   ██╔══██║██╔══╝  ██╔══██╗
 *       ██║   ███████╗   ██║   ██║  ██║███████╗██║  ██║
 *       ╚═╝   ╚══════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝╚═╝  ╚═╝
 * <pre>
 * Copyright (c) 2022 Team Sanctum
 */
public class ClansJavaPlugin extends JavaPlugin implements ClansAPI, Vent.Host {

	private NamespacedKey STATE;
	public Configurable.Extension TYPE;
	private static ClansJavaPlugin PRO;
	private static FileList origin;
	private MessagePrefix prefix;
	private ArenaManager arenaManager;
	private ClaimManager claimManager;
	private ShieldManager shieldManager;
	private ClanManager clanManager;
	private CommandManager commandManager;
	private LogoGallery gallery;
	public DataManager dataManager;
	private Hastebin hastebin;
	private Pastebin pastebin;
	private KeyedServiceManager<ClanAddon> serviceManager;

	public String USER_ID = "%%__USER__%%";
	public String NONCE = "%%__NONCE__%%";
	private UUID sessionId;

	public void onLoad() {
		fixDataFolder();
		// register api on load, a change from before.
		Bukkit.getServicesManager().register(ClansAPI.class, this, this, ServicePriority.Normal);
	}

	public void onEnable() {
		initialize();
		if (!isValid()) return;

		Configurable.registerClass(Clan.class);
		Configurable.registerClass(Claim.class);
		ConfigurationSerialization.registerClass(Claim.class);
		ConfigurationSerialization.registerClass(Clan.class);

		getClaimManager().getFlagManager().register(DefaultClaimFlag.values());

		OrdinalProcedure.process(new StartProcedure(this));

		LogoGallery gallery = getLogoGallery();
		ReservedLogoCarrier reserved2 = ReservedLogoCarrier.SUMMER;
		gallery.load(reserved2.getId(), reserved2.get());
		ReservedLogoCarrier reserved3 = ReservedLogoCarrier.HALLOWEEN;
		gallery.load(reserved3.getId(), reserved3.get());
		ReservedLogoCarrier reserved4 = ReservedLogoCarrier.BIG_LANDSCAPE;
		gallery.load(reserved4.getId(), reserved4.get());
	}

	void fixDataFolder() {
		final File pluginsDir = new File(FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath().replaceAll("%20", " "));
		File clansDir = new File(pluginsDir.getParentFile().getPath(), "ClansPro");
		File newDir = new File(pluginsDir.getParentFile().getPath(), getDescription().getName());
		if (clansDir.renameTo(newDir)) {
			getLogger().info("Renamed the old 'ClansPro' folder to 'Tether' for you.");
		}
	}

	private boolean isValid() {
		String state = LabyrinthProvider.getInstance().getContainer(STATE).get(String.class, "toString");
		if (state != null) {
			boolean recorded = Boolean.parseBoolean(state);
			if (recorded != Bukkit.getOnlineMode()) {
				if (!Clan.ACTION.getAllClanIDs().isEmpty()) {
					FancyMessageChain chain = new FancyMessageChain();
					chain.append(msg -> msg.then("-------------------------------------------------------"));
					chain.append(msg -> msg.then("-------------------------------------------------------"));
					chain.append(msg -> msg.then("-------------------------------------------------------"));
					chain.append(msg -> msg.then("======================================================="));
					chain.append(msg -> msg.then("======================================================="));
					chain.append(msg -> msg.then("            [Online state change detected]             "));
					chain.append(msg -> msg.then("[To use this plugin again your clan data must be reset]"));
					chain.append(msg -> msg.then(" [This is due to a change in unique id's for players.] "));
					chain.append(msg -> msg.then(" "));
					chain.append(msg -> msg.then("   [Online uuid provision is persistent but offline] "));
					chain.append(msg -> msg.then("    [provision is only persistent to the username.] "));
					chain.append(msg -> msg.then("======================================================="));
					chain.append(msg -> msg.then("======================================================="));
					chain.append(msg -> msg.then("-------------------------------------------------------"));
					chain.append(msg -> msg.then("-------------------------------------------------------"));
					chain.append(msg -> msg.then("-------------------------------------------------------"));
					for (Message m : chain) {
						Bukkit.getConsoleSender().spigot().sendMessage(m.build());
					}
					getServer().getPluginManager().disablePlugin(this);
					return false;
				} else {
					LabyrinthProvider.getInstance().getContainer(STATE).delete("toString");
				}
			}
		}
		return true;
	}

	public void onDisable() {
		ClanAddonQueue addonQueue = ClanAddonQueue.getInstance();
		Optional.ofNullable(LabyrinthProvider.getService(Service.TASK).getScheduler(TaskService.ASYNCHRONOUS).get(AsynchronousLoanableTask.KEY)).ifPresent(Task::cancel);
		for (PersistentContainer component : LabyrinthProvider.getService(Service.DATA).getContainers(this)) {
			for (String key : component.keySet()) {
				try {
					component.save(key);
				} catch (IOException e) {
					getLogger().severe("- Unable to save meta '" + key + "' from namespace " + component.getKey().getNamespace() + ":" + component.getKey().getKey());
					e.printStackTrace();
				}
			}
		}

		PlayerEventListener.LOANABLE_TASK.stop();

		for (ClanAddon addon : addonQueue.get()) {
			AnnotationDiscovery<Ordinal, ClanAddon> discovery = AnnotationDiscovery.of(Ordinal.class, ClanAddon.class);
			discovery.filter(method -> method.getName().equals("remove"), true);
			discovery.ifPresent((ordinal, method) -> {
				try {
					method.invoke(addon);
				} catch (IllegalAccessException | InvocationTargetException ex) {
					ex.printStackTrace();
				}
			});
		}

		dataManager.ID_MODE.clear();

		PlayerEventListener.ARMOR_STAND_REMOVAL.run(this).deploy();

		getClanManager().getClans().forEach(c -> {
			c.save(); // save the clan
			for (Clan.Associate a : c.getMembers()) {
				if (!(a instanceof EntityAssociate)) {
					a.save(); // save associate data.
				} else a.remove();
			}
			for (Claim claim : c.getClaims()) {
				claim.save(); // save claim data.
			}
			Reservoir r = Reservoir.get(c);
			if (r != null) r.save(); // save reservoir data
			c.remove(); // clean up the clan from cache.
		});

		getLogoGallery().save();

		Optional.ofNullable(System.getProperty("RELOAD")).ifPresent(s -> {
			LabyrinthProvider.getService(Service.DATA).getContainer(STATE).attach("toString", String.valueOf(Bukkit.getOnlineMode()));
			try {
				LabyrinthProvider.getInstance().getContainer(STATE).save("toString");
			} catch (IOException e) {
				getLogger().warning("- Unable to record current online status");
			}
			if (s.equals("FALSE")) {
				System.setProperty("RELOAD", "TRUE");
			}
		});

		FileManager heads = getFileList().get("heads", "Configuration/Data", Configurable.Type.JSON);
		CustomHead.Manager.getHeads().stream().filter(h -> h.category().equals("ClansPro")).forEach(h -> {
			heads.write(t -> {
				t.set(h.name() + ".name", h.name());
				t.set(h.name() + ".custom", true);
				t.set(h.name() + ".category", h.category());
			});
		});
	}

	public void setPrefix(MessagePrefix prefix) {
		this.prefix = prefix;
	}

	@Override
	public @NotNull KeyedServiceManager<ClanAddon> getServiceManager() {
		return this.serviceManager;
	}

	@Override
	public @NotNull ArenaManager getArenaManager() {
		return this.arenaManager;
	}

	@Override
	public Optional<Clan.Associate> getAssociate(OfflinePlayer player) {
		return player == null ? Optional.empty() : getClanManager().getClans().stream().filter(c -> c.getMember(m -> Objects.equals(m.getName(), player.getName())) != null).map(c -> c.getMember(m -> Objects.equals(m.getName(), player.getName()))).findFirst();
	}

	@Override
	public Optional<Clan.Associate> getAssociate(UUID uuid) {
		return uuid == null ? Optional.empty() : getClanManager().getClans().stream().filter(c -> c.getMember(m -> Objects.equals(m.getId(), uuid)) != null).map(c -> c.getMember(m -> Objects.equals(m.getId(), uuid))).findFirst();
	}

	@Override
	public Optional<Clan.Associate> getAssociate(String playerName) {
		return playerName == null ? Optional.empty() : getClanManager().getClans().stream().filter(c -> c.getMember(m -> Objects.equals(m.getName(), playerName)) != null).map(c -> c.getMember(m -> Objects.equals(m.getName(), playerName))).findFirst();
	}

	@Override
	public @NotNull FileList getFileList() {
		return origin;
	}

	@Override
	public @NotNull ClanManager getClanManager() {
		return clanManager;
	}

	@Override
	public @NotNull ClaimManager getClaimManager() {
		return claimManager;
	}

	@Override
	public @NotNull ShieldManager getShieldManager() {
		return shieldManager;
	}

	@Override
	public @NotNull CommandManager getCommandManager() {
		return commandManager;
	}

	@Override
	public @NotNull LogoGallery getLogoGallery() {
		return gallery;
	}

	@Override
	public @NotNull PasteManager getPasteManager() {
		return PasteManager.getInstance();
	}

	@Override
	public @NotNull Hastebin getHastebin() {
		return hastebin;
	}

	@Override
	public @NotNull Pastebin getPastebin() {
		return pastebin;
	}

	@Override
	public boolean isUpdated() {
		ClansUpdate update = new ClansUpdate(getPlugin());
		return CompletableFuture.supplyAsync(() -> {
			try {
				if (update.hasUpdate()) {
					getPlugin().getLogger().warning("- An update is available! " + update.getLatest() + " download: [" + update.getResource() + "]");
					return false;
				} else {
					getPlugin().getLogger().info("- All up to date! Latest:(" + update.getLatest() + ") Current:(" + getDescription().getVersion() + ")");
					return true;
				}
			} catch (Exception e) {
				getPlugin().getLogger().info("- Couldn't connect to servers, unable to check for updates.");
			}
			return false;
		}).join();
	}

	@Override
	public boolean isTrial() {
		return false;
	}

	@Override
	public boolean isNameBlackListed(String name) {
		for (String s : ClansAPI.getDataInstance().getConfig().read(c -> c.getNode("Clans.name-blacklist").get(ConfigurationSection.class)).getKeys(false)) {
			if (StringUtils.use(name).containsIgnoreCase(s)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public @NotNull UUID getSessionId() {
		return sessionId;
	}

	@Override
	public @NotNull MessagePrefix getPrefix() {
		return this.prefix;
	}

	@Override
	public @NotNull Plugin getPlugin() {
		return PRO;
	}

	void initialize() {
		origin = FileList.search(PRO = this);
		hastebin = getPasteManager().newHaste();
		pastebin = getPasteManager().newPaste("a5tsxh3c37_rmPTCN9gy9kjhd5vepz34");
		STATE = new NamespacedKey(this, "online-state");
		sessionId = UUID.randomUUID();
		dataManager = new DataManager();
		gallery = new LogoGallery();
		TYPE = new FileTypeCalculator(dataManager).getType();
		clanManager = new ClanManager();
		claimManager = new ClaimManager();
		shieldManager = new ShieldManager();
		commandManager = new CommandManager();
		serviceManager = new KeyedServiceManager<>();
		arenaManager = new ArenaManager();

		// load configured arenas, each new arena allows for another war to be held.
		FileManager config = dataManager.getConfig();
		Node clans = config.getRoot().getNode("Clans");
		int arenas = clans.getNode("war").getNode("max-wars").toPrimitive().getInt();
		for (int i = 0; i < arenas; i++) {
			arenaManager.load(new DefaultArena("PRO-" + (i + 1)));
		}
		Node formatting = config.read(c -> c.getNode("Formatting"));
		Node prefix = formatting.getNode("prefix");
		this.prefix = new MessagePrefix(prefix.getNode("prefix").toPrimitive().getString(),
				prefix.getNode("text").toPrimitive().getString(),
				prefix.getNode("suffix").toPrimitive().getString());
	}

}
