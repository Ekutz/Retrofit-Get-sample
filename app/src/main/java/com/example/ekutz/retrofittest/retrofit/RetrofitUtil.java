package com.example.ekutz.retrofittest.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

    private static RetrofitUtil instance = null;

    private static ApiServices apiServices;

    private static String baseUrl = "http://openapi.seoul.go.kr:8088/694a71794777697332384f47585741/json/GetParkInfo/";

    private RetrofitUtil() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(ApiServices.class);
    }

    public static RetrofitUtil getInstance() {
        instance = null;

        if(instance==null) {
            instance = new RetrofitUtil();
        }

        return instance;
    }

    public static ApiServices getApiServices() {
        return apiServices;
    }
}
