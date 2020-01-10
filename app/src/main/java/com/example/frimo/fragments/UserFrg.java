package com.example.frimo.fragments;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.frimo.R;
import com.example.frimo.activity.CollectPersonActivity;
import com.example.frimo.activity.DetailInfoActivity;
import com.example.frimo.activity.LoginActivity;
import com.example.frimo.activity.SettingActivity;
import com.example.frimo.beans.Data;
import com.example.frimo.receiver.LoginReceiver;
import com.example.frimo.utils.Constants;

public class UserFrg  extends Fragment implements View.OnClickListener,LoginReceiver.IisLogin {
    private static final String TAG="UserFrg";
    private static final  int REQUEST_PICK_IMAGE=0;

    private ImageView img_set;
    private ImageView head_pic;
    private TextView txt_username;

    private RelativeLayout relative_collect_people,relative_collect_trend,relative_my_trend;

    private Boolean isLogin;
    private LoginReceiver receiver;
    private Data user;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frg_user, null);

        initView(view);
        return view;
    }
    private void initReceiver(){
        receiver=new LoginReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.UNLOGIN_RECEIVER_ACTION);
        intentFilter.addAction(Constants.ISLOGIN_RECEIVER_ACTION);
        getActivity().getApplicationContext().registerReceiver( receiver, intentFilter);
        receiver.setReceiverListener(this);
    }
    private void initView(View view){
        img_set=view.findViewById(R.id.img_set);
        head_pic=view.findViewById(R.id.head_pic);
        txt_username=view.findViewById(R.id.txt_username);

        relative_collect_people=view.findViewById(R.id.relative_collect_people);
        relative_collect_trend=view.findViewById(R.id.relative_collect_trend);
        relative_my_trend=view.findViewById(R.id.relative_my_trend);

        initReceiver();
        getLocalLoginData();
        img_set.setOnClickListener(this);
        relative_collect_people.setOnClickListener(this);
        relative_collect_trend.setOnClickListener(this);
        relative_my_trend.setOnClickListener(this);

        head_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                     Intent in=new Intent(getActivity(),DetailInfoActivity.class);
                     startActivity(in);
                }else{
                    Intent in_login=new Intent(getActivity(), LoginActivity.class);
                    startActivity(in_login);
                }
            }
        });
        img_set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  startActivity(new Intent(getActivity(), SettingActivity.class));
            }
        });
    }
    @Override
    public void onClick(View view) {
        if(isLogin){
            switch (view.getId()) {
                case R.id.relative_collect_people:
                    Intent in_person=new Intent(getActivity(), CollectPersonActivity.class);
                    startActivity(in_person);
                    break;
                case R.id.relative_collect_trend:
                    Toast.makeText(getActivity(),"哈哈哈，我已经登陆了",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_my_trend:
                    Toast.makeText(getActivity(),"哈哈哈，我已经登陆了",Toast.LENGTH_SHORT).show();
                    break;
            }
        }else{
            Toast.makeText(getActivity(),"你还没登录",Toast.LENGTH_SHORT).show();
        }

    }



    /**
     * 获取本地 SharedPreferences保存的用户数据
     */
    private  void getLocalLoginData(){
        SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        //第二个参数为缺省值，如果不存在该key，返回缺省值
        String username=sp.getString("username","游客");
        String nickname=sp.getString("nickname","");
        String password=sp.getString("password","");
        if(!username.equals("")){
            txt_username.setText(nickname);
        }else{
            txt_username.setText(username);
        }

        isLogin=sp.getBoolean("logintag",false);
    }

    @Override
    public void setData(int tag) {
        if(tag==0){
            isLogin=false;
            txt_username.setText("游客");
        }else{
            isLogin=true;
            getLocalLoginData();
        }
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().unregisterReceiver(receiver);
    }
}
