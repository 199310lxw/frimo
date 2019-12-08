package com.example.frimo.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.frimo.MainActivity;
import com.example.frimo.R;
import com.example.frimo.utils.SystemUtil;

public class GuideActivity extends AppCompatActivity {
    //停留的时长
    private static final long DELAY_TIME = 1000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进入主程序页面
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
            }
        }, DELAY_TIME);
    }
}
