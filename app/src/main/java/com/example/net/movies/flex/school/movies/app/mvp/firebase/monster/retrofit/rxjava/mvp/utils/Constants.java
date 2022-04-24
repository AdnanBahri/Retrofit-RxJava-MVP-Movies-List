package com.example.net.movies.flex.school.movies.app.mvp.firebase.monster.retrofit.rxjava.mvp.utils;

import java.util.HashMap;
import java.util.Map;

public class Constants {

    public static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w500";
    public static final String API_KEY = "eea817b734ed288ab6730d4787451043";
    private static HashMap<String, String> options;

    public static HashMap<String, String> getOptions(int page) {
        options = new HashMap<>();
        options.put("language", "en-US");
        options.put("page", "" + page);
        return options;
    }
}
