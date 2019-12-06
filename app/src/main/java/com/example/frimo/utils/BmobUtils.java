package com.example.frimo.utils;

import android.content.Context;
import android.widget.Toast;

import com.example.frimo.beans.User;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class BmobUtils {
    private Context mContext;
    public BmobUtils(Context mContext){
        this.mContext=mContext;
    }
    //插入数据
    public  void savaData(BmobObject object){
        object.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){
                    Toast.makeText(mContext,"添加数据成功，返回objectId为："+objectId,Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext,"创建数据失败："+e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    //根据条件查找数据
    //查找Person表里面id为6b6c11c537的数据
    public void  searchData(){
        BmobQuery<BmobObject> bmobQuery = new BmobQuery<BmobObject>();
        bmobQuery.getObject("6b6c11c537", new QueryListener<BmobObject>() {
            @Override
            public void done(BmobObject object,BmobException e) {
                if(e==null){
                }else{
                }
            }
        });
    }

}
