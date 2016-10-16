package com.pro.happyjuzi.channel;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FashionFragment extends BaseFragment {


    public static int time= 1;
    public FashionFragment() {
        // Required empty public constructor
    }

    @Override
    public void selectTypeRecommen() {
        presenter.start(1,CAT_FASHION);
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void loadMore() {
        presenter.getLoadMore(++time);
    }


}
