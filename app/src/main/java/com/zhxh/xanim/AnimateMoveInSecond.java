package com.zhxh.xanim;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AnimateMoveInSecond extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_move_in_second);
        imageView = (ImageView) findViewById(R.id.imageView1);
    }

    public void doit(View view) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "alpha",
                1.0f, 0f);
        animator.setDuration(1000);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                ObjectAnimator animator = ObjectAnimator.ofFloat(imageView,
                        "alpha", 0f, 1.0f);
                animator.setDuration(1000);
                animator.start();
                imageView.setTranslationY(400);
            }
        });
    }
}
