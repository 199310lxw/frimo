package com.example.frimo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.frimo.beans.Data;

public class SharedPreferenceUtil {
    private Context mContext;
    public SharedPreferenceUtil(Context mContext){
        this.mContext=mContext;
    }

    /**
     * 保存用户user的数据到userdata表
     * @param login_tag 登陆状态
     */
    public  void saveUserDataInLocal(Data user, Boolean login_tag){
        SharedPreferences sp=mContext.getSharedPreferences("userdata",mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();   //获取编辑器
        editor.putString("username",user.getUserName()); //存入username
        editor.putString("nickname",user.getNickName());//存入昵称
        editor.putString("city",user.getCity());//存入城市
        editor.putString("hometown",user.getHomeTown());//存入城市
        editor.putInt("gender",user.getGender());//存入性别
        editor.putInt("age",user.getAge());//存入年龄
        editor.putBoolean("logintag",login_tag);//存入登陆状态
        editor.commit();                //提交修改，否则不生效
    }
}
