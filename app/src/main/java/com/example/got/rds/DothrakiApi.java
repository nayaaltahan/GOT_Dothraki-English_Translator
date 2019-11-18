package com.example.got.rds;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DothrakiApi {

    //https://api.funtranslations.com/translate/dothraki.json?text=Have%20you%20seen%20my%20lady%E2%80%99s%20dragon%3F
    //total/{artist}
    @POST("translate/dothraki.json")
    Call<DothrakiResponse> TranslateToDothraki( @Query("text")String text);
}
