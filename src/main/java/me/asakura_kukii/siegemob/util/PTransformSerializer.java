package me.asakura_kukii.siegemob.util;

import me.asakura_kukii.lib.jackson.core.JsonGenerator;
import me.asakura_kukii.lib.jackson.databind.JsonSerializer;
import me.asakura_kukii.lib.jackson.databind.SerializerProvider;
import me.asakura_kukii.siegemob.mob.PTransform;
import org.joml.Matrix4f;

import java.io.IOException;

public class PTransformSerializer extends JsonSerializer<PTransform> {
    @Override
    public void serialize(PTransform transform, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        double[] vector = new double[7];
        vector[0] = transform.quaternion.x;
        vector[1] = transform.quaternion.y;
        vector[2] = transform.quaternion.z;
        vector[3] = transform.quaternion.w;

        vector[4] = transform.translation.x;
        vector[5] = transform.translation.y;
        vector[6] = transform.translation.z;

        jsonGenerator.writeArray(vector, 0, 7);
    }
}
