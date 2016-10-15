package com.pro.happyjuzi.channel;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommendFragment extends BaseFragment{


    public static int time = 1;
    public RecommendFragment() {

    }


    @Override
    public void selectTypeRecommen() {
        presenter.start(1,0);
    }

    @Override
    public void loadMore() {
        presenter.getLoadMore(++time);
    }
}
