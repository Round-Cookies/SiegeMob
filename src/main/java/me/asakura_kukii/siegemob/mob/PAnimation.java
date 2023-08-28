package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.siegecore.io.PFileIdDeserializer;
import me.asakura_kukii.siegecore.io.PFileIdSerializer;
import me.asakura_kukii.siegemob.util.Matrix4FDeserializer;
import me.asakura_kukii.siegemob.util.Matrix4FSerializer;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.List;

public class PAnimation {
    @JsonSerialize(using = PFileIdSerializer.class)
    @JsonDeserialize(using = PFileIdDeserializer.class)
    public PJoint joint = null;

    @JsonSerialize(contentUsing = Matrix4FSerializer.class)
    @JsonDeserialize(contentUsing = Matrix4FDeserializer.class)
    public List<Matrix4f> matrixList = new ArrayList<>();
}
