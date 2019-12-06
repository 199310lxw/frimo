package com.example.frimo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.frimo.R;
import com.example.frimo.utils.SystemUtil;

public class LoginActivity extends BaseActivity {
    private TextView txt_register,txt_forget;
    private ImageView img_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this,getResources().getColor(R.color.transparent_bg));
        initView();
    }
    private void initView(){
        txt_forget=findViewById(R.id.txt_forget);
        txt_register=findViewById(R.id.txt_register);
        img_back=findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(in);
            }
        });
    }
}
