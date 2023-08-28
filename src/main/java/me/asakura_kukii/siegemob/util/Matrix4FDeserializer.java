package me.asakura_kukii.siegemob.util;

import me.asakura_kukii.lib.jackson.core.JsonParser;
import me.asakura_kukii.lib.jackson.core.JsonToken;
import me.asakura_kukii.lib.jackson.databind.DeserializationContext;
import me.asakura_kukii.lib.jackson.databind.JsonDeserializer;
import org.joml.Matrix4f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Matrix4FDeserializer extends JsonDeserializer<Matrix4f> {
    @Override
    public Matrix4f deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        if (jsonParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IOException("Could not interpret Matrix4F " + jsonParser.getCurrentLocation().toString());
        }
        List<Float> vector = new ArrayList<>();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            vector.add(jsonParser.getFloatValue());
        }
        if (vector.size() == 16) {
            return new Matrix4f(
                    vector.get(0), vector.get(4), vector.get(8), vector.get(12),
                    vector.get(1), vector.get(5), vector.get(9), vector.get(13),
                    vector.get(2), vector.get(6), vector.get(10), vector.get(14),
                    vector.get(3), vector.get(7), vector.get(11), vector.get(15)
            );
        } else {
            throw new IOException("Could not interpret Matrix4F " + jsonParser.getCurrentLocation().toString());
        }
    }
}