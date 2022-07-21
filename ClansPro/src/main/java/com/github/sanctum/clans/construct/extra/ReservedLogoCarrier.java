package com.github.sanctum.clans.construct.extra;

import com.github.sanctum.clans.bridge.ClanVentBus;
import com.github.sanctum.clans.construct.api.LogoHolder;
import com.github.sanctum.clans.event.insignia.InsigniaBuildCarrierEvent;
import com.github.sanctum.labyrinth.library.Entities;
import com.github.sanctum.labyrinth.library.LabyrinthEncoded;
import com.github.sanctum.labyrinth.library.StringUtils;
import com.github.sanctum.labyrinth.task.TaskScheduler;
import com.github.sanctum.panther.annotation.Ordinal;
import com.github.sanctum.panther.util.HUID;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.attribute.Attributable;
import org.bukkit.entity.ArmorStand;

public enum ReservedLogoCarrier implements LogoHolder.Carrier {

	MOTD("rO0ABXNyABNqYXZhLnV0aWuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAGdwQAAAAGdABswqc04paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqc04paRdACQwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paSwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paSwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRdACQwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqdBwqc2wqcxwqcywqc4wqdE4paTwqd4wqdBwqc2wqcxwqcywqc4wqdE4paTwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRdACQwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqdBwqc2wqcxwqcywqc4wqdE4paTwqd4wqdBwqc2wqcxwqcywqc4wqdE4paTwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRdACQwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paSwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqdBwqc2wqcxwqcywqc4wqdE4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paSwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRdABswqc04paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqd4wqcywqcxwqcwwqc5wqc0wqdG4paRwqc04paReA"),
	BIG_LANDSCAPE("rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAQdwQAAAAQdAG2wqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqc34paRwqc34paRwqc44paRwqc34paRwqc44paRdAG2wqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqdEwqdCwqdFwqdCwqczwqc04paTwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqc34paRwqc44paRwqc34paRwqc44paRwqc44paRdAG2wqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqdEwqdCwqdFwqdCwqczwqc04paTwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqc34paRwqc44paRwqc44paIwqc44paIwqc44paRdAGkwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqdEwqdCwqdFwqdCwqczwqc04paTwqd4wqdEwqdCwqdFwqdCwqczwqc04paTwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqc34paRwqc44paRwqc44paIwqc34paRwqc44paIwqc44paIdAGSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqc34paRwqc44paRwqc44paRwqc44paIwqc34paRwqc44paRwqc44paIdAHawqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqc44paIwqc44paIwqc44paIdAHswqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqc44paRwqc44paRdAGkwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqdBwqc0wqdGwqc1wqc0wqcy4paRwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqc34paRwqc44paIwqc44paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqc44paIwqc44paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paIwqc44paIdAGkwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqc34paRwqc44paIwqc44paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqc34paRwqc34paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paIwqc44paIdAHIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqc34paRwqc44paRwqc44paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqc44paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRdAG2wqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqc34paRwqc44paIwqc44paIwqc34paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqc44paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRdAG2wqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqc34paRwqc44paIwqc44paRwqc34paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqc44paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRdAIQwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paRdAIQwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paRdAIQwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqdFwqdCwqc2wqc4wqczwqc04paRdAIQwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paReA=="),
	SUMMER("rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAMdwQAAAAMdAD8wqd4wqdEwqdCwqdFwqdCwqczwqc04paSwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc44paRwqc34paIdAD8wqd4wqdEwqdCwqdFwqdCwqczwqc04paSwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqdEwqdCwqdFwqdCwqczwqc04paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc34paIwqc34paIdADYwqd4wqdEwqdCwqdFwqdCwqczwqc04paSwqd4wqdEwqdCwqdFwqdCwqczwqc04paSwqd4wqdEwqdCwqdFwqdCwqczwqc04paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc44paRwqc44paRwqc34paIwqc44paIdADYwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc44paIwqc34paIwqc44paIwqc44paRdADYwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paRwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc44paRwqc34paIwqc44paIwqc44paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paIdADqwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paTwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc44paIwqc34paIwqc44paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paIdADqwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc34paIwqc44paIwqc44paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paIdADqwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paIwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqd4wqcwwqc3wqdEwqcwwqdEwqdC4paSwqc44paIwqc34paIwqc44paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paTdAEgwqd4wqcwwqc3wqdEwqdCwqczwqc44paIwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqcwwqc3wqdEwqdCwqcwwqdC4paIwqd4wqdFwqdCwqc2wqc4wqczwqc04paSdAEgwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqcwwqc3wqdEwqdCwqczwqc44paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paSwqd4wqdFwqdCwqc2wqc4wqczwqc04paSdAEgwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paIwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqcwwqc3wqdBwqc2wqdEwqdC4paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paSdAEgwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paRwqd4wqc5wqc2wqc1wqc4wqcwwqc24paSwqd4wqdFwqdCwqc2wqc4wqczwqc04paRwqd4wqdFwqdCwqc2wqc4wqczwqc04paReA=="),
	HALLOWEEN("rO0ABXNyABNqYXZhLnV0aWwuQXJyYXlMaXN0eIHSHZnHYZ0DAAFJAARzaXpleHAAAAAQdwQAAAAQdACMIzIxMDk0ZuKWkSMyMTA5NGbilpIjNWYwN2Ri4paRJjTilpEmNOKWkiNhNjEyOGTilpEjYTYxMjhk4paRI2E2MTI4ZOKWkSNhNjEyOGTilpEjYTYxMjhk4paRI2E2MTI4ZOKWkSY04paSJjTilpEjNWYwN2Ri4paRIzIxMDk0ZuKWkiMyMTA5NGbilpF0AIwjMjEwOTRm4paRIzVmMDdkYuKWkSNlYjY4MzTilogmNOKWkSY04paSI2E2MTI4ZOKWkSNhNjEyOGTilpEjYTYxMjhk4paRI2E2MTI4ZOKWkSNhNjEyOGTilpEjYTYxMjhk4paRJjTilpIjZWI2ODM04paIJjTilpEjNWYwN2Ri4paRIzIxMDk0ZuKWkXQAkSM1ZjA3ZGLilpEmNOKWkSNlYjY4MzTilogjZWI2ODM04paIJjTilpIjZWI2ODM04paII2ViNjgzNOKWiCNlYjY4MzTilogjZWI2ODM04paII2ViNjgzNOKWiCNhNjEyOGTilpEjZWI2ODM04paII2ViNjgzNOKWiCY04paRIzVmMDdkYuKWkSMyMTA5NGbilpF0AJYjNWYwN2Ri4paRJjTilpEjZWI2ODM04paII2ViNjgzNOKWiCNlYjY4MzTilogjZWI2ODM04paTI2ViNjgzNOKWkyNlYjY4MzTilpMjZWI2ODM04paSI2ViNjgzNOKWkiNlYjY4MzTilogjZWI2ODM04paII2ViNjgzNOKWiCY04paRIzVmMDdkYuKWkSMyMTA5NGbilpF0AJEjNWYwN2Ri4paRJjTilpEjZWI2ODM04paII2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paTI2ViNjgzNOKWkiNlYjY4MzTilpIjZWI2ODM04paSI2ViNjgzNOKWkiNlYjY4MzTilpEjZWI2ODM04paRI2ViNjgzNOKWiCY04paRJjTilpEjNWYwN2Ri4paRdAB9JjTilpEmNOKWkSNlYjY4MzTilogjZWI2ODM04paTJjDilogjZWI2ODM04paSI2ViNjgzNOKWkiNlYjY4MzTilpIjZWI2ODM04paRI2ViNjgzNOKWkSYw4paII2ViNjgzNOKWkSNlYjY4MzTilogmNOKWkiY04paSJjTilpF0AHMmNOKWkiNlYjY4MzTilogjZWI2ODM04paTJjDilogmNOKWkSYw4paII2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paRJjDilogmNOKWkSYw4paII2ViNjgzNOKWkSNlYjY4MzTilogmNOKWkiY04paSdACbI2E2MTI4ZOKWkSNlYjY4MzTilogjZWI2ODM04paTI2ViNjgzNOKWkyNlYjY4MzTilpIjZWI2ODM04paSI2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paRI2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paRI2ViNjgzNOKWkSNlYjY4MzTilogjYTYxMjhk4paRJjTilpJ0AIwjYTYxMjhk4paRI2ViNjgzNOKWiCNlYjY4MzTilpMmMOKWiCYw4paII2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paRI2ViNjgzNOKWkSNlYjY4MzTilpEmMOKWiCYw4paII2ViNjgzNOKWkSNlYjY4MzTilogjYTYxMjhk4paRI2E2MTI4ZOKWkXQAeCY04paSI2ViNjgzNOKWiCNlYjY4MzTilpMmMOKWiCNkYmViMzTilpEmMOKWiCYw4paIJjDilogmMOKWiCYw4paII2RiZWIzNOKWkSYw4paII2ViNjgzNOKWkSNlYjY4MzTilogjYTYxMjhk4paRI2E2MTI4ZOKWkXQAhyY04paRI2ViNjgzNOKWiCNlYjY4MzTilpMjZWI2ODM04paSJjDilogjOTY1ODA24paRIzk2NTgwNuKWkSNkYmViMzTilpEjOTY1ODA24paRI2RiZWIzNOKWkSYw4paII2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paIJjTilpImNOKWknQAbiY04paRJjTilpEjZWI2ODM04paII2ViNjgzNOKWkiNlYjY4MzTilpEmMOKWiCYw4paIJjDilogmMOKWiCYw4paII2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paIJjTilpEmNOKWkSY04paRdACWIzVmMDdkYuKWkSY04paRI2ViNjgzNOKWiCNlYjY4MzTilpIjZWI2ODM04paRI2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paRI2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paRI2ViNjgzNOKWkSNlYjY4MzTilogmNOKWkSM1ZjA3ZGLilpEjNWYwN2Ri4paRdACWIzIxMDk0ZuKWkSM1ZjA3ZGLilpEmNOKWkSNlYjY4MzTilogjZWI2ODM04paII2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paRI2ViNjgzNOKWkSNlYjY4MzTilpEjZWI2ODM04paII2ViNjgzNOKWiCY04paRIzVmMDdkYuKWkSMyMTA5NGbilpIjMjEwOTRm4paRdACMIzIxMDk0ZuKWkSMyMTA5NGbilpIjNWYwN2Ri4paRJjTilpEmNOKWkiNlYjY4MzTilogjZWI2ODM04paII2ViNjgzNOKWiCNlYjY4MzTilogjZWI2ODM04paII2E2MTI4ZOKWkSY04paSJjTilpEjNWYwN2Ri4paRIzIxMDk0ZuKWkiMyMTA5NGbilpF0AIwjMjEwOTRm4paRIzVmMDdkYuKWkSY04paRJjTilpIjYTYxMjhk4paRI2E2MTI4ZOKWkSNhNjEyOGTilpEjYTYxMjhk4paRI2E2MTI4ZOKWkSNhNjEyOGTilpEjYTYxMjhk4paRI2E2MTI4ZOKWkSY04paSJjTilpEjNWYwN2Ri4paRIzIxMDk0ZuKWkXg=");

	final String logo;
	final LinkedHashSet<LogoHolder.Carrier.Line> lines = new LinkedHashSet<>();
	final HUID id = HUID.randomID();
	Location top;
	private Chunk chunk;

	ReservedLogoCarrier(String logo) {
		this.logo = logo;
	}

	public List<String> get() {
		return (List<String>) new LabyrinthEncoded(logo).deserialize(List.class);
	}

	public void add(Attributable attributable) throws IllegalArgumentException {
		if (attributable instanceof ArmorStand) {
			ReservedLogoCarrier.Line line = new ReservedLogoCarrier.Line((ArmorStand) attributable);
			if (this.chunk == null) {
				this.chunk = line.getStand().getLocation().getChunk();
			}
			lines.add(line);
		} else throw new IllegalArgumentException("Attribute cannot be used for line processing!");
	}

	@Override
	public Location getTop() {
		return top;
	}

	public Chunk getChunk() {
		return chunk;
	}

	@Override
	public LogoHolder getHolder() {
		return null;
	}

	@Ordinal
	HUID getRealId() {
		return id;
	}

	public String getId() {
		return id.toString().replace("-", "").substring(8);
	}

	public Set<LogoHolder.Carrier.Line> getLines() {
		return lines;
	}

	void remove(LogoHolder.Carrier.Line line) {
		lines.remove(line);
	}

	public void build(Location location) {
		double y = location.getY() + 0.5;
		List<String> t = new ArrayList<>(get());
		Collections.reverse(t);
		String[] l = t.toArray(new String[0]);
		for (int i = 0; i < l.length; i++) {
			y += 0.2;
			Location loc = new Location(location.getWorld(), location.getX(), y, location.getZ(), location.getYaw(), location.getPitch()).add(0.5, 0, 0.5);
			if (i == l.length - 1) {
				top = loc;
			}
			String name = StringUtils.use(l[i]).translate();
			InsigniaBuildCarrierEvent event = ClanVentBus.call(new InsigniaBuildCarrierEvent(this, i, l.length, name));
			if (!event.isCancelled()) {
				ArmorStand stand = Entities.ARMOR_STAND.spawn(loc, s -> {
					s.setVisible(false);
					s.setSmall(true);
					s.setMarker(true);
					s.setCustomNameVisible(true);
					s.setCustomName(event.getContent());
				});
				add(stand);
			}
		}
	}

	public void build(Location location, Consumer<ReservedLogoCarrier> consumer) {
		consumer.accept(this);
		build(location);
	}

	@Override
	public String toString() {
		return "Carrier{" + "world=" + getChunk().getWorld().getName() + ",x=" + getChunk().getX() + ",z=" + getChunk().getZ() + ",size=" + getLines().size() + ",id=" + getRealId().toString() + '}';
	}

	final class Line implements LogoHolder.Carrier.Line {

		private final ArmorStand line;
		private final int index;

		Line(ArmorStand stand) {
			this.line = stand;
			this.index = ReservedLogoCarrier.this.lines.size() + 1;
		}

		public HUID getId() {
			return ReservedLogoCarrier.this.id;
		}

		@Override
		public int getIndex() {
			return this.index;
		}

		public ArmorStand getStand() {
			return line;
		}

		public void destroy() {
			getStand().remove();
			TaskScheduler.of(() -> ReservedLogoCarrier.this.remove(this)).schedule();
		}

	}
}
