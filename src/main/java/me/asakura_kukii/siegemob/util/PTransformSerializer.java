package me.asakura_kukii.siegemob.util;

import me.asakura_kukii.lib.jackson.core.JsonGenerator;
import me.asakura_kukii.lib.jackson.databind.JsonSerializer;
import me.asakura_kukii.lib.jackson.databind.SerializerProvider;
import me.asakura_kukii.siegemob.mob.PTransform;
import org.joml.Vector3f;

import java.io.IOException;

public class PTransformSerializer extends JsonSerializer<PTransform> {
    @Override
    public void serialize(PTransform pT, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        double[] vector = new double[10];
        vector[0] = pT.translation.x;
        vector[1] = pT.translation.y;
        vector[2] = pT.translation.z;
        vector[3] = pT.quaternion.x;
        vector[4] = pT.quaternion.y;
        vector[5] = pT.quaternion.z;
        vector[6] = pT.quaternion.w;
        vector[7] = pT.scale.x;
        vector[8] = pT.scale.y;
        vector[9] = pT.scale.z;
        jsonGenerator.writeArray(vector, 0, 10);
    }
}
