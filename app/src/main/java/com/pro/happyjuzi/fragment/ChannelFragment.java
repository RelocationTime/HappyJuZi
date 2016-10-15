package com.pro.happyjuzi.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pro.happyjuzi.R;
import com.pro.happyjuzi.adapter.ChannelFragmentAdapter;
import com.pro.happyjuzi.channel.BaseFragment;
import com.pro.happyjuzi.channel.FashionFragment;
import com.pro.happyjuzi.channel.GossipFragment;
import com.pro.happyjuzi.channel.LifeFragment;
import com.pro.happyjuzi.channel.RecommendFragment;
import com.pro.happyjuzi.channel.VideoFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChannelFragment extends Fragment implements ViewPager.OnPageChangeListener {


    public RecyclerView recycle_view;
    public ViewPager viewPager;
    public TabLayout flowTab;
    public ArrayList<String> strings;

    public ChannelFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_channel, container, false);
        viewPager = ((ViewPager) view.findViewById(R.id.viewpager));
        viewPager.setOffscreenPageLimit(5);
        flowTab = ((TabLayout) view.findViewById(R.id.tablayout));
      //  flowTab.getBackground().setAlpha(0);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

       // toolbar.getBackground().setAlpha(0);
        BaseFragment aFragment = new RecommendFragment();
        BaseFragment bFragment = new GossipFragment();
        BaseFragment cFragment = new VideoFragment();
        BaseFragment dFragment = new LifeFragment();
        BaseFragment eFragment = new FashionFragment();
        List<Fragment> list = new ArrayList<>();
        list.add(aFragment);
        list.add(bFragment);
        list.add(cFragment);
        list.add(dFragment);
        list.add(eFragment);
        strings = new ArrayList<>();
        strings.add("推荐");
        strings.add("八卦");
        strings.add("影视");
        strings.add("时尚");
        strings.add("生活");
        viewPager.setAdapter(new ChannelFragmentAdapter(getChildFragmentManager(),list, strings));
        flowTab.setupWithViewPager(viewPager);
        viewPager.setOnPageChangeListener(this);
        return view;
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        TabLayout.Tab tab = flowTab.getTabAt(position);

//        Toast.makeText(getContext(), view.getText().toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageSelected(int position) {

    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
