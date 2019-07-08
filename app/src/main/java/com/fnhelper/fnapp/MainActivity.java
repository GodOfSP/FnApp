package com.fnhelper.fnapp;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.fnhelper.fnapp.data.BaseResult;
import com.fnhelper.fnapp.data.PhoneBean;
import com.fnhelper.fnapp.interfaces.RetrofitService;
import com.fnhelper.fnapp.utils.KShareViewActivityManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fnhelper.fnapp.interfaces.RetrofitService.phoneId;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRecyclerView();
        getPhoneListHttp();
    }

    private void initView(){

        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mRecyclerView = findViewById(R.id.recyclerView);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        //使用CollapsingToolbarLayout必须把title设置到CollapsingToolbarLayout上，设置到Toolbar上则不会显示
        collapsingToolbarLayout.setTitle("手机精选");
        //通过CollapsingToolbarLayout修改字体颜色
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);//设置还没收缩时状态下字体颜色
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);//设置收缩后Toolbar上字体的颜色

    }

    private void getPhoneListHttp(){
        RetrofitService.createMyAPI().getPhoneList().enqueue(new Callback<BaseResult<ArrayList<PhoneBean>>>() {
            @Override
            public void onResponse(Call<BaseResult<ArrayList<PhoneBean>>> call, Response<BaseResult<ArrayList<PhoneBean>>> response) {

                System.out.println(call.request().toString());
                System.out.println(response.body().toString());

                if (response.isSuccessful()) {
                    if (RetrofitService.RESULT_OK.equals(response.body().getCode())){
                        adapter.setNewData(response.body().getData());
                    }
                }


            }


            @Override
            public void onFailure(Call<BaseResult<ArrayList<PhoneBean>>> call, Throwable t) {
            }
        });
    }



    private BaseQuickAdapter<PhoneBean,BaseViewHolder> adapter = new BaseQuickAdapter<PhoneBean,BaseViewHolder> (R.layout.phone_list_item) {

        @Override
        protected void convert(BaseViewHolder helper, final PhoneBean item) {

           final SimpleDraweeView pic = helper.getView(R.id.pic);

            pic.setImageURI(Uri.parse(item.getPIC()));
            helper.setText(R.id.name,item.getPHONENAME());
            helper.setText(R.id.prize,item.getPRIZE());
            helper.setText(R.id.sellPoint,item.getSELLPOINT());

            helper.setOnClickListener(R.id.pic, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phoneId =  item.getID();
                    KShareViewActivityManager.getInstance(MainActivity.this).startActivity(MainActivity.this, PhoneDetailAc.class,R.layout.phone_list_item,R.layout.activity_phone_detail,pic);
                }
            });



        }

    };



    private void initRecyclerView(){
        Fresco.initialize(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(adapter);

    }
}
