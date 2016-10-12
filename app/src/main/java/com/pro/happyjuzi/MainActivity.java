package com.pro.happyjuzi;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.pro.happyjuzi.fragment.ChannelFragment;
import com.pro.happyjuzi.fragment.DiscoverFragment;
import com.pro.happyjuzi.fragment.MineFragment;
import com.pro.happyjuzi.fragment.SubscribeFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mTabChannel;
    private LinearLayout mTabMine;
    private LinearLayout mTabSubject;
    private LinearLayout mTabDiscover;

    private ImageButton mImgChannel;
    private ImageButton mImgMine;
    private ImageButton mImgSubject;
    private ImageButton mImgDicover;

    private TextView mTxtChannel;
    private TextView mTxtMine;
    private TextView mTxtSubject;
    private TextView mTxtDicover;


    private Fragment mFragmentChannel;
    private Fragment mFragmentSubject;
    private Fragment mFragmentMine;
    private Fragment mFragmentDiscover;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initAllView();
        initAllListener();
        setFramgent(0);
    }

    private void initAllListener() {
        mTabChannel.setOnClickListener(this);
        mTabSubject.setOnClickListener(this);
        mTabMine.setOnClickListener(this);
        mTabDiscover.setOnClickListener(this);
    }

    private void initAllView() {

        mTabChannel = (LinearLayout) findViewById(R.id.id_tab_channel);
        mTabMine = (LinearLayout) findViewById(R.id.id_tab_mine);
        mTabDiscover = (LinearLayout) findViewById(R.id.id_tab_discover);
        mTabSubject = (LinearLayout) findViewById(R.id.id_tab_subscribe);

        mImgChannel = (ImageButton) mTabChannel.findViewById(R.id.id_tab_channel_img);
        mImgDicover = (ImageButton) mTabDiscover.findViewById(R.id.id_tab_discover_img);
        mImgMine = (ImageButton) mTabMine.findViewById(R.id.id_tab_mine_img);
        mImgSubject = (ImageButton) mTabSubject.findViewById(R.id.id_tab_subscribe_img);

        mTxtChannel = (TextView) mTabChannel.findViewById(R.id.txt_channel);
        mTxtDicover = (TextView) mTabDiscover.findViewById(R.id.txt_discover);
        mTxtMine = (TextView) mTabMine.findViewById(R.id.txt_mine);
        mTxtSubject = (TextView) mTabSubject.findViewById(R.id.txt_subject);

    }

    @Override
    public void onClick(View v) {
        setImageDark();
        switch (v.getId()) {
            case R.id.id_tab_channel:
                setFramgent(0);
                break;
            case R.id.id_tab_subscribe:
                setFramgent(1);
                break;
            case R.id.id_tab_discover:
                setFramgent(2);
                break;
            case R.id.id_tab_mine:
                setFramgent(3);
                break;
            default:
                break;
        }
    }

    private void setFramgent(int i) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(beginTransaction);
        switch (i) {
            case 0:
                if (mFragmentChannel == null) {
                    mFragmentChannel = new ChannelFragment();
                    beginTransaction.add(R.id.fragment_container, mFragmentChannel);
                } else {
                    beginTransaction.show(mFragmentChannel);
                }
                mImgChannel.setImageResource(R.mipmap.ic_navi_channel_checked);
                mTxtChannel.setTextColor(getResources().getColor(R.color.colorTabTextSeletor));
                break;

            case 1:
                if (mFragmentSubject == null) {
                    mFragmentSubject = new SubscribeFragment();
                    beginTransaction.add(R.id.fragment_container, mFragmentSubject);
                } else {
                    beginTransaction.show(mFragmentSubject);
                }
                mImgSubject.setImageResource(R.mipmap.ic_navi_sub_checked);
                mTxtSubject.setTextColor(getResources().getColor(R.color.colorTabTextSeletor));
                break;
            case 2:
                if (mFragmentDiscover == null) {
                    mFragmentDiscover = new DiscoverFragment();
                    beginTransaction.add(R.id.fragment_container, mFragmentDiscover);
                } else {
                    beginTransaction.show(mFragmentDiscover);
                }
                mImgDicover.setImageResource(R.mipmap.ic_new_navi_discover_checked);
                mTxtDicover.setTextColor(getResources().getColor(R.color.colorTabTextSeletor));
                break;

            case 3:
                if (mFragmentMine == null) {
                    mFragmentMine = new MineFragment();
                    beginTransaction.add(R.id.fragment_container, mFragmentMine);
                } else {
                    beginTransaction.show(mFragmentMine);
                }
                mImgMine.setImageResource(R.mipmap.ic_navi_profile_checked);
                mTxtMine.setTextColor(getResources().getColor(R.color.colorTabTextSeletor));

                break;

        }
        beginTransaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (mFragmentChannel != null) {
            transaction.hide(mFragmentChannel);
        }
        if (mFragmentSubject != null) {
            transaction.hide(mFragmentSubject);
        }
        if (mFragmentDiscover != null) {
            transaction.hide(mFragmentDiscover);
        }
        if (mFragmentMine != null) {
            transaction.hide(mFragmentMine);
        }
    }


    private void setImageDark() {
        /**
         * 切换图片至暗色
         */
        mImgChannel.setImageResource(R.mipmap.ic_navi_channel_normal_dark);
        mTxtChannel.setTextColor(getResources().getColor(R.color.colorTabTextNormal));
        mImgMine.setImageResource(R.mipmap.ic_navi_profile_normal_dark);
        mTxtMine.setTextColor(getResources().getColor(R.color.colorTabTextNormal));
        mImgDicover.setImageResource(R.mipmap.ic_new_navi_discover_normal_dark);
        mTxtDicover.setTextColor(getResources().getColor(R.color.colorTabTextNormal));
        mImgSubject.setImageResource(R.mipmap.ic_navi_sub_normal_dark);
        mTxtSubject.setTextColor(getResources().getColor(R.color.colorTabTextNormal));

    }
}
