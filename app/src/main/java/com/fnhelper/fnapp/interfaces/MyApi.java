package com.fnhelper.fnapp.interfaces;


import com.fnhelper.fnapp.data.BaseResult;
import com.fnhelper.fnapp.data.PhoneBean;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
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

    // 获取手机详情
    @POST("/getPhonedDetail")
    Call<BaseResult<ArrayList<PhoneBean>>> getPhoneDetail(@Query("phoneId") String phoneId);

    // 添加对比
    @POST("/addContrast")
    Call<BaseResult<String>> addContrast(@Query("userId") String userId,@Query("phoneId") String phoneId);

    // 获取对比列表数量
    @GET("/addContrast")
    Call<BaseResult<String>> addContrast(@Query("userId") String userId);


    // 获取对比列表
    @POST("/getContrastList")
    Call<BaseResult<ArrayList<PhoneBean>>> getContrastList(@Query("userId") String userId);


}
