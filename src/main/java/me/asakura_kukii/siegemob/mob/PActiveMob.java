package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.siegecore.item.PDeco;
import me.asakura_kukii.siegemob.SiegeMob;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.Vector3f;

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
            PDeco pD = this.mob.jointMap.get(pJ);
            if (pD == null) continue;
            Entity e = Objects.requireNonNull(anchor.getWorld()).spawnEntity(anchor, EntityType.ITEM_DISPLAY);
            ItemDisplay iD = (ItemDisplay) e;
            iD.setItemStack(pD.getItemStack(1));
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
            Transformation bias = this.action.biasList.get(actionTick).getTransform();
            ItemDisplay iD = entityMap.get(pJ);
            Transformation transform = transformListMap.get(pJ).get(actionTick).getTransform();
            Vector3f translation = new Vector3f(transform.getTranslation().x + bias.getTranslation().x, transform.getTranslation().y + bias.getTranslation().y, transform.getTranslation().z + bias.getTranslation().z);
            transform = new Transformation(translation, transform.getLeftRotation(), transform.getScale(), transform.getRightRotation());
            iD.setTransformation(transform);
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
