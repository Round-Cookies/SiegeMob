package me.asakura_kukii.siegemob.mob;

import me.asakura_kukii.lib.jackson.databind.annotation.JsonDeserialize;
import me.asakura_kukii.lib.jackson.databind.annotation.JsonSerialize;
import me.asakura_kukii.siegecore.io.*;
import me.asakura_kukii.siegecore.item.PDeco;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PMob extends PFile {

    public static PMob test;

    public PMob(String id, File file, PType type) {
        super(id, file, type);
    }

    public PMob() {}

    @JsonSerialize(keyUsing = PFileIdKeySerializer.class, contentUsing = PFileIdSerializer.class)
    @JsonDeserialize(keyUsing = PFileIdKeyDeserializer.class, contentUsing = PFileIdDeserializer.class)
    public HashMap<PJoint, PDeco> jointMap = new HashMap<>();

    @JsonSerialize(contentUsing = PFileIdSerializer.class)
    @JsonDeserialize(contentUsing = PFileIdDeserializer.class)
    public List<PAction> actionList = new ArrayList<>();

    @Override
    public void finalizeDeserialization() {
        test = this;
    }
}
