package com.example.frimo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.frimo.R;
import com.example.frimo.utils.SystemUtil;

public class AddInfoActivity extends BaseActivity {
    private TextView txt_title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        initView();
    }
    private void initView(){
        txt_title=findViewById(R.id.txt_title);
        txt_title.setText("完善信息");
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
