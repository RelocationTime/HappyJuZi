package com.pro.happyjuzi.utils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/10/12.
 */

public class NetUtils {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    public static <T> T getApi(Class<T> tClass){
        Retrofit.Builder builder = new Retrofit.Builder();
        Retrofit retrofit = builder.baseUrl("http://api.app.happyjuzi.com")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(tClass);

    }

    public static String getInfo(){
        return "?mac=68-3e-34-80-6d-8b&uid=4029527336798670&pf=android&net=wifi&accesstoken=82ff5a67a57d104572e7c38c803dbf95&channel=qihoo360&ver=3.4&res=1080x1920";
    }

    public static String getMac(){
        return "68-3e-34-80-6d-8b&uid=4029527336798670";
    }

    public static String getUid(){
        return "4029527336798670";
    }


    public static String getPf(){
        return "android";
    }
    public static String getNet(){
        return "wifi";
    }
    public static String getAccesstoken(){
        return "82ff5a67a57d104572e7c38c803dbf95";
    }

    public static String getChannel(){
        return "qihoo360";
    }


    public static  float getVer(){
        return 3.4f;
    }
    public static String getRes(){
        return "1080x1920";
    }
}
