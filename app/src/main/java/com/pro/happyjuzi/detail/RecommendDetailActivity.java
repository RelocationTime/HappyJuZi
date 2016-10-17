package com.pro.happyjuzi.detail;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.pro.happyjuzi.R;
import com.pro.happyjuzi.bean.DetailBean;
import com.pro.happyjuzi.channel.RecomendPresenter;
import com.pro.happyjuzi.tanmu.AnimationHelper;
import com.pro.happyjuzi.tanmu.ScreenUtils;
import com.pro.happyjuzi.tanmu.TanmuBean;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import static com.pro.happyjuzi.R.id.info_author_username;
import static com.pro.happyjuzi.R.id.info_publish_time;

public class RecommendDetailActivity extends AppCompatActivity implements IDetailView, View.OnClickListener {

    public TextView author_username;
    public TextView cata_name;
    public TextView publish_time;
    public TextView info_title;
    public SimpleDraweeView in_img;
    public ImageView btn_back;
    public RecomendPresenter presenter;
    public ProgressBar view;
    public LinearLayout txt_img_content;

    private MyHandler handler;

    //弹幕内容
    private TanmuBean tanmuBean;
    //放置弹幕内容的父组件
    private RelativeLayout containerVG;

    //父组件的高度
    private int validHeightSpace;
    public View startTanmuView;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommend_detail);
        presenter = new RecomendPresenter(this,this);
        int id = getIntent().getIntExtra("id", 0);
        presenter.getDetailInfo(id);
        presenter.getTanmu(id);
        initView();
        containerVG = (RelativeLayout) findViewById(R.id.tanmucontaier);
        tanmuBean = new TanmuBean();

        handler = new MyHandler(this);
        startTanmuView = findViewById(R.id.piao);
        startTanmuView.setOnClickListener(this);
    }

    private void initView() {
        author_username = ((TextView) findViewById(info_author_username));
        author_username.setOnClickListener(this);
        cata_name = (TextView) findViewById(R.id.info_cata_name);
        publish_time = (TextView) findViewById(info_publish_time);
        info_title = (TextView) findViewById(R.id.info_title);
        in_img = (SimpleDraweeView) findViewById(R.id.in_img);
        btn_back = (ImageView) findViewById(R.id.btn_back);
        txt_img_content = (LinearLayout) findViewById(R.id.content_image_string);
    }

    @Override
    public void showDetail_title(String title) {
        info_title.setText(title+"");
    }

    @Override
    public void showAuthorID(int id) {
        this.id = id;
    }

    @Override
    public void showDetail_info_cata_name(String cataName) {
        cata_name.setText(cataName+"");
    }

    @Override
    public void showDetail_info_in_im(String image_url) {
        if (image_url != null) {

            PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder();
            builder.setUri(Uri.parse(image_url));
            builder.setAutoPlayAnimations(true);
            in_img.setController(builder.build());

        }
    }

    @Override
    public void showDetail_info_title(String title) {
        info_title.setText(title+"");
    }

    @Override
    public void showDetail_content(ArrayList<String> titls,List<DetailBean.DataBean.ResourcesBean.IMGBean> imgUrl) {
        TextView tx = null;
        SimpleDraweeView img = null;

        for (int i = 0; i < titls.size(); i++) {
            tx = new TextView(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tx.setLayoutParams(params);
            tx.setText("\t\t"+Html.fromHtml(titls.get(i)));
            if (i == imgUrl.size()) {
                break;
            }
            if (imgUrl.get(i).getThumb() != null) {

                img = new SimpleDraweeView(this);
                img.setMinimumHeight(imgUrl.get(i).getHeight());
                img.setMinimumWidth(imgUrl.get(i).getWidth());
                img.setImageURI(imgUrl.get(i).getThumb());
                img.setPadding(15,30,15,30);
                img.setAdjustViewBounds(true);
                PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder();
                builder.setUri(Uri.parse(imgUrl.get(i).getThumb()));
                builder.setAutoPlayAnimations(true);
                img.setController(builder.build());
            }

            txt_img_content.addView(tx);
            if (imgUrl.get(i).getThumb() != null) {
                txt_img_content.addView(img);
            }


        }
    }

    @Override
    public void showDetail_info_author_username(String info_author_username) {
        author_username.setText(info_author_username+"");
    }

    @Override
    public void showDetail_info_publish_time(String info_publish_time) {
        publish_time.setText(info_publish_time+"");
    }

    @Override
    public void showAnimaotion() {
        view = (ProgressBar) findViewById(R.id.loading);
    }

    @Override
    public void hideAnimation() {
        findViewById(R.id.appbar).setVisibility(View.VISIBLE);
        btn_back.setVisibility(View.VISIBLE);
        view.setVisibility(View.GONE);
    }

    @Override
    public void showTanmu(List<com.pro.happyjuzi.bean.TanmuBean.DataBean.ListBean> list) {

        String[] strings1 = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            strings1[i] = list.get(i).getContent();
        }

        tanmuBean.setItems(strings1);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.piao:
            {
                if (containerVG.getChildCount() > 0) {
                    return;
                }

                existMarginValues.clear();
                new Thread(new CreateTanmuThread()).start();
            }
                break;
            case R.id.info_author_username:
                Intent user = new Intent(this, UserInfoActivity.class);
                user.putExtra("id", id);
                startActivity(user);
                break;
        }

    }

    private class CreateTanmuThread implements Runnable {
        @Override
        public void run() {
            if (tanmuBean != null) {
                int N = tanmuBean.getItems().length;
                for (int i = 0; i < N; i++) {
                    handler.obtainMessage(1, i, 0).sendToTarget();
                    SystemClock.sleep(2000);
                }

            }
        }
    }

    //需要在主线城中添加组件
    private static class MyHandler extends Handler {
        private WeakReference<RecommendDetailActivity> ref;

        MyHandler(RecommendDetailActivity ac) {
            ref = new WeakReference<>(ac);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (msg.what == 1) {
                RecommendDetailActivity ac = ref.get();
                if (ac != null && ac.tanmuBean != null) {
                    int index = msg.arg1;
                    String content = ac.tanmuBean.getItems()[index];
                    float textSize = (float) (ac.tanmuBean.getMinTextSize() * (1 + Math.random() * ac.tanmuBean.getRange()));
                    int textColor = ac.tanmuBean.getColor();

                    Log.e(TAG, "handleMessage: "+ textColor);
                    ac.showTanmu(content, textSize, Color.RED);
                }
            }
        }
    }

    private static final String TAG = "RecommendDetailActivity";
    private void showTanmu(String content, float textSize, int textColor) {
        final TextView textView = (TextView) LayoutInflater.from(this).inflate(R.layout.tanmu_tx, containerVG, false);
        //textView.setTextSize(textSize);
        textView.setText(content);
//        textView.setSingleLine();
        int color = (int) (Math.random() * 250);
        Random random = new Random();
        int ranColor = 0xff000000 | random.nextInt(0x00ffffff);
        textView.setTextColor(ranColor);
        //textView.setBackgroundColor(textColor-10);
      //  textView.setPadding(3,3,3,3);
        int leftMargin = containerVG.getRight() - containerVG.getLeft() - containerVG.getPaddingLeft();
        //计算本条弹幕的topMargin(随机值，但是与屏幕中已有的不重复)
        int verticalMargin = getRandomTopMargin();
        textView.setTag(verticalMargin);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        params.topMargin = verticalMargin;
        textView.setLayoutParams(params);
        textView.setGravity(Gravity.CENTER_HORIZONTAL);

        Animation anim = AnimationHelper.createTranslateAnim(this, leftMargin, -ScreenUtils.getScreenW(this));
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //移除该组件
                containerVG.removeView(textView);
                //移除占位
                int verticalMargin = (int) textView.getTag();
                existMarginValues.remove(verticalMargin);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        textView.startAnimation(anim);

        containerVG.addView(textView);
    }

    //记录当前仍在显示状态的弹幕的位置（避免重复）
    private Set<Integer> existMarginValues = new HashSet<>();
    private int linesCount;

    private int getRandomTopMargin() {
        //计算用于弹幕显示的空间高度
        if (validHeightSpace == 0) {
            validHeightSpace = containerVG.getBottom() - containerVG.getTop()
                    - containerVG.getPaddingTop() - containerVG.getPaddingBottom();
        }

        //计算可用的行数
        if (linesCount == 0) {
            linesCount = validHeightSpace / ScreenUtils.dp2px(this, tanmuBean.getMinTextSize() * (1 + tanmuBean.getRange()));
            if (linesCount == 0) {
                throw new RuntimeException("Not enough space to show text.");
            }
        }

        //检查重叠
        while (true) {
            int randomIndex = (int) (Math.random() * linesCount);
            int marginValue = randomIndex * (validHeightSpace / linesCount);

            if (!existMarginValues.contains(marginValue)) {
                existMarginValues.add(marginValue);
                return marginValue;
            }
        }
    }
}
