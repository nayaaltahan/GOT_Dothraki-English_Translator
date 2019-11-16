package com.example.got.rds;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("https://api.funtranslations.com/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static DothrakiApi dothrakiApi = retrofit.create(DothrakiApi.class);

    public static DothrakiApi getDothrakiApi() {
        return dothrakiApi;
    }
}