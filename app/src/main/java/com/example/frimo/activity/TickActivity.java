package com.example.frimo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.frimo.R;
import com.example.frimo.utils.SystemUtil;

public class TickActivity extends BaseActivity {
    private TextView txt_title,txt_finish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tick);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        initView();
    }
    private void initView(){
        txt_title=findViewById(R.id.txt_title);
        txt_finish=findViewById(R.id.txt_finish);
        txt_title.setText("反馈建议");
        txt_finish.setText("提交");
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
