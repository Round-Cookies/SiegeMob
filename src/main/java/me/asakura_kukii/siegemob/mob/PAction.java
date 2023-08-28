package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.core.JsonProcessingException;
import me.asakura_kukii.lib.jackson.databind.ObjectMapper;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.siegecore.io.PFile;
import me.asakura_kukii.siegemob.util.Matrix4FDeserializer;
import me.asakura_kukii.siegemob.util.Matrix4FSerializer;
import me.asakura_kukii.siegemob.util.PTransformDeserializer;
import me.asakura_kukii.siegemob.util.PTransformSerializer;
import org.joml.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PAction extends PFile {

    public static PAction test;

    public PAction() {}

    public int statusPrev = 0;
    public int statusNext = 0;
    public float triggerDistanceMin = -1F;
    public float triggerDistanceMax = -1F;
    public float triggerProbability = 0.2F;

    public List<Integer> rotateFrameList = new ArrayList<>();
    public float rotateMaxPerFrame = 10;
    public List<Integer> extendFrameList = new ArrayList<>();
    public float extendTarget = 0;
    public float extendDistanceMaxPerFrame = 2;

    @JsonSerialize(contentUsing = PTransformSerializer.class)
    @JsonDeserialize(contentUsing = PTransformDeserializer.class)
    public List<PTransform> biasList = new ArrayList<>();

    public List<PAnimation> animationList = new ArrayList<>();

    @Override
    public void finalizeDeserialization() {
        test = this;
    }
}
