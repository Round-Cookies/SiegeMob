package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.core.JsonProcessingException;
import me.asakura_kukii.lib.jackson.databind.ObjectMapper;
import me.asakura_kukii.siegecore.io.PFile;
import me.asakura_kukii.siegecore.io.PType;
import me.asakura_kukii.siegemob.SiegeMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.util.HashMap;
import java.util.Objects;

public class PMob extends PFile {

    public static PMob test;

    public PMob(String id, File file, PType type) {
        super(id, file, type);
    }

    public PMob() {}

    public PJoint root = new PJoint();

    public HashMap<Integer, String> testMap = new HashMap<>();

    @Override
    public String serialize() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    @Override
    public void finalizeDeserialization() {
        SiegeMob.error(root.transform.translation.toString());
        testMap.put(1, "123123");
        test = this;
    }

    public void spawn(Location l) {
        recursiveSpawn(root, l);
    }

    public void recursiveSpawn(PJoint root, Location l) {
        Entity e = Objects.requireNonNull(l.getWorld()).spawnEntity(l, EntityType.ITEM_DISPLAY);
        ItemDisplay iD = (ItemDisplay) e;
        iD.setItemStack(new ItemStack(Material.COOKIE));
        iD.setInterpolationDuration(200);
        iD.setTransformation(root.transform.toTransformation());
        for (PJoint pJ : root.children) {
            recursiveSpawn(pJ, l);
        }
    }
}
