package com.korearbazar.ecom;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Api2 {
    public static Retrofit getClient1(){
        return new Retrofit.Builder()
                .baseUrl( "http://ecom.hrventure.xyz/api/")
                .addConverterFactory( GsonConverterFactory.create())
                .build();
    }
}
