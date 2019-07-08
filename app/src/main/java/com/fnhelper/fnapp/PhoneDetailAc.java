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

        mContentBanner.setData(Arrays.asList( "http://imgm.cnmo-img.com.cn/cnmo_product/23/390/cezKWsNHOacW.jpg", "http://imgm5.cnmo-img.com.cn/cnmo_product/23/215/ceqU8oHYTTZ66.jpg","http://imgm9.cnmo-img.com.cn/cnmo_product/23/429/cedkomKvqblIY.jpg"), Arrays.asList( "提示文字2", "提示文字3","asd"));
    }


    private void getDetailHttp(){


        RetrofitService.createMyAPI().getPhoneDetail(phoneId).enqueue(new Callback<BaseResult<PhoneBean>>() {

            @Override
            public void onResponse(Call<BaseResult<PhoneBean>> call, Response<BaseResult<PhoneBean>> response) {

                System.out.println(call.request().toString());
                System.out.println(response.body().toString());

                if (response.isSuccessful()) {
                    if (RetrofitService.RESULT_OK.equals(response.body().getCode())){
                        final PhoneBean phoneBean = response.body().getData();
                        ((TextView)findViewById(R.id.timeToMarket)).setText(phoneBean.getTIMETOMARKET());
                        ((TextView)findViewById(R.id.phoneSize)).setText(phoneBean.getPHONESIZE());
                        ((TextView)findViewById(R.id.phoneScreenSize)).setText(phoneBean.getPHONESCREENSIZE());
                        ((TextView)findViewById(R.id.screenResolution)).setText(phoneBean.getSCREENRESOLUTION());
                        ((TextView)findViewById(R.id.RearCamera)).setText(phoneBean.getREARCAMERA());
                        ((TextView)findViewById(R.id.FrontCamera)).setText(phoneBean.getFRONTCAMERA());
                        ((TextView)findViewById(R.id.os)).setText(phoneBean.getOS());
                        ((TextView)findViewById(R.id.ram_rom)).setText(phoneBean.getRAM()+phoneBean.getROM());
                        ((TextView)findViewById(R.id.sellPoint)).setText(phoneBean.getSELLPOINT());
                        ((TextView)findViewById(R.id.RelatedArticles)).setText(phoneBean.getRELATEDARTICLES().split(",")[0]);
                        ((TextView)findViewById(R.id.RelatedArticles)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              String x =  phoneBean.getRELATEDARTICLES().split(",")[1];
                            }
                        });

                    }
                }


            }

            @Override
            public void onFailure(Call<BaseResult<PhoneBean>> call, Throwable t) {

            }

        });
    }
}
