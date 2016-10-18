package com.pro.happyjuzi.detail;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
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

    public Handler mHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            Bitmap bitmap = (Bitmap) msg.obj;
            BitmapDrawable drawable = new BitmapDrawable(bitmap);
            bg.setBackgroundDrawable(drawable);
        }
    };

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
            info_title.setText(dataInfo.getName());
        }
    }

    @Override
    public void showBackground(final String imageUrl) {

        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithSource(Uri.parse(imageUrl)).setProgressiveRenderingEnabled(true).build();
        ImagePipeline imagePipeline = Fresco.getImagePipeline();
        DataSource<CloseableReference<CloseableImage>> dataSource = imagePipeline.fetchDecodedImage(imageRequest, this);
        dataSource.subscribe(new BaseBitmapDataSubscriber() {
            @Override
            public void onNewResultImpl(@Nullable Bitmap bitmap) {

                bitmap = blurImageAmeliorate(bitmap);
                Message message = mHandler.obtainMessage();
                message.obj = bitmap;
                mHandler.sendMessage(message);
            }
            @Override
            public void onFailureImpl(DataSource dataSource) {
            }
        }, CallerThreadExecutor.getInstance());
    }

    @Override
    public void setImageBitMap(Bitmap bitmap) {
        BitmapDrawable drawable = new BitmapDrawable(bitmap);
        bg.setBackgroundDrawable(drawable);
    }
    public static Bitmap blurImageAmeliorate(Bitmap bmp)
    {
        long start = System.currentTimeMillis();
        // 高斯矩阵
        int[] gauss = new int[] { 1, 2, 1, 2, 4, 2, 1, 2, 1 };

        int width = bmp.getWidth();
        int height = bmp.getHeight();
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        int pixR = 0;
        int pixG = 0;
        int pixB = 0;

        int pixColor = 0;

        int newR = 0;
        int newG = 0;
        int newB = 0;
        int delta = 35; // 值越小图片会越亮，越大则越暗
        int idx = 0;
        int[] pixels = new int[width * height];
        bmp.getPixels(pixels, 0, width, 0, 0, width, height);
        for (int i = 1, length = height - 1; i < length; i++)
        {
            for (int k = 1, len = width - 1; k < len; k++)
            {
                idx = 0;
                for (int m = -1; m <= 1; m++)
                {
                    for (int n = -1; n <= 1; n++)
                    {
                        pixColor = pixels[(i + m) * width + k + n];
                        pixR = Color.red(pixColor);
                        pixG = Color.green(pixColor);
                        pixB = Color.blue(pixColor);

                        newR = newR + (int) (pixR * gauss[idx]);
                        newG = newG + (int) (pixG * gauss[idx]);
                        newB = newB + (int) (pixB * gauss[idx]);
                        idx++;
                    }
                }

                newR /= delta;
                newG /= delta;
                newB /= delta;

                newR = Math.min(255, Math.max(0, newR));
                newG = Math.min(255, Math.max(0, newG));
                newB = Math.min(255, Math.max(0, newB));

                pixels[i * width + k] = Color.argb(255, newR, newG, newB);

                newR = 0;
                newG = 0;
                newB = 0;
            }
        }

        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        long end = System.currentTimeMillis();
        return bitmap;
    }
}
