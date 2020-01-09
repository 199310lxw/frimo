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
import com.example.frimo.beans.Data;
import com.example.frimo.beans.User;
import com.example.frimo.constants.constants;
import com.example.frimo.utils.Constants;
import com.example.frimo.utils.SharedPreferenceUtil;
import com.example.frimo.utils.SystemUtil;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;

import java.io.Serializable;


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
                if(!edit_username.getText().equals("")&&!edit_password.getText().equals("")){
                    LoginData(edit_username.getText().toString().trim(),edit_password.getText().toString().trim());
                }
            }
        });
    }
    private void LoginData(String UserName,String PassWord){
        String url= constants.COMMON_IP+"login.php";
        RequestQueue queue = NoHttp.newRequestQueue();
        final Request<String> request = new StringRequest(url);
        request.add("UserName",UserName);
        request.add("PassWord",PassWord);
        queue.add(0, request, new OnResponseListener<String>(){
            @Override
            public void onSucceed(int what, Response<String> response) {
                if(response.responseCode() == 200) {// 请求成功。
                    String result = response.get();
                    Log.e(TAG,"请求结果"+result);
                    if(!result.equals("")){
                        Gson gson=new Gson();
                        User user=gson.fromJson(result, User.class);
                        if(user.getCode().equals("200")){
                            Data data=user.getData();
                            new SharedPreferenceUtil(LoginActivity.this).saveUserDataInLocal(data,true);
                            Intent  in_receiver=new Intent(Constants.ISLOGIN_RECEIVER_ACTION);
                            sendBroadcast( in_receiver);
                            Intent in=new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(in);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailed(int what, Response<String> response) {
                Log.e(TAG,"请求错误"+response.getException());
            }

            @Override
            public void onStart(int what) {
                // 这里可以show()一个wait dialog。
            }

            @Override
            public void onFinish(int what) {
                // 这里可以dismiss()上面show()的wait dialog。
            }
        });
    }

}
