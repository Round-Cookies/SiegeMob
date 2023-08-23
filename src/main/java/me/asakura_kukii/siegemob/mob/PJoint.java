package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.siegemob.util.PTransformDeserializer;
import me.asakura_kukii.siegemob.util.PTransformSerializer;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PJoint {

    public PJoint() {}

    public String id = "joint";

    @JsonSerialize(using = PTransformSerializer.class)
    @JsonDeserialize(using = PTransformDeserializer.class)
    public PTransform transform = new PTransform();
    public HashMap<String, PAnimation> animation = new HashMap<>();

    public List<PJoint> children = new ArrayList<>();
}
