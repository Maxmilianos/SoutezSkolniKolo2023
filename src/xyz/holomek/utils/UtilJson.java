package xyz.holomek.utils;

import com.google.gson.Gson;

public class UtilJson {

    private static Gson gson = new Gson();

    public static Object jsonToObject(String data, Class c) {
        return  gson.fromJson(data, c);
    }

    public static String objectToJson(Object o) {
        return gson.toJson(o);
    }


}
