package com.auth0.trf.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public final class CommonUtil {
    public static final Gson GSON_MINIFY_PRINT = new GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create();
    public static final Gson GSON_BEAUTIFY_PRINT = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithoutExposeAnnotation()
            .create();

    public static final String PATH = System.getProperty("user.home") + "/Desktop";
    public static final String DIRECTORY = "/accounts";

    public static final String toMinifyJson(Object object) {
        return GSON_MINIFY_PRINT.toJson(object);
    }

    public static final String toBeautifyJson(Object object) {
        return GSON_BEAUTIFY_PRINT.toJson(object);
    }

    public static final int generateFiles(List<String> jsonQueries) throws IOException {
        new File(PATH + DIRECTORY).mkdir();
        int size = 1;
        for (String jsonQuery : jsonQueries) {
            String fileName = "/accounts-" + size + ".json";
            FileWriter file = new FileWriter(PATH + DIRECTORY + fileName);
            file.write(jsonQuery);
            file.close();
            size++;
        }

        size--;
        return size;
    }
}
