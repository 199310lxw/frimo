package com.example.frimo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.MainActivity;
import com.example.frimo.R;
import com.example.frimo.beans.User;
import com.example.frimo.utils.PhoneUtils;
import com.example.frimo.utils.SystemUtil;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisterActivity extends BaseActivity {
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
                            confirmSMSCode(phonenum,verrity);
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
       txt_verity.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(new PhoneUtils(getApplicationContext()).judgePhone(edit_phoneNumber.getText().toString().trim())){
                   String phone_num=edit_phoneNumber.getText().toString().trim();
                   getSMSCode( phone_num);
               }
           }
       });

    }

    /**
     * 获取短信验证码
     */
    private void getSMSCode(String str_phone){
        BmobSMS.requestSMSCode(str_phone, "frimo", new QueryListener<Integer>() {
            @Override
            public void done(Integer smsId, BmobException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(),"发送验证码成功,请稍后!",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"发送验证码失败!",Toast.LENGTH_SHORT).show();
                    Log.e("regster","发送验证码失败:"+e.getErrorCode() + "-" + e.getMessage() + "\n");
                }
            }
        });
    }

    /**
     * 验证验证码是否正确
     */
    private void confirmSMSCode(String phone_num,String smsCode){
        BmobSMS.verifySmsCode(phone_num, smsCode, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    User user=new User();
                    user.setUsername(phonenum);//必填项，不然报错
                    user.setMobilePhoneNumber(phonenum);
                    user.setPassword(PassWord);//必填项，不然报错
                    user.setMobilePhoneNumberVerified(true);
                    user.signUp(new SaveListener<User>() {
                        @Override
                        public void done(User user, BmobException e) {
                            if (e == null) {
                                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                                Intent in=new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(in);
                                finish();
                            } else {
                                Log.e("register",e.getErrorCode()+":"+e.getMessage());
                                Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Log.e("register",""+e.getErrorCode()+"-----"+e.getMessage());
                    if(e.getErrorCode()==10007){
                        Toast.makeText(getApplicationContext(), "验证码错误！"+ + e.getErrorCode(), Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    /**
     * 注册提交数据
     * @param user
     */
    private void saveData(BmobObject user) {
        user.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "添加数据成功，返回objectId为：" + objectId, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "创建数据失败：" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("register", e.getMessage());
                }
            }
        });
    }
}
