package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.siegecore.io.PFileIdDeserializer;
import me.asakura_kukii.siegecore.io.PFileIdSerializer;
import me.asakura_kukii.siegemob.util.Matrix4FDeserializer;
import me.asakura_kukii.siegemob.util.Matrix4FSerializer;
import me.asakura_kukii.siegemob.util.PTransformDeserializer;
import me.asakura_kukii.siegemob.util.PTransformSerializer;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class PAnimation {
    @JsonSerialize(using = PFileIdSerializer.class)
    @JsonDeserialize(using = PFileIdDeserializer.class)
    public PJoint joint = null;

    @JsonSerialize(contentUsing = PTransformSerializer.class)
    @JsonDeserialize(contentUsing = PTransformDeserializer.class)
    public List<PTransform> transformList = new ArrayList<>();
}
