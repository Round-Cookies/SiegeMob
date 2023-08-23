package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.siegemob.util.PTransformDeserializer;
import me.asakura_kukii.siegemob.util.PTransformSerializer;

import java.util.ArrayList;
import java.util.List;

public class PAnimation {
    @JsonSerialize(contentUsing = PTransformSerializer.class)
    @JsonDeserialize(contentUsing = PTransformDeserializer.class)
    public List<PTransform> animation = new ArrayList<>();
}
