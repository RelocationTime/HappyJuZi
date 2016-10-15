package com.pro.happyjuzi.channel;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class LifeFragment extends RecommendFragment {


    public LifeFragment() {
        // Required empty public constructor
    }


    @Override
    public void selectTypeRecommen() {
        presenter.start(1,BaseFragment.CAT_LIFE);
    }
}
