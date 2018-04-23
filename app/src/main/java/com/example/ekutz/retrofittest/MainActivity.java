package com.example.ekutz.retrofittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.ekutz.retrofittest.response.GetParkInfo.ResponseParkInfo;
import com.example.ekutz.retrofittest.retrofit.ApiServices;
import com.example.ekutz.retrofittest.retrofit.RetrofitUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RetrofitUtil retrofitUtil;
    ApiServices apiServices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRetrofit();

        String gangnam = textToUtf8("강남");

        apiServices.getParkInfo(1, 5, gangnam).enqueue(new Callback<ResponseParkInfo>() {
            @Override
            public void onResponse(Call<ResponseParkInfo> call, Response<ResponseParkInfo> response) {
                ((TextView)findViewById(R.id.textTest)).setText(response.body().getGetParkInfo().getRow().get(0).getPARKING_NAME());
            }

            @Override
            public void onFailure(Call<ResponseParkInfo> call, Throwable t) {

            }
        });
    }

    private void setRetrofit() {
        retrofitUtil = RetrofitUtil.getInstance();

        apiServices = retrofitUtil.getApiServices();
    }

    private String textToUtf8(String str) {
        String utf8Str = "";
        try {
            utf8Str = URLEncoder.encode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {

            Log.w("utf8 Encoding", "UTF-8 Encoding Error");
            e.printStackTrace();
        }

        return utf8Str;
    }
}
