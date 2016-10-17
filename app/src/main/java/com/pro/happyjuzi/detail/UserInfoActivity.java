package com.pro.happyjuzi.detail;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pro.happyjuzi.R;
import com.pro.happyjuzi.adapter.UserInfoAdapter;
import com.pro.happyjuzi.bean.AuthorBeanInfo;
import com.pro.happyjuzi.channel.RecomendPresenter;
import com.pro.happyjuzi.utils.MyImageViewHandler;
import com.pro.happyjuzi.utils.OnCompleImage;

import java.util.ArrayList;
import java.util.List;

public class UserInfoActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener,IAuthorView, OnCompleImage {

    public RecyclerView info_recycle;
    public AppBarLayout appbar;
    public RecomendPresenter presenter;
    public SimpleDraweeView header_icon;
    public TextView header_name;
    public TextView header_desc;
    public UserInfoAdapter adapter;
    public RelativeLayout bg;
    public MyImageViewHandler myImageViewHandler;
    public TextView info_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_info);
        info_recycle = (RecyclerView) findViewById(R.id.info_recycle);
        info_recycle.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        appbar.addOnOffsetChangedListener(this);
        int id = getIntent().getIntExtra("id", 0);
        info_title = (TextView) findViewById(R.id.info_title);
        bg = (RelativeLayout) findViewById(R.id.info_bg);
        if (id != 0) {
            presenter = new RecomendPresenter(this,this);
            presenter.getAuthor(id);
        }

        header_icon = (SimpleDraweeView) findViewById(R.id.header_icon);
        header_name = (TextView) findViewById(R.id.header_name);
        header_desc = (TextView) findViewById(R.id.header_desc);
        List<AuthorBeanInfo.DataBean.ListBean> listBeen = new ArrayList<>();
        adapter = new UserInfoAdapter(this,listBeen);
        info_recycle.setAdapter(adapter);
    }

    private static final String TAG = "UserInfoActivity";
    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        Log.e(TAG, "onOffsetChanged: " + verticalOffset );
        int range = appbar.getTotalScrollRange();
        verticalOffset = ((int) Math.abs(verticalOffset));
        float v = (float) (verticalOffset / range);
        info_title.setTextColor(Color.argb((int)v*255,255,255,255));
    }

    @Override
    public void showAuthor(List<AuthorBeanInfo.DataBean.ListBean> listBeen) {
        if (listBeen != null) {
            adapter.addList(listBeen);
        }
    }

    @Override
    public void showDataList(AuthorBeanInfo.DataBean.InfoBean dataInfo) {

        RoundingParams params = RoundingParams.fromCornersRadius(7f);
        params.setBorder(Color.WHITE, 2.7f);
        params.setRoundAsCircle(true);
        header_icon.getHierarchy().setRoundingParams(params);

        if (dataInfo != null) {
            header_desc.setText(dataInfo.getDesc() + "");
            header_name.setText("---  " + dataInfo.getName() + " ---");
            if (dataInfo.getImg() != null) {
                header_icon.setImageURI(dataInfo.getImg());
            }

        }
    }

    @Override
    public void showBackground(final String imageUrl) {
        myImageViewHandler = new MyImageViewHandler(this, imageUrl);
        myImageViewHandler.start();
    }

    @Override
    public void setImageBitMap(Bitmap bitmap) {
        BitmapDrawable drawable = new BitmapDrawable(bitmap);
        bg.setBackgroundDrawable(drawable);
    }





}
