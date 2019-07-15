package com.fnhelper.fnapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fnhelper.fnapp.data.BaseResult;
import com.fnhelper.fnapp.data.PhoneBean;
import com.fnhelper.fnapp.interfaces.RetrofitService;
import com.fnhelper.fnapp.utils.KShareViewActivityManager;

import java.util.ArrayList;
import java.util.Arrays;

import cn.bingoogolapple.bgabanner.BGABanner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fnhelper.fnapp.interfaces.RetrofitService.phoneId;

public class PhoneDetailAc extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_detail);
        initHeader();
        getDetailHttp();
    }

    @Override
    public void onBackPressed() {
        KShareViewActivityManager.getInstance(PhoneDetailAc.this).finish(PhoneDetailAc.this);
    }


    private BGABanner mContentBanner;

    private void  initHeader(){
        mContentBanner = findViewById(R.id.imgs);
        mContentBanner.setAdapter(new BGABanner.Adapter<ImageView, String>() {
            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(PhoneDetailAc.this)
                        .load(model)
                        .placeholder(R.mipmap.bg)
                        .error(R.mipmap.bg)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
            }
        });



    }


    private void getDetailHttp(){


        RetrofitService.createMyAPI().getPhoneDetail(phoneId).enqueue(new Callback<BaseResult<ArrayList<PhoneBean>>>() {

            @Override
            public void onResponse(Call<BaseResult<ArrayList<PhoneBean>>> call, Response<BaseResult<ArrayList<PhoneBean>>> response) {

                System.out.println(call.request().toString());
                System.out.println(response.body().toString());

                if (response.isSuccessful()) {
                    if (RetrofitService.RESULT_OK.equals(response.body().getCode())){
                        final PhoneBean phoneBean = response.body().getData().get(0);
                        ((TextView)findViewById(R.id.timeToMarket)).setText(phoneBean.getTimeToMarket());
                        ((TextView)findViewById(R.id.phoneSize)).setText(phoneBean.getPhoneSize());
                        ((TextView)findViewById(R.id.phoneScreenSize)).setText(phoneBean.getPhoneScreenSize());
                        ((TextView)findViewById(R.id.screenResolution)).setText(phoneBean.getScreenResolution());
                        ((TextView)findViewById(R.id.RearCamera)).setText(phoneBean.getRearCamera());
                        ((TextView)findViewById(R.id.FrontCamera)).setText(phoneBean.getFrontCamera());
                        ((TextView)findViewById(R.id.os)).setText(phoneBean.getOs());
                        ((TextView)findViewById(R.id.ram_rom)).setText(phoneBean.getRam()+phoneBean.getRom());
                        ((TextView)findViewById(R.id.sellPoint)).setText(phoneBean.getSellPoint());
                        ((TextView)findViewById(R.id.RelatedArticles)).setText(phoneBean.getRelatedArticles().split(",")[0]);
                        ((TextView)findViewById(R.id.RelatedArticles)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              String x =  phoneBean.getRelatedArticles().split(",")[1];
                            }
                        });
                        mContentBanner.setData(Arrays.asList(phoneBean.getPics().split(",")),Arrays.asList("市场价: "+phoneBean.getPrize()));
                    }
                }


            }

            @Override
            public void onFailure(Call<BaseResult<ArrayList<PhoneBean>>> call, Throwable t) {

            }

        });
    }
}
