package com.fnhelper.fnapp.interfaces;


import com.fnhelper.fnapp.data.BaseResult;
import com.fnhelper.fnapp.data.PhoneBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by ls on 2016/5/12.
 */

public interface MyApi {


    // 注册
    @POST("/regist")
    Call<BaseResult<String>> regist(@Query("userName") String userName,@Query("password") String password);

    // 获取首页手机列表
    @POST("/getPhoneList")
    Call<BaseResult<ArrayList<PhoneBean>>> getPhoneList();


}
