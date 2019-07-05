package com.fnhelper.fnapp.interfaces;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * <pre>
 *     网络请求引引擎类，包含网络拦截器，包含网络缓存策略
 *     <pre/>
 */

public class RetrofitService {

    //设置 数据的缓存时间，有效期为两天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    //查询缓存的Cache-Control 配置，为 only-if-cache 时，只查询缓存而不会请求服务器， max-stale可以配合设置缓存失效时间
    protected static final String CACHE_CONTROL_CACHE = "only-if-cache, max-stale=" + CACHE_STALE_SEC;
    //查询缓存的Cache-Control配置，为 Cache-Control设置为max-age=0时则不会使用缓存，而是请求服务器
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    /**
     * 单例模式的 OKhttpClient,以及Retrifit引擎类
     */
    private static OkHttpClient mOkHttpClient;
    private static RetrofitService instance = null;

    //私有构造函数，使用newInstance方式访问对象(单利RetrofitService)
    private RetrofitService() {
    }

    //单利 引擎
    public static RetrofitService getInstance() {
        if (instance == null) {
            synchronized (RetrofitService.class) {
                if (instance == null) {
                    instance = new RetrofitService();
                }
            }
        }
        return instance;
    }



    public static String userId = "";
    public static String RESULT_OK = "200";

    /**
     * API
     */
    private volatile static MyApi myApi = null;

    public static MyApi createMyAPI() {
        if (myApi == null) {
            synchronized (RetrofitService.class) {
                if (myApi == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder()
                            //设置超时时间
                            .retryOnConnectionFailure(false  );
                            //设置日志拦截器



                    onHttps(builder);
                    OkHttpClient okHttpClient = builder.build();
                    //创建Retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                           // .baseUrl(BuildConfig.YBSX_COMMON_API)
                         .baseUrl("https://sy.ybveg.com/")
                        // .baseUrl("https://syx.ybveg.com/")
                          // .baseUrl("http://sy.ybveg.com/")
                        .baseUrl("http://172.16.2.123:8080/")
                            //   .baseUrl("http://172.16.5.66:85/")
                            //    .baseUrl("http://172.16.2.39:85/")
                            //  .baseUrl("http://172.16.4.209:85/")
                            .client(okHttpClient)
                            //添加Gson转换器
                            .addConverterFactory(GsonConverterFactory.create())
                            //添加RxJava连接器
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .build();
                    myApi = retrofit.create(MyApi.class);

                }


            }
        }
        return myApi;
    }


    /**
     * 获取支持https的SocketFactory
     * @return
     * @throws Exception
     */
    private static SSLSocketFactory getSSLSocketFactory() throws Exception {
        //创建一个不验证证书链的证书信任管理器。
        final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(
                    java.security.cert.X509Certificate[] chain,
                    String authType) throws CertificateException {
            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        final SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts,
                new java.security.SecureRandom());
        // Create an ssl socket factory with our all-trusting manager
        return sslContext
                .getSocketFactory();
    }
    private static void onHttps(OkHttpClient.Builder builder) {
        try {
            builder.sslSocketFactory(getSSLSocketFactory()).hostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
