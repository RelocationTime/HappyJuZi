package com.pro.happyjuzi.pulltoheader;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.pro.happyjuzi.R;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

import static android.content.ContentValues.TAG;

/**
 * Created by Administrator on 2016/10/17 0017.
 */

public class CustomPullHeader extends FrameLayout implements PtrUIHandler {

    private ImageView airplance;
    private ImageView earth;

    public CustomPullHeader(Context context) {
        super(context);
        init();
    }

    private void init() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.recommend_load, this);
        earth = (ImageView) view.findViewById(R.id.earth);
        airplance = (ImageView) view.findViewById(R.id.airplance);
    }

    public void startAnimation() {
        ObjectAnimator animator = ObjectAnimator.ofFloat(earth, View.ROTATION, 0, 360);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.start();
        ObjectAnimator.ofFloat(airplance, View.ROTATION, 0, 120).setDuration(2000).start();
    }
    public CustomPullHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomPullHeader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    @Override
    public void onUIReset(PtrFrameLayout frame) {
        Log.e(TAG, "onUIReset: "  );
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {
        Log.e(TAG, "onUIRefreshPrepare: " );
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        Log.e(TAG, "onUIRefreshBegin: " );
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {
        Log.e(TAG, "onUIRefreshComplete: " );
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {
        Log.e(TAG, "onUIPositionChange: " );
    }
}
