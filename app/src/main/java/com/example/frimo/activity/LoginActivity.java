package com.example.frimo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.MainActivity;
import com.example.frimo.R;
import com.example.frimo.beans.User;
import com.example.frimo.utils.Constants;
import com.example.frimo.utils.SharedPreferenceUtil;
import com.example.frimo.utils.SystemUtil;

import java.io.Serializable;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;

public class LoginActivity extends BaseActivity {
    private static final String TAG="LoginActivity";
    private Boolean login_state=false;
    private TextView txt_register, txt_forget;
    private EditText edit_username, edit_password;
    private Button btn_login;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        initView();
    }

    private void initView() {
        txt_forget = findViewById(R.id.txt_forget);
        txt_register = findViewById(R.id.txt_register);
        edit_username = findViewById(R.id.edit_phoneNumber);
        edit_password = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
        img_back = findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(in);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent in=new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(in);
                if(!edit_username.getText().toString().trim().equals("")){
                    if(!edit_password.getText().toString().trim().equals("")){
                        checkData(edit_username.getText().toString().trim(),edit_password.getText().toString().trim());
                    }else{
                        Toast.makeText(getApplicationContext(),"密码不能为空",Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"账号不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * 检查输入账号密码是否正确
     */
    private void checkData(final String str_username, final String str_password) {

       User.loginByAccount(str_username, str_password, new LogInListener<User>() {
            @Override
            public void done(User user, BmobException e) {
                if(user!=null){
                    if (e == null) {
                        //发送广播通知登陆成功
                        Intent in_receiver=new Intent(Constants.ISLOGIN_RECEIVER_ACTION);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("user_info",(Serializable)user);
                        in_receiver.putExtra("user_bundle",bundle);
                        sendBroadcast(in_receiver);

                        new SharedPreferenceUtil(getApplicationContext()).saveUserDataInLocal(user,true);
                        Toast.makeText(getApplicationContext(), "登陆成功", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(in);
                        finish();
                    } else {
                        if(e.getErrorCode()==9016){
                            Toast.makeText(getApplicationContext(), "网络错误"+e.getErrorCode(), Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), "账号或密码错误"+e.getErrorCode(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
