package com.example.myapplication;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FoodDataCentralApi {
    private static final String BASE_URL = "https://api.nal.usda.gov/fdc/v1/";
    private static final String API_KEY = "EKBvBURy3O3nZ3rRoCMZwKnH4T4pgUfV0zik3KaP";
    private static final String SEARCH_ENDPOINT = "foods/search";
    private static final String DETAILS_ENDPOINT = "food";

    private static final String NUTRIENT_NUM = "nutrients=205&nutrients=203&nutrients=204&nutrients=401&nutrients=208";

    private final OkHttpClient httpClient;
    private final Gson gson;

    public FoodDataCentralApi() {
        httpClient = new OkHttpClient();
        gson = new Gson();
    }

    public List<Food> searchFoods(String query) throws IOException {
        String url = String.format("%s%s?api_key=%s&query=%s", BASE_URL, SEARCH_ENDPOINT, API_KEY, query);
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        String json = response.body().string();
        Log.i("Query result: ", json);
        FoodSearchResult result = gson.fromJson(json, FoodSearchResult.class);
        return result.getFoods();
    }

    public Food getFoodDetails(long id) throws IOException {
        String url = String.format("%s%s/%d?%s&api_key=%s", BASE_URL, DETAILS_ENDPOINT, id, NUTRIENT_NUM, API_KEY);
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();
        int resCode = response.code();
        Log.d("Response code: ", Integer.toString(resCode));
        String json = response.body().string();
        Log.i("Response JSON: ", json);
        return gson.fromJson(json, Food.class);
    }

}

