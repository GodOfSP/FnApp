package com.fnhelper.fnapp;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
import static com.fnhelper.fnapp.interfaces.RetrofitService.userId;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private TextView contrastNum ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initRecyclerView();
        getPhoneListHttp();
        addContrast();
    }

    private void initView(){
        contrastNum = findViewById(R.id.startContrast);
        toolbar = findViewById(R.id.toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar_layout);
        mRecyclerView = findViewById(R.id.recyclerView);
        contrastNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ContrastAc.class));
            }
        });
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

            pic.setImageURI(Uri.parse(item.getPic()));
            helper.setText(R.id.name,item.getPhoneName());
            helper.setText(R.id.prize,item.getPrize());
            helper.setText(R.id.sellPoint,item.getSellPoint());

            //进入详情
            helper.setOnClickListener(R.id.pic, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    phoneId =  item.getId();
                    KShareViewActivityManager.getInstance(MainActivity.this).startActivity(MainActivity.this, PhoneDetailAc.class,R.layout.phone_list_item,R.layout.activity_phone_detail,pic);
                }
            });

            //加入对比
            helper.setOnClickListener(R.id.add_contrast, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addContrast(item.getId());
                }
            });


        }

    };



    private void initRecyclerView(){
        Fresco.initialize(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        mRecyclerView.setAdapter(adapter);

    }

    private void addContrast(String pId){

        RetrofitService.createMyAPI().addContrast(userId,pId).enqueue(new Callback<BaseResult<String>>() {
            @Override
            public void onResponse(Call<BaseResult<String>> call, Response<BaseResult<String>> response) {

                System.out.println(call.request().toString());
                System.out.println(response.body().toString());

                if (RetrofitService.RESULT_OK.equals(response.body().getCode())){
                    addContrast();
                    Toast.makeText(MainActivity.this,response.body().getMsg(),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResult<String>> call, Throwable t) {
            }
        });
    }

    private void addContrast(){

        RetrofitService.createMyAPI().addContrast(userId).enqueue(new Callback<BaseResult<String>>() {
            @Override
            public void onResponse(Call<BaseResult<String>> call, Response<BaseResult<String>> response) {

                System.out.println(call.request().toString());
                System.out.println(response.body().toString());

                if (response.isSuccessful()) {
                    if (RetrofitService.RESULT_OK.equals(response.body().getCode())){
                        contrastNum.setText("开始对比("+response.body().getData()+")");
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResult<String>> call, Throwable t) {
            }
        });
    }
}
