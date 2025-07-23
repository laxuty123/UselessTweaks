package me.laxuty.uselesstweaks;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public final class SharedConstants {

    public static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .create();
}