package com.fnhelper.fnapp;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

/**
 * 自定义Application用来做一些初始化配置
 */
public class MyApplication extends Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 初始化fresco
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(instance)
                .setDownsampleEnabled(true)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .build();
      //  Fresco.initialize(this, config);

    //Fresco.initialize(this);

    }


}

