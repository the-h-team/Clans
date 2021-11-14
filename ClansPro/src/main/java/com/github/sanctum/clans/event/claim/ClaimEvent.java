package com.github.sanctum.clans.event.claim;

import com.github.sanctum.clans.construct.RankPriority;
import com.github.sanctum.clans.construct.api.Channel;
import com.github.sanctum.clans.construct.api.Claim;
import com.github.sanctum.clans.construct.api.Clan;
import com.github.sanctum.clans.construct.api.InvasiveEntity;
import com.github.sanctum.clans.construct.api.Relation;
import com.github.sanctum.clans.construct.api.Teleport;
import com.github.sanctum.clans.construct.impl.Resident;
import com.github.sanctum.clans.event.associate.AssociateEvent;
import com.github.sanctum.labyrinth.data.LabyrinthUser;
import com.github.sanctum.labyrinth.data.Node;
import com.github.sanctum.labyrinth.library.Mailer;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The parent abstraction for all clan claim related events.
 */
public abstract class ClaimEvent extends AssociateEvent {

	protected static final Clan.Associate dummy = new Clan.Associate() {

		@Override
		public String getPath() {
			return null;
		}

		@Override
		public boolean isNode(String key) {
			return false;
		}

		@Override
		public Node getNode(String key) {
			return null;
		}

		@Override
		public Set<String> getKeys(boolean deep) {
			return null;
		}

		@Override
		public Map<String, Object> getValues(boolean deep) {
			return null;
		}

		@Override
		public List<String> getLogo() {
			return null;
		}

		@Override
		public List<Carrier> getCarriers() {
			return null;
		}

		@Override
		public List<Carrier> getCarriers(Chunk chunk) {
			return null;
		}

		@Override
		public Carrier newCarrier(Location location) {
			return null;
		}

		@Override
		public void save() {

		}

		@Override
		public void remove(Carrier carrier) {

		}

		@Override
		public void remove() {

		}

		@Override
		public @NotNull String getName() {
			return Bukkit.getServer().getName();
		}

		@Override
		public UUID getId() {
			return UUID.randomUUID();
		}

		@Override
		public LabyrinthUser getUser() {
			return null;
		}

		@Override
		public ItemStack getHead() {
			return null;
		}

		@Override
		public Channel getChannel() {
			return Channel.GLOBAL;
		}

		@Override
		public Clan getClan() {
			return null;
		}

		@Override
		public Mailer getMailer() {
			return Mailer.empty();
		}

		@Override
		public @NotNull Claim[] getClaims() {
			return new Claim[0];
		}

		@Override
		public @Nullable Claim newClaim(Chunk c) {
			return null;
		}

		InvasiveEntity getEntity() {
			return this;
		}

		@Override
		public @NotNull Relation getRelation() {
			return new Relation() {


				@Override
				public @NotNull Alliance getAlliance() {
					return new Alliance() {
						@Override
						public @NotNull List<InvasiveEntity> get() {
							return null;
						}

						@Override
						public @NotNull <T extends InvasiveEntity> List<T> get(Class<T> cl) {
							return null;
						}

						@Override
						public void request(InvasiveEntity target) {

						}

						@Override
						public void request(InvasiveEntity target, String message) {

						}

						@Override
						public @NotNull List<InvasiveEntity> getRequests() {
							return null;
						}

						@Override
						public @NotNull <T extends InvasiveEntity> List<T> getRequests(Class<T> cl) {
							return null;
						}

						@Override
						public Teleport getTeleport(InvasiveEntity entity) {
							return null;
						}

						@Override
						public boolean isEmpty() {
							return false;
						}

						@Override
						public InvasiveEntity[] toArray() {
							return new InvasiveEntity[0];
						}

						@Override
						public <T extends InvasiveEntity> T[] toArray(T[] a) {
							return null;
						}

						@Override
						public <T extends InvasiveEntity> boolean has(T o) {
							return false;
						}

						@Override
						public <T extends InvasiveEntity> boolean hasAll(Collection<T> c) {
							return false;
						}

						@Override
						public <T extends InvasiveEntity> boolean addAll(Collection<T> c) {
							return false;
						}

						@Override
						public <T extends InvasiveEntity> boolean removeAll(Collection<T> c) {
							return false;
						}

						@Override
						public void add(InvasiveEntity entity) {

						}

						@Override
						public void remove(InvasiveEntity entity) {

						}

						@Override
						public int size() {
							return 0;
						}

						@Override
						public void clear() {

						}
					};
				}

				@Override
				public @NotNull Rivalry getRivalry() {
					return new Rivalry() {
						@Override
						public @NotNull List<InvasiveEntity> get() {
							return null;
						}

						@Override
						public @NotNull <T extends InvasiveEntity> List<T> get(Class<T> cl) {
							return null;
						}

						@Override
						public Teleport getTeleport(InvasiveEntity entity) {
							return null;
						}

						@Override
						public boolean isEmpty() {
							return false;
						}

						@Override
						public InvasiveEntity[] toArray() {
							return new InvasiveEntity[0];
						}

						@Override
						public <T extends InvasiveEntity> T[] toArray(T[] a) {
							return null;
						}

						@Override
						public <T extends InvasiveEntity> boolean has(T o) {
							return false;
						}

						@Override
						public <T extends InvasiveEntity> boolean hasAll(Collection<T> c) {
							return false;
						}

						@Override
						public <T extends InvasiveEntity> boolean addAll(Collection<T> c) {
							return false;
						}

						@Override
						public <T extends InvasiveEntity> boolean removeAll(Collection<T> c) {
							return false;
						}

						@Override
						public void add(InvasiveEntity entity) {

						}

						@Override
						public void remove(InvasiveEntity entity) {

						}

						@Override
						public int size() {
							return 0;
						}

						@Override
						public void clear() {

						}
					};
				}

				@Override
				public @NotNull InvasiveEntity getEntity() {
					return null;
				}

				@Override
				public boolean isNeutral(InvasiveEntity target) {
					return false;
				}
			};
		}

		@Override
		public @Nullable Teleport getTeleport() {
			return null;
		}

		@Override
		public int getClaimLimit() {
			return 0;
		}

		@Override
		public Optional<Resident> toResident() {
			return Optional.empty();
		}

		@Override
		public boolean isValid() {
			return false;
		}

		@Override
		public String getRankFull() {
			return null;
		}

		@Override
		public String getRankWordless() {
			return null;
		}

		@Override
		public RankPriority getPriority() {
			return null;
		}

		@Override
		public long getKilled(TimeUnit threshold, long time) {
			return 0;
		}

		@Override
		public String getNickname() {
			return null;
		}

		@Override
		public String getBiography() {
			return null;
		}

		@Override
		public Date getJoinDate() {
			return null;
		}

		@Override
		public double getKD() {
			return 0;
		}

		@Override
		public void setChannel(String chat) {

		}

		@Override
		public void setPriority(RankPriority priority) {

		}

		@Override
		public void setBio(String newBio) {

		}

		@Override
		public void setNickname(String newName) {

		}

		@Override
		public @NotNull Tag getTag() {
			return () -> this.getId().toString();
		}
	};

	private final Claim claim;

	public ClaimEvent(boolean isAsync) {
		super(dummy, isAsync);
		this.claim = null;
	}

	public ClaimEvent(Claim claim) {
		super(dummy, false);
		this.claim = claim;
	}

	public ClaimEvent(Clan.Associate associate, Claim claim) {
		super(associate, false);
		this.claim = claim;
	}

	public ClaimEvent(Clan.Associate associate, UUID id, Claim claim) {
		super(associate, id,false);
		this.claim = claim;
	}

	@Override
	public @Nullable Clan.Associate getAssociate() {
		return super.getAssociate();
	}

	/**
	 * Get the claim involved in this event.
	 *
	 * @return the claim or null.
	 */
	public Claim getClaim() {
		return claim;
	}
}