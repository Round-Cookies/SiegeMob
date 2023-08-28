package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.siegemob.util.PTransformDeserializer;
import me.asakura_kukii.siegemob.util.PTransformSerializer;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class PTransform {

    public PTransform() {}

    public PTransform(Quaternionf quaternion, Vector3f translation) {
        this.quaternion = quaternion;
        this.translation = translation;
    }

    public Quaternionf quaternion = new Quaternionf(0, 0, 0, 1);
    public Vector3f translation = new Vector3f(0, 0, 0);

    public Transformation getTransform() {
        return new Transformation(translation, quaternion, new Vector3f(1, 1, 1), new Quaternionf());
    }
}
