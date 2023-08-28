package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.siegemob.SiegeMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class PActiveMob {
    public static HashMap<UUID, PActiveMob> livingMobMap = new HashMap<>();

    public PMob mob;
    public UUID uuid;
    public Location anchor;
    public HashMap<PJoint, ItemDisplay> entityMap;
    public HashMap<PJoint, List<PTransform>> transformListMap;
    public PAction action;
    public int actionTick = 0;

    public PActiveMob(PMob mob, Location l) {
        this.mob = mob;
        this.uuid = UUID.randomUUID();
        this.anchor = l;
        this.entityMap = new HashMap<>();
        this.transformListMap = new HashMap<>();
        this.action = null;
        this.actionTick = 0;
    }

    public void spawn() {
        for (PJoint pJ : this.mob.jointMap.keySet()) {
            if (entityMap.containsKey(pJ)) continue;
            Entity e = Objects.requireNonNull(anchor.getWorld()).spawnEntity(anchor, EntityType.ITEM_DISPLAY);
            ItemDisplay iD = (ItemDisplay) e;
            iD.setItemStack(new ItemStack(Material.COOKIE));
            iD.setTransformation(pJ.transform.getTransform());
            iD.setInterpolationDuration(1);
            iD.setInterpolationDelay(0);
            entityMap.put(pJ, iD);
        }
        livingMobMap.put(this.uuid, this);
    }

    public void update() {
        if (this.action == null) return;
        for (PJoint pJ : this.mob.jointMap.keySet()) {
            if (!entityMap.containsKey(pJ)) continue;
            if (!transformListMap.containsKey(pJ)) continue;
            if (actionTick > transformListMap.get(pJ).size() - 1) {
                transformListMap.remove(pJ);
                continue;
            }
            ItemDisplay iD = entityMap.get(pJ);
            iD.setTransformation(transformListMap.get(pJ).get(actionTick).getTransform());
            iD.setInterpolationDuration(1);
            iD.setInterpolationDelay(0);
        }
        if (transformListMap.isEmpty()) {
            this.action = null;
            this.actionTick = 0;
            return;
        }
        actionTick = actionTick + 1;
        SiegeMob.error(actionTick + " ");
    }

    public void action(PAction pA) {
        this.action = pA;
        this.actionTick = 0;
        this.transformListMap.clear();
        for (PAnimation pAnimation : this.action.animationList) {
            if (!this.mob.jointMap.containsKey(pAnimation.joint)) continue;
            this.transformListMap.put(pAnimation.joint, pAnimation.transformList);
        }
    }
}
