package com.fnhelper.fnapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fnhelper.fnapp.data.BaseResult;
import com.fnhelper.fnapp.data.PhoneBean;
import com.fnhelper.fnapp.interfaces.RetrofitService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPhoneListHttp();
    }


    private void getPhoneListHttp(){
        RetrofitService.createMyAPI().getPhoneList().enqueue(new Callback<BaseResult<ArrayList<PhoneBean>>>() {
            @Override
            public void onResponse(Call<BaseResult<ArrayList<PhoneBean>>> call, Response<BaseResult<ArrayList<PhoneBean>>> response) {

                System.out.println(call.request().toString());
                System.out.println(response.body().toString());


            }/*
                if (response.isSuccessful()) {
                    if (RetrofitService.RESULT_OK.equals(response.body().getCode())){
                        userId = response.body().getData();
                        Toast.makeText(MainActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();

                    }
                }*/

            @Override
            public void onFailure(Call<BaseResult<ArrayList<PhoneBean>>> call, Throwable t) {
            }
        });
    }
}
