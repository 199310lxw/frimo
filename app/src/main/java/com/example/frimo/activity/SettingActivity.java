package com.example.frimo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.frimo.MainActivity;
import com.example.frimo.R;
import com.example.frimo.utils.Constants;
import com.example.frimo.utils.SystemUtil;

public class SettingActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_setting);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this,getResources().getColor(R.color.transparent_bg));
        initView();
    }
    private void initView(){
        findViewById(R.id.btn_unLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in_receiver=new Intent();
                in_receiver.setAction(Constants.UNLOGIN_RECEIVER_ACTION);
                sendBroadcast(in_receiver);

                Intent in_ac=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in_ac);
                finish();
            }
        });
    }
}
