package com.zhxh.xanimlib;

import android.text.TextUtils;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by zhxh on 2018/7/3
 */
public class SocketAnimationTool {


    public synchronized static void viewSetAnimation(TextView textView, String refreshValue) {

        if (null != textView && !(textView.getText().toString()).equals("--") && !TextUtils.isEmpty(refreshValue) && !"--".equals(refreshValue)) {
            Double oldRefreshValue = Double.parseDouble(textView.getText().toString());

            textView.setText(refreshValue);

            if (oldRefreshValue != Double.parseDouble(refreshValue)) {
                Animation mAnimation = AnimationUtils.loadAnimation(textView.getContext(), R.anim.alpha_scoket);
                textView.startAnimation(mAnimation);
            }
        }
    }

    public synchronized static void viewSetAnimation1(TextView textView, String refreshValue) {

        if (null != textView && !(textView.getText().toString()).equals("--") && !TextUtils.isEmpty(refreshValue) && !"--".equals(refreshValue)) {
            String oldRefreshValue = textView.getText().toString();

            textView.setText(refreshValue);

            if (!oldRefreshValue.equals(refreshValue)) {
                Animation mAnimation = AnimationUtils.loadAnimation(textView.getContext(), R.anim.alpha_scoket);
                textView.startAnimation(mAnimation);
            }
        }
    }

    public synchronized static void viewSetAnimation2(TextView textView, String refreshValue) {

        if (null != textView && !(textView.getText().toString()).equals("--") && !TextUtils.isEmpty(refreshValue) && !"--".equals(refreshValue)) {
            String oldRefreshValue = textView.getText().toString();

            textView.setText(refreshValue);

            if (!oldRefreshValue.equals(refreshValue)) {
                Animation mAnimation = AnimationUtils.loadAnimation(textView.getContext(), R.anim.alpha_scoket);
                mAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                textView.startAnimation(mAnimation);
            }
        }
    }

    public synchronized static void viewSetAnimation3(TextView textView, String refreshValue) {

        if (null != textView && !(textView.getText().toString()).equals("--") && !TextUtils.isEmpty(refreshValue) && !"--".equals(refreshValue)) {
            String oldRefreshValue = textView.getText().toString();

            textView.setText(refreshValue);

            if (!oldRefreshValue.equals(refreshValue)) {
                Animation mAnimation = AnimationUtils.loadAnimation(textView.getContext(), R.anim.alpha_scoket);
                mAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                textView.startAnimation(mAnimation);
            }
        }
    }

    public synchronized static void viewSetAnimation4(TextView textView, String refreshValue) {

        if (null != textView && !(textView.getText().toString()).equals("--") && !TextUtils.isEmpty(refreshValue) && !"--".equals(refreshValue)) {
            String oldRefreshValue = textView.getText().toString();

            textView.setText(refreshValue);

            if (!oldRefreshValue.equals(refreshValue)) {
                Animation mAnimation = AnimationUtils.loadAnimation(textView.getContext(), R.anim.alpha_scoket);
                mAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                textView.startAnimation(mAnimation);
            }
        }

    }

    public synchronized static void viewSetAnimation5(TextView textView, String refreshValue) {

        if (null != textView && !(textView.getText().toString()).equals("--") && !TextUtils.isEmpty(refreshValue) && !"--".equals(refreshValue)) {
            String oldRefreshValue = textView.getText().toString();

            textView.setText(refreshValue);

            if (!oldRefreshValue.equals(refreshValue)) {
                Animation mAnimation = AnimationUtils.loadAnimation(textView.getContext(), R.anim.alpha_scoket);
                mAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
                textView.startAnimation(mAnimation);
            }
        }
    }
}
