package com.example.frimo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.frimo.R;
import com.example.frimo.beans.User;
import com.example.frimo.utils.GetPathFromUri;
import com.example.frimo.utils.StatusBarUtil;
import com.example.frimo.utils.uriToPathUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UploadFileListener;

public class DetailInfoActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = "DetailInfoActivity";
    private static final  int REQUEST_PICK_IMAGE=0;

    private ImageView img_back;
    private RelativeLayout re_headpic;

    private TextView txt_nickname;//昵称
    private TextView txt_gender;//性别
    private TextView txt_age;//性别
    private TextView txt_username;//用户名/手机号
    private TextView txt_city;//城市
    private TextView txt_hometown;//家乡

    private BmobFile bmobFile;
    private Uri uri;
    private String picPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        //根据状态栏颜色来决定状态栏文字用黑色还是白色
        StatusBarUtil.setStatusBarMode(this, true, R.color.gray);
        initView();
    }

    private void initView() {
       findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

       re_headpic=findViewById(R.id.re_headpic);
       re_headpic.setOnClickListener(this);

       txt_nickname=findViewById(R.id.txt_nickname);
       txt_gender=findViewById(R.id.txt_gender);
        txt_age=findViewById(R.id.txt_age);
       txt_username=findViewById(R.id.txt_username);
       txt_city=findViewById(R.id.txt_city);
       txt_hometown=findViewById(R.id.txt_hometown);
       getUserData();
    }

    /**
     * 获取用户信息
     */
    private void getUserData(){
        SharedPreferences sp=getSharedPreferences("userdata", Context.MODE_PRIVATE);
        //第二个参数为缺省值，如果不存在该key，返回缺省值
        String username=sp.getString("username","");
        String nickname=sp.getString("nickname","");
        int gender=sp.getInt("gender",-1);
        int age=sp.getInt("age",0);
        String city=sp.getString("city","");
        String hometown=sp.getString("hometown","");

        txt_username.setText(username);
        txt_nickname.setText(nickname);
        if(gender==1){
            txt_gender.setText("男");
        }else if(gender==0){
            txt_gender.setText("女");
        }else{
            txt_gender.setText("未知");
        }
        txt_age.setText(age+"");
        txt_city.setText(city);
        txt_hometown.setText(hometown);
    }
    private void uploadFile(String picPath) {

        Log.e(TAG,picPath+"------------");
        if(!picPath.equals("")){
            bmobFile = new BmobFile(new File(picPath));
            bmobFile.uploadblock(new UploadFileListener() {

                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //bmobFile.getFileUrl()--返回的上传文件的完整地址
                        Log.e(TAG, bmobFile.getFileUrl() + "");
                    } else {
                        Log.e(TAG, "文件上传失败" +e.getErrorCode()+ e.getMessage());
                    }

                }

                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                    Log.e(TAG, "文件已上传：" + value);
                }
            });
        }else{
            Toast.makeText(DetailInfoActivity.this,"文件路径不存在!",Toast.LENGTH_SHORT).show();
        }


    }
    /**
     * 获取系统相册路径
     */
    private void getImage() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            startActivityForResult(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), REQUEST_PICK_IMAGE);
        } else {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            startActivityForResult(intent, REQUEST_PICK_IMAGE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.re_headpic:
                getImage();
                break;
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case  REQUEST_PICK_IMAGE:
                    if (data != null) {
                        picPath = GetPathFromUri.getPath(DetailInfoActivity.this, data.getData());
                        Log.e(TAG,data.getData()+"------------");
                        Log.e(TAG,picPath+"------------");
                        uploadFile(picPath);
                    } else {
                        Toast.makeText(DetailInfoActivity.this, "图片损坏，请重新选择", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }

}
