package com.zhxh.xanim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.zhxh.xanim.R.layout.activity_main);
        listView = (ListView) findViewById(com.zhxh.xanim.R.id.listView1);
        listView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, new String[]{
                "AnimateLayoutChanges", "AnimateFreeFall",
                "AnimateMoveInSecond", "AnimateMultiple",
                "AnimateLayoutTransition", "AnimateLayoutAnimation"}));
        listView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                switch (arg2) {
                    case 0:
                        startActivity(new Intent(MainActivity.this,
                                AnimateLayoutChanges.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,
                                AnimateFreeFall.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,
                                AnimateMoveInSecond.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this,
                                AnimateMultiple.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this,
                                AnimateLayoutTransition.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this,
                                AnimateLayoutAnimation.class));
                        break;
                    default:
                        break;
                }

            }
        });
    }
}
