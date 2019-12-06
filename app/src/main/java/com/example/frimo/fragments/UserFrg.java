package com.example.frimo.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.frimo.R;
import com.example.frimo.activity.LoginActivity;
import com.example.frimo.activity.RegisterActivity;

import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class UserFrg  extends Fragment {
    private Button btn_sendSMS;
    private Button btn_confirm;
    private EditText edit;
    private String smsCode;
    private ImageView img_set;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frg_user, null);
        initView(view);
        return view;
    }
    private void initView(View view){
//        btn_confirm=view.findViewById(R.id.btn_confirm);
        img_set=view.findViewById(R.id.img_set);
        img_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getActivity(), LoginActivity.class);
                startActivity(in);
            }
        });
    }
    public void confirm(){
        BmobSMS.verifySmsCode("13682377116", smsCode, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
//                            mTvInfo.append("验证码验证成功，您可以在此时进行绑定操作！\n");
                    Toast.makeText(getActivity(),"验证码验证成功，您可以在此时进行绑定操作！\n",Toast.LENGTH_SHORT).show();
//                            User user = BmobUser.getCurrentUser(User.class);
//                            user.setMobilePhoneNumber(phone);
//                            user.setMobilePhoneNumberVerified(true);
//                            user.update(new UpdateListener() {
//                                @Override
//                                public void done(BmobException e) {
//                                    if (e == null) {
//                                        mTvInfo.append("绑定手机号码成功");
//                                    } else {
//                                        mTvInfo.append("绑定手机号码失败：" + e.getErrorCode() + "-" + e.getMessage());
//                                    }
//                                }
//                            });
                } else {
//                            mTvInfo.append("验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage() + "\n");
                    Toast.makeText(getActivity(),"验证码验证失败：" + e.getErrorCode() + "-" + e.getMessage() ,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
