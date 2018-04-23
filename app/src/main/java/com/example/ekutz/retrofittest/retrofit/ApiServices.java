package com.example.ekutz.retrofittest.retrofit;

import com.example.ekutz.retrofittest.response.GetParkInfo.ResponseParkInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiServices {

    @GET("{start}/{end}/{location}")
    Call<ResponseParkInfo> getParkInfo(
            @Path("start") int start,
            @Path("end") int end,
            @Path("location") String location
    );
}
