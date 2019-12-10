package com.example.frimo.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.frimo.MainActivity;
import com.example.frimo.R;
import com.example.frimo.utils.Constants;
import com.example.frimo.utils.SystemUtil;

public class SettingActivity extends BaseActivity implements View.OnClickListener {
    private Boolean LOGIN_TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_setting);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this,getResources().getColor(R.color.transparent_bg));
        SharedPreferences sp=getSharedPreferences("userdata", Context.MODE_PRIVATE);
        LOGIN_TAG=sp.getBoolean("logintag",false);
        initView();
    }
    private void initView(){
        findViewById(R.id.re_account).setOnClickListener(this);
        findViewById(R.id.re_add_info).setOnClickListener(this);

        findViewById(R.id.re_about).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),AboutActivity.class);
                startActivity(in);
            }
        });
        findViewById(R.id.re_help).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),TickActivity.class);
                startActivity(in);
            }
        });
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        findViewById(R.id.btn_unLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in_receiver=new Intent();
                in_receiver.setAction(Constants.UNLOGIN_RECEIVER_ACTION);
                sendBroadcast(in_receiver);

                deleteLocalUserData();

                Intent in_ac=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(in_ac);
                finish();
            }
        });
    }

    /**
     * 退出登陆，删除SharedPreferences中保存的用户数据
     */
    private void  deleteLocalUserData(){
        SharedPreferences sp=getSharedPreferences("userdata",MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();   //获取编辑器
        editor.remove("username");   //删除一条数据
        editor.remove("password");   //删除一条数据
        editor.remove("logintag");   //删除一条数据
        editor.clear();         //删除所有数据
        editor.commit();     //提交修改，否则不生效
    }

    @Override
    public void onClick(View v) {
        if(LOGIN_TAG){
            switch (v.getId()){
                case R.id.re_account:
                    Intent in =new Intent(getApplicationContext(),SetPassActivity.class);
                    startActivity(in);
                    break;
                case R.id.re_add_info:
                    Intent in_add=new Intent(getApplicationContext(),AddInfoActivity.class);
                    startActivity(in_add);
                    break;
            }
        }else{
            Toast.makeText(getApplicationContext(),"请先登录",Toast.LENGTH_SHORT).show();
        }

    }
}
