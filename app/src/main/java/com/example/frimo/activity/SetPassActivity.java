package com.example.frimo.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.R;
import com.example.frimo.beans.User;
import com.example.frimo.utils.SystemUtil;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

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
                String str_old=edit_old_password.getText().toString().trim();
                String str_new=edit_new_password.getText().toString().trim();
                String str_new_confirm=edit_new_password_confirm.getText().toString().trim();
                if(str_old.equals(getUserPass())){
                    if(!str_new.equals("")){
                        if(!str_old.equals(str_new)){
                            if(str_new.equals(str_new_confirm)){
                                updateData(str_new);
                                finish();
                            }else{
                                Toast.makeText(SetPassActivity.this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(SetPassActivity.this, "旧密码和新密码一致，无法修改", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(SetPassActivity.this, "新密码不能为空", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(SetPassActivity.this, "原来的密码不正确", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void updateData(String str_password){
        final User user = BmobUser.getCurrentUser(User.class);
        user.setPassword(str_password);
        user.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(SetPassActivity.this, "更新用户信息成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(SetPassActivity.this, "原来的密码不能为空"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("error", e.getMessage());
                }
            }
        });
    }
    private String getUserPass(){
        String password="";
        if(BmobUser.isLogin()){
            User user = BmobUser.getCurrentUser(User.class);
            password = (String) BmobUser.getObjectByKey("password");
        }
        return password;
    }
}
