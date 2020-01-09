package com.example.frimo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.frimo.R;
import com.example.frimo.utils.SystemUtil;

public class AboutActivity extends BaseActivity {
    private TextView txt_vertion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //根据状态栏颜色来决定状态栏文字用黑色还是白色
        SystemUtil.initSystemBarTint(this,getResources().getColor(R.color.transparent_bg));
        SystemUtil.setAndroidNativeLightStatusBar(this,true);
        initView();
    }

    private void initView() {
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_vertion=findViewById(R.id.txt_vertion);
        txt_vertion.setText("应用当前版本：\t"+ SystemUtil.getVersionName(this));
    }
}
