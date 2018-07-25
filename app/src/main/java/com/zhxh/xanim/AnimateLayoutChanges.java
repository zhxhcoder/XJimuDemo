package com.zhxh.xanim;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class AnimateLayoutChanges extends Activity {

    private LinearLayout ll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animate_layout_changes);
        ll = (LinearLayout) findViewById(R.id.ll);
    }

    public void add(View view) {
        ll.addView(new Button(this));
    }
}
