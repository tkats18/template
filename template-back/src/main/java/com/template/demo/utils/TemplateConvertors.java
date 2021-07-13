package com.template.demo.utils;

import com.google.gson.Gson;

import java.util.List;
import java.util.Map;

@SuppressWarnings(value = "unchecked")
public class TemplateConvertors {

    public static String listToString(List<String> strings){
        return new Gson().toJson(strings);
    }

    public static List<String> stringToList(String labels) {
        return (List<String>)new Gson().fromJson(labels, List.class);
    }

    public static String mapToString(Map<String,String> map){
        return new Gson().toJson(map);
    }

    public static Map<String,String> stringToMap(String labels) {
        return (Map<String,String>)new Gson().fromJson(labels, Map.class);
    }
}
