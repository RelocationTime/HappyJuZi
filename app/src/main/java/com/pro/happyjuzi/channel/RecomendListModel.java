package com.pro.happyjuzi.channel;

import com.pro.happyjuzi.bean.AuthorBeanInfo;
import com.pro.happyjuzi.bean.DetailBean;
import com.pro.happyjuzi.bean.ResponseRecommBean;
import com.pro.happyjuzi.bean.TanmuBean;
import com.pro.happyjuzi.utils.NetUtils;

import retrofit2.Call;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class RecomendListModel {


    public Call<ResponseRecommBean> getRecommendData(int page) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        return server.getRespnoseRecommendData(page);
    }

    public Call<TanmuBean> getRecommendTanmu(int id) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        return server.getRecommendTanmu(id);
    }

    public Call<ResponseRecommBean> getGossipData(int page) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        return server.getGossipRecommendData(page);
    }

    public Call<ResponseRecommBean> getVideoData(int page) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        return server.getVideoRecommendData(page);
    }

    public Call<AuthorBeanInfo> getRecommendAuthro(int id) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        Call<AuthorBeanInfo> call = server.getRecommendAuthor(id);
        return call;
    }
    public Call<ResponseRecommBean> getFashionData(int page) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        return server.getFashionRecommendData(page);
    }
    public Call<ResponseRecommBean> getLifeData(int page) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        return server.getLifeRecommendData(page);
    }


    public Call<DetailBean> getDetailInfo(int id) {
        RecommendServer server = NetUtils.getApi(RecommendServer.class);
        return server.getRecommendDetails(id);
    }

}
