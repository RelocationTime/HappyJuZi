package com.pro.happyjuzi.channel;


import android.support.v4.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoFragment extends RecommendFragment {


    public VideoFragment() {
        // Required empty public constructor
    }

    @Override
    public void selectTypeRecommen() {
        presenter.start(1,BaseFragment.CAT_VIDEO);

    }
}
