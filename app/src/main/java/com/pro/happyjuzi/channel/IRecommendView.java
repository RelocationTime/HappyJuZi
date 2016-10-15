package com.pro.happyjuzi.channel;

import com.pro.happyjuzi.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/13 0013.
 */

public interface IRecommendView {

    void loadMore(ArrayList<BaseBean> list);

    void loadSucess();

    void showProgress();

    void hideProgress();

    void showRecommendList(List<BaseBean> list);

    void showNoNet();
}
