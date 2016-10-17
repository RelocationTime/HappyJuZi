package com.pro.happyjuzi.detail;

import com.pro.happyjuzi.bean.DetailBean;
import com.pro.happyjuzi.bean.TanmuBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/16 0016.
 */

public interface IDetailView {

    void showDetail_title(String title);

    void showAuthorID(int id);
    void showDetail_info_cata_name(String cataName);

    void showDetail_info_in_im(String image_url);

    void showDetail_info_title(String title);

    void showDetail_content(ArrayList<String> titles,List<DetailBean.DataBean.ResourcesBean.IMGBean> list);

    void showDetail_info_author_username(String info_author_username);

    void showDetail_info_publish_time(String info_publish_time);

    void showAnimaotion();

    void hideAnimation();

    void showTanmu(List<TanmuBean.DataBean.ListBean> list);
}
