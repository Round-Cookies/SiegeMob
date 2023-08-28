package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.core.JsonProcessingException;
import me.asakura_kukii.lib.jackson.databind.ObjectMapper;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.siegecore.io.PFile;
import me.asakura_kukii.siegecore.io.PFileIdDeserializer;
import me.asakura_kukii.siegecore.io.PFileIdSerializer;
import me.asakura_kukii.siegecore.io.PType;
import me.asakura_kukii.siegemob.SiegeMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PMob extends PFile {

    public static PMob test;

    public PMob(String id, File file, PType type) {
        super(id, file, type);
    }

    public PMob() {}

    @JsonSerialize(contentUsing = PFileIdSerializer.class)
    @JsonDeserialize(contentUsing = PFileIdDeserializer.class)
    public List<PJoint> jointList = new ArrayList<>();

    @JsonSerialize(contentUsing = PFileIdSerializer.class)
    @JsonDeserialize(contentUsing = PFileIdDeserializer.class)
    public List<PAction> actionList = new ArrayList<>();

    @Override
    public void finalizeDeserialization() {
        test = this;
    }
}
