package me.asakura_kukii.siegemob.util;

import me.asakura_kukii.lib.jackson.core.JsonGenerator;
import me.asakura_kukii.lib.jackson.databind.JsonSerializer;
import me.asakura_kukii.lib.jackson.databind.SerializerProvider;
import org.joml.Matrix4f;

import java.io.IOException;

public class Matrix4FSerializer extends JsonSerializer<Matrix4f> {
    @Override
    public void serialize(Matrix4f matrix, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        double[] vector = new double[16];
        vector[0] = matrix.get(0, 0);
        vector[1] = matrix.get(1, 0);
        vector[2] = matrix.get(2, 0);
        vector[3] = matrix.get(3, 0);
        vector[4] = matrix.get(0, 1);
        vector[5] = matrix.get(1, 1);
        vector[6] = matrix.get(2, 1);
        vector[7] = matrix.get(3, 1);
        vector[8] = matrix.get(0, 2);
        vector[9] = matrix.get(1, 2);
        vector[10] = matrix.get(2, 2);
        vector[11] = matrix.get(3, 2);
        vector[12] = matrix.get(0, 3);
        vector[13] = matrix.get(1, 3);
        vector[14] = matrix.get(2, 3);
        vector[15] = matrix.get(3, 3);
        jsonGenerator.writeArray(vector, 0, 16);
    }
}

