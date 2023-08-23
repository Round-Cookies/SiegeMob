package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import org.bukkit.util.Transformation;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.List;

public class PTransform {

    public PTransform() {}

    public PTransform(List<Float> parameter) {
        this.translation = new Vector3f(parameter.get(0), parameter.get(1), parameter.get(2));
        this.quaternion = new Quaternionf(parameter.get(3), parameter.get(4), parameter.get(5), parameter.get(6));
        this.scale = new Vector3f(parameter.get(7), parameter.get(8), parameter.get(9));
    }
    public Vector3f translation = new Vector3f(0, 0, 0);
    public Quaternionf quaternion = new Quaternionf(0, 0, 0, 1);
    public Vector3f scale = new Vector3f(0, 0, 0);

    public Transformation toTransformation() {
        return new Transformation(translation, quaternion, scale, new Quaternionf(0, 0, 0, 1));
    }
}
