package com.example.frimo.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceUtil {
    private Context mContext;
    public SharedPreferenceUtil(Context mContext){
        this.mContext=mContext;
    }

    /**
     * 保存用户user的数据到userdata表
     * @param username 用户名
     * @param password 密码
     * @param login_tag 登陆状态
     */
    public void saveUserDataInLocal(String username,String password,Boolean login_tag){
        SharedPreferences sp=mContext.getSharedPreferences("userdata",mContext.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();   //获取编辑器
        editor.putString("username",username); //存入username
        editor.putString("password",password); //存入password
        editor.putBoolean("logintag",login_tag);//存入登陆状态
        editor.commit();                //提交修改，否则不生效
    }
}
