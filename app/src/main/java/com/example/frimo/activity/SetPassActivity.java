package com.example.frimo.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.R;
import com.example.frimo.utils.SystemUtil;


public class SetPassActivity extends BaseActivity {
    private final static String TAG="SetPassActivity";
    private TextView txt_title,txt_finish,txt_username;
    private EditText edit_old_password,edit_new_password,edit_new_password_confirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pass);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        initView();
    }
    private  void initView(){
        txt_title=findViewById(R.id.txt_title);
        txt_finish=findViewById(R.id.txt_finish);
        edit_old_password=findViewById(R.id.edit_old_password);
        edit_new_password=findViewById(R.id.edit_new_password);
        edit_new_password_confirm=findViewById(R.id.edit_new_password_confirm);
        SharedPreferences sp=getSharedPreferences("userdata", Context.MODE_PRIVATE);
        //第二个参数为缺省值，如果不存在该key，返回缺省值
        String username=sp.getString("username","");
        txt_username=findViewById(R.id.txt_username);
        txt_username.setText(username);
        txt_title.setText("设置密码");
        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"修改完成!",Toast.LENGTH_SHORT).show();
            }
        });
        txt_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }});
    }
}
