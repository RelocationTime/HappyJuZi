package com.pro.happyjuzi.channel;

import com.pro.happyjuzi.bean.ResponseRecommBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public interface RecommendServer {

    @GET("article/list/home?ts=0&startup=0&mac=68-3e-34-80-6d-8b&uid=4029527336798670&pf=android&net=wifi&accesstoken=82ff5a67a57d104572e7c38c803dbf95&channel=qihoo360&ver=3.4&res=1080x1920")
    Call<ResponseRecommBean> getRespnoseRecommendData(@Query("page") int page);

    @GET("article/list/channel?id=27&ts=0&startup=0&mac=68-3e-34-80-6d-8b&uid=4029527336798670&pf=android&net=wifi&accesstoken=82ff5a67a57d104572e7c38c803dbf95&channel=qihoo360&ver=3.4&res=1080x1920")
    Call<ResponseRecommBean> getGossipRecommendData(@Query("page") int page);

    @GET("article/list/channel?id=32&ts=0&startup=0&mac=68-3e-34-80-6d-8b&uid=4029527336798670&pf=android&net=wifi&accesstoken=82ff5a67a57d104572e7c38c803dbf95&channel=qihoo360&ver=3.4&res=1080x1920")
    Call<ResponseRecommBean> getVideoRecommendData(@Query("page") int page);


    @GET("article/list/channel?id=26&ts=0&startup=0&mac=68-3e-34-80-6d-8b&uid=4029527336798670&pf=android&net=wifi&accesstoken=82ff5a67a57d104572e7c38c803dbf95&channel=qihoo360&ver=3.4&res=1080x1920")
    Call<ResponseRecommBean> getFashionRecommendData(@Query("page") int page);

    @GET("article/list/channel?id=61&ts=0&startup=0&mac=68-3e-34-80-6d-8b&uid=4029527336798670&pf=android&net=wifi&accesstoken=82ff5a67a57d104572e7c38c803dbf95&channel=qihoo360&ver=3.4&res=1080x1920")
    Call<ResponseRecommBean> getLifeRecommendData(@Query("page") int page);
}

