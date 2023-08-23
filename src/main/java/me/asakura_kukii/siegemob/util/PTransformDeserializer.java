package me.asakura_kukii.siegemob.util;

import me.asakura_kukii.lib.jackson.core.JsonParser;
import me.asakura_kukii.lib.jackson.core.JsonToken;
import me.asakura_kukii.lib.jackson.databind.DeserializationContext;
import me.asakura_kukii.lib.jackson.databind.JsonDeserializer;
import me.asakura_kukii.siegemob.mob.PTransform;
import org.joml.Quaternionf;

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
        if (vector.size() == 10) {
            return new PTransform(vector);
        } else {
            throw new IOException("Could not interpret PTransform " + jsonParser.getCurrentLocation().toString());
        }
    }
}