package com.example.frimo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.MainActivity;
import com.example.frimo.R;
import com.example.frimo.beans.Data;
import com.example.frimo.beans.User;
import com.example.frimo.constants.constants;
import com.example.frimo.utils.Constants;
import com.example.frimo.utils.PhoneUtils;
import com.example.frimo.utils.SharedPreferenceUtil;
import com.example.frimo.utils.SystemUtil;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.yanzhenjie.nohttp.rest.StringRequest;


public class RegisterActivity extends BaseActivity {
    private static final String TAG="RegisterActivity";
    private EditText edit_phoneNumber;
    private EditText edit_passWord;
    private EditText edit_verity;

    private Button btn_register;
    private String phonenum, PassWord, verrity;
    private TextView txt_verity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        initView();
    }

    private void initView() {
        edit_phoneNumber = findViewById(R.id.edit_phoneNumber);
        edit_passWord = findViewById(R.id.edit_password);
        edit_verity = findViewById(R.id.edit_verity);
        txt_verity=findViewById(R.id.txt_verity);
        btn_register = findViewById(R.id.btn_register);


        findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phonenum = edit_phoneNumber.getText().toString();
                verrity = edit_verity.getText().toString();
                PassWord = edit_passWord.getText().toString();
                if (!phonenum.equals("")) {
                    if(!verrity.equals("")) {
                        if (!PassWord.equals("")) {

                        } else {
                            Toast.makeText(getApplicationContext(), "请输入密码!", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(getApplicationContext(), "请输入验证码!", Toast.LENGTH_SHORT).show();
                    }
                  }
                else {
                    Toast.makeText(getApplicationContext(), "请输入手机号!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        final MyCountDownTimer myCountDownTimer = new MyCountDownTimer(60000,1000);
       txt_verity.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(new PhoneUtils(getApplicationContext()).judgePhone(edit_phoneNumber.getText().toString().trim())){
                   myCountDownTimer.start();
                   String phone_num=edit_phoneNumber.getText().toString().trim();

               }
           }
       });

    }

private void register(String UserName,String PassWord){
    String url= constants.COMMON_IP+"register.php";
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
                        new SharedPreferenceUtil(RegisterActivity.this).saveUserDataInLocal(data,true);
                        Intent in_receiver=new Intent(Constants.ISLOGIN_RECEIVER_ACTION);
                        sendBroadcast( in_receiver);
                        Intent in=new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(in);
                    }
                }else{
                    Toast.makeText(RegisterActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
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



    /**
     * 获取验证码倒计时
     */

    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        //计时过程
        @Override
        public void onTick(long l) {
            //防止计时过程中重复点击
            txt_verity.setClickable(false);
            txt_verity.setText(l/1000+"s");
            txt_verity.setBackground(getResources().getDrawable(R.drawable.shape_button_round_corner_pressed));
        }

        //计时完毕的方法
        @Override
        public void onFinish() {
            //重新给Button设置文字
            txt_verity.setText("重新获取");
            //设置可点击
            txt_verity.setClickable(true);
            txt_verity.setBackground(getResources().getDrawable(R.drawable.shape_button_round_corner_unpressed));
        }
    }

}
