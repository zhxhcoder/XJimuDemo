package com.zhxh.xanim;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AnimateMultiple extends Activity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_multiple);
        imageView = (ImageView) findViewById(R.id.imageM);
    }


    public void start(View view) {
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView, "scaleX",
                1f, 2f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView, "scaleY",
                1f, 2f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView,
                "translationY", 0f, 500f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(1000);
        set.playTogether(animator1, animator2, animator3);
        set.start();
    }
}
