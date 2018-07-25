package com.zhxh.xanim;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

public class AnimateFreeFall extends Activity {

    private int screenHeight;
    private int screenWidth;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(com.zhxh.xanim.R.layout.animate_free_fall);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        screenHeight = metrics.heightPixels;
        screenWidth = metrics.widthPixels;

        imageView = (ImageView) findViewById(R.id.im);
    }

    public void clean(View view) {
        imageView.setTranslationX(0);
        imageView.setTranslationY(0);
    }

    public void freefall(View view) {
        final ValueAnimator animator = ValueAnimator.ofFloat(0, screenHeight
                - imageView.getHeight());
        animator.setTarget(view);
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(1000).start();
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                imageView.setTranslationY(value);
            }
        });
    }

    public void parabola(View view) {
        ValueAnimator animator = ValueAnimator.ofObject(
                new TypeEvaluator<PointF>() {

                    @Override
                    public PointF evaluate(float fraction, PointF arg1,
                                           PointF arg2) {
                        PointF p = new PointF();
                        p.x = fraction * screenWidth;
                        p.y = fraction * fraction * 0.5f * screenHeight * 4f
                                * 0.5f;
                        return p;
                    }
                }, new PointF(0, 0));
        animator.setDuration(800);
        animator.setInterpolator(new LinearInterpolator());
        animator.start();
        animator.addUpdateListener(new AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                PointF p = (PointF) animator.getAnimatedValue();
                imageView.setTranslationX(p.x);
                imageView.setTranslationY(p.y);
            }
        });
    }
}
