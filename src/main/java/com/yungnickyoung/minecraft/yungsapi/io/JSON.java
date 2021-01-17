package com.yungnickyoung.minecraft.yungsapi.io;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yungnickyoung.minecraft.yungsapi.json.BiomeDictionaryTypeAdapter;
import com.yungnickyoung.minecraft.yungsapi.json.BlockSetSelectorAdapter;
import com.yungnickyoung.minecraft.yungsapi.json.BlockStateAdapter;
import com.yungnickyoung.minecraft.yungsapi.world.BlockSetSelector;
import net.minecraft.block.BlockState;
import net.minecraftforge.common.BiomeDictionary;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;

public class JSON {
    private JSON() {}

    private static Gson gson;

    // One-time gson initialization
    static  {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeHierarchyAdapter(BlockState.class, new BlockStateAdapter());
        gsonBuilder.registerTypeHierarchyAdapter(BlockSetSelector.class, new BlockSetSelectorAdapter());
        gsonBuilder.registerTypeHierarchyAdapter(BiomeDictionary.Type.class, new BiomeDictionaryTypeAdapter());
        gsonBuilder.setPrettyPrinting();
        gsonBuilder.disableHtmlEscaping();
        gson = gsonBuilder.create();
    }

    public static String toJsonString(Object object) {
        return gson.toJson(object);
    }

    public static void createJsonFileFromObject(Path path, Object object) throws IOException {
        String jsonString = gson.toJson(object);
        Files.write(path, jsonString.getBytes());
    }

    public static <T> T loadObjectFromJsonFile(Path path, Class<T> objectClass) throws IOException {
        Reader reader = Files.newBufferedReader(path);
        T jsonObject = gson.fromJson(reader, objectClass);
        reader.close();
        return jsonObject;
    }
}
