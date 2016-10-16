package com.pro.happyjuzi.channel;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.pro.happyjuzi.bean.BaseBean;
import com.pro.happyjuzi.bean.DetailBean;
import com.pro.happyjuzi.bean.InfoBean;
import com.pro.happyjuzi.bean.LuBean;
import com.pro.happyjuzi.bean.ReBean;
import com.pro.happyjuzi.bean.ResponseRecommBean;
import com.pro.happyjuzi.bean.TanmuBean;
import com.pro.happyjuzi.detail.IDetailView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public class RecomendPresenter {

    private IRecommendView view;
    private RecomendListModel model = new RecomendListModel();
    public Context context;
    public IDetailView detailView;
    public RecomendPresenter(IRecommendView view,Context context) {
        this.view = view;
        this.context = context;
    }

    public RecomendPresenter(IDetailView info_view, Context context) {
        this.detailView = info_view;
        this.context = context;
    }

    public void getTanmu(int id) {
        Call<TanmuBean> call = model.getRecommendTanmu(id);
        call.enqueue(new Callback<TanmuBean>() {
            @Override
            public void onResponse(Call<TanmuBean> call, Response<TanmuBean> response) {
                TanmuBean.DataBean data = response.body().getData();
                if (data != null) {
                    List<TanmuBean.DataBean.ListBean> list = data.getList();
                    if (list != null) {
                        for (int i = 0; i < list.size(); i++) {
                            Log.e(TAG, "onResponse: " + list.get(i).getContent() );
                        }
                        detailView.showTanmu(list);
                    }


                }
            }

            @Override
            public void onFailure(Call<TanmuBean> call, Throwable t) {

            }
        });
    }
    private static final String TAG = "RecomendPresenter";
    public void getDetailInfo(int id) {
        Call<DetailBean> call = model.getDetailInfo(id);
        detailView.showAnimaotion();
        call.enqueue(new Callback<DetailBean>() {
            @Override
            public void onResponse(Call<DetailBean> call, Response<DetailBean> response) {
                DetailBean body = response.body();
                DetailBean.DataBean bean = body.getData();
                if (bean != null) {
                    DetailBean.DataBean.InfoBean info = bean.getInfo();
                    detailView.hideAnimation();
                    getDetailTask(info);
                    String contents = body.getData().getContents();
                    ArrayList<String> titles = new ArrayList<>();
                    String[] split = contents.split("-->");
                    for (int i = 0 ; i < split.length; i++) {
                        titles.add(split[i]);
                    }
                    List<DetailBean.DataBean.ResourcesBean.IMGBean> imgBeanList = body.getData().getResources().getIMG();
                    detailView.showDetail_content(titles,imgBeanList);
                }
            }

            @Override
            public void onFailure(Call<DetailBean> call, Throwable t) {

            }
        });
    }


    private void getDetailTask(DetailBean.DataBean.InfoBean info) {
        if (info.getAuthor() != null) {
            detailView.showDetail_info_author_username(info.getAuthor().getUsername()+"");
        }

        if (info.getCat() != null) {
            detailView.showDetail_info_cata_name(info.getCat().getName());
        }

        detailView.showDetail_info_in_im(info.getImg());
        detailView.showDetail_info_publish_time(info.getPublish_time());
        detailView.showDetail_info_title(info.getTitle());
        detailView.showDetail_title(info.getTxtlead());
    }

    public void  getLoadMore(int time) {
        Call<ResponseRecommBean> call = model.getRecommendData(time);
        call.enqueue(new Callback<ResponseRecommBean>() {
            @Override
            public void onResponse(Call<ResponseRecommBean> call, Response<ResponseRecommBean> response) {
                ResponseRecommBean body = response.body();
                ResponseRecommBean.Data data = body.getData();
                List<ReBean> been = data.getList();
                ArrayList<BaseBean> baseBeen = new ArrayList<>();
                baseBeen.addAll(been);
                view.loadMore(baseBeen);
            }

            @Override
            public void onFailure(Call<ResponseRecommBean> call, Throwable t) {

            }
        });
    }
    public void start(int page, int type) {
        if (checkNet()) {
            view.showProgress();
            switch (type) {
                case BaseFragment.CAT_RECOMMEND:
                    getRecommendList(page);
                    break;
                case BaseFragment.CAT_GOSSIP:
                    getGossipList(page);
                    break;
                case BaseFragment.CAT_VIDEO:
                    getVideoList(page);
                    break;
                case BaseFragment.CAT_LIFE:
                    getLifeList(page);
                    break;
                case BaseFragment.CAT_FASHION:
                    getFashionList(page);
                    break;
            }
        } else {
            view.showNoNet();
        }

    }

    private void getGossipList(int page) {
        Call<ResponseRecommBean> recommendData = model.getGossipData(page);
        getResponseInfo(recommendData);
    }
    private void getFashionList(int page) {
        Call<ResponseRecommBean> recommendData = model.getFashionData(page);
        getResponseInfo(recommendData);
    }
    private void getLifeList(int page) {
        Call<ResponseRecommBean> recommendData = model.getLifeData(page);
        getResponseInfo(recommendData);
    }

    private void getVideoList(int page) {
        Call<ResponseRecommBean> recommendData = model.getVideoData(page);
        getResponseInfo(recommendData);
    }


    private void getRecommendList(int page) {
        Call<ResponseRecommBean> recommendData = model.getRecommendData(page);
        getResponseInfo(recommendData);
    }

    private void getResponseInfo(Call<ResponseRecommBean> recommendData) {
        recommendData.enqueue(new Callback<ResponseRecommBean>() {
            @Override
            public void onResponse(Call<ResponseRecommBean> call, Response<ResponseRecommBean> response) {
                ResponseRecommBean body = response.body();
                ResponseRecommBean.Data data = body.getData();
                List<LuBean> info = data.getInfo();
                InfoBean bean = new InfoBean();
                bean.setInfo(info);

                List<ReBean> dataList = data.getList();
                ArrayList<BaseBean> list = new ArrayList<>();

                list.add(bean);
                list.addAll(dataList);
                view.showRecommendList(list);
                view.hideProgress();
            }

            @Override
            public void onFailure(Call<ResponseRecommBean> call, Throwable t) {
                String string = call.request().url().toString();

            }
        });
    }

    private boolean checkNet() {

        ConnectivityManager manager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (manager != null) {
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                return true;
            } else {
                return false;
            }

        }

        return false;
    }
}
