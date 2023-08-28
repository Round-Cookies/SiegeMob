package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.core.JsonProcessingException;
import me.asakura_kukii.lib.jackson.databind.ObjectMapper;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.lib.jackson.databind.module.SimpleModule;
import me.asakura_kukii.siegecore.io.PFile;
import me.asakura_kukii.siegemob.util.Matrix4FDeserializer;
import me.asakura_kukii.siegemob.util.Matrix4FSerializer;
import me.asakura_kukii.siegemob.util.PTransformDeserializer;
import me.asakura_kukii.siegemob.util.PTransformSerializer;
import org.joml.Matrix4f;

public class PJoint extends PFile {

    public PJoint() {}

    @JsonSerialize(using = PTransformSerializer.class)
    @JsonDeserialize(using = PTransformDeserializer.class)
    public PTransform transform = new PTransform();

    @Override
    public void finalizeDeserialization() {}
}
