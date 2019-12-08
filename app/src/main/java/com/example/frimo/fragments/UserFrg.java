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
import com.example.frimo.activity.DetailInfoActivity;
import com.example.frimo.activity.LoginActivity;
import com.example.frimo.activity.SettingActivity;
import com.example.frimo.beans.User;
import com.example.frimo.receiver.LoginReceiver;
import com.example.frimo.utils.Constants;

import java.util.List;

public class UserFrg  extends Fragment implements View.OnClickListener,LoginReceiver.IisLogin {
    private static final String TAG="UserFrg";

    private ImageView img_set;
    private ImageView head_pic;
    private TextView txt_username;

    private RelativeLayout relative_collect_people,relative_collect_trend,relative_detail_info,relative_my_trend;

    private Boolean isLogin;
    private LoginReceiver receiver;
    private List<User> list_user;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frg_user, null);
        Bundle bundle =this.getArguments();//得到从Activity传来的数据
        if(bundle!=null){
            isLogin=bundle.getBoolean("ISLOGIN");
            Log.e(TAG,isLogin+"hhhhhhh");
        }

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
        relative_detail_info=view.findViewById(R.id.relative_detail_info);
        relative_my_trend=view.findViewById(R.id.relative_my_trend);

        initReceiver();
        getLocalLoginData();
        img_set.setOnClickListener(this);
        relative_collect_people.setOnClickListener(this);
        relative_collect_trend.setOnClickListener(this);
        relative_detail_info.setOnClickListener(this);
        relative_my_trend.setOnClickListener(this);

        head_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isLogin){
                    Toast.makeText(getActivity(), "用户已经登陆了", Toast.LENGTH_SHORT).show();
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
        if(isLogin) {
            switch (view.getId()) {
                case R.id.relative_collect_people:
                    Toast.makeText(getActivity(),"哈哈哈，我已经登陆了",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_collect_trend:
                    Toast.makeText(getActivity(),"哈哈哈，我已经登陆了",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.relative_detail_info:
                    Intent in=new Intent(getActivity(), DetailInfoActivity.class);
                    startActivity(in);
                    break;
                case R.id.relative_my_trend:
                    Toast.makeText(getActivity(),"哈哈哈，我已经登陆了",Toast.LENGTH_SHORT).show();
                    break;
            }
        }else{
            Toast.makeText(getActivity(),"请先登陆",Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 获取本地 SharedPreferences保存的用户数据
     */
    private  void getLocalLoginData(){
        SharedPreferences sp=getActivity().getSharedPreferences("userdata", Context.MODE_PRIVATE);
        //第二个参数为缺省值，如果不存在该key，返回缺省值
        String username=sp.getString("username","游客123456");
        String password=sp.getString("password","");
        txt_username.setText(username);
        isLogin=sp.getBoolean("logintag",false);
    }

    @Override
    public void setData(int tag) {
        if(tag==0){
            isLogin=false;
            txt_username.setText("游客1234567");
            Log.e(TAG,isLogin+"哈哈哈哈");
        }else{
            isLogin=true;
            Log.e(TAG,isLogin+"嘿嘿嘿嘿");
        }
    }

    @Override
    public void setBundle(Bundle bundle) {
        list_user=(List<User>) bundle.getSerializable("user_info");
        txt_username.setText(list_user.get(0).getMobilePhoneNumber());
        Log.e(TAG,list_user.get(0).getMobilePhoneNumber()+"-----------?");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().getApplicationContext().unregisterReceiver(receiver);
    }


}
