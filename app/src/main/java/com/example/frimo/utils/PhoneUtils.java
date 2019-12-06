package com.example.frimo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

public class PhoneUtils {
    private static Context mContext;
    public PhoneUtils(Context mContext){
        this.mContext=mContext;
    }
    /**
     * 验证手机号码是否正确
     * @param str
     * @return
     */
    public boolean judgePhone(String str) {
        if (TextUtils.isEmpty(str)) {
            Toast.makeText(mContext, "请输入您的电话号码", Toast.LENGTH_LONG).show();
            return false;
        } else if (str.length()!= 11) {
            Toast.makeText(mContext, "您的电话号码位数不正确", Toast.LENGTH_LONG).show();
            return false;
        } else {
            String num = "[1][358]\\d{9}";
            if (str.matches(num))
                return true;
            else {
                Toast.makeText(mContext, "请输入正确的手机号码", Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }
}
