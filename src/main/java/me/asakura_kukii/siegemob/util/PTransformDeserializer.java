package me.asakura_kukii.siegemob.util;

import me.asakura_kukii.lib.jackson.core.JsonParser;
import me.asakura_kukii.lib.jackson.core.JsonToken;
import me.asakura_kukii.lib.jackson.databind.DeserializationContext;
import me.asakura_kukii.lib.jackson.databind.JsonDeserializer;
import me.asakura_kukii.siegemob.mob.PTransform;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PTransformDeserializer extends JsonDeserializer<PTransform> {
    @Override
    public PTransform deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        if (jsonParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IOException("Could not interpret PTransform " + jsonParser.getCurrentLocation().toString());
        }
        List<Float> vector = new ArrayList<>();
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {
            vector.add(jsonParser.getFloatValue());
        }
        if (vector.size() == 7) {
            return new PTransform(
                    new Quaternionf(vector.get(0), vector.get(1), vector.get(2), vector.get(3)),
                    new Vector3f(vector.get(4), vector.get(5), vector.get(6))
            );
        } else {
            throw new IOException("Could not interpret PTransform " + jsonParser.getCurrentLocation().toString());
        }
    }
}