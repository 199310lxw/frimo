package com.example.frimo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.frimo.R;
import com.example.frimo.utils.uriToPathUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UploadFileListener;

public class DetailInfoActivity extends BaseActivity {
    private final String TAG = "DetailInfoActivity";
    private BmobFile bmobFile;
    private Uri uri;
    private String picPath = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        initView();
    }

    private void initView() {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadFile();
            }
        });
    }
    private void uploadFile() {
        String path= "file://asset/test.jpg";
        Log.e(TAG,path+"-----------------");
        bmobFile = new BmobFile(new File(path));
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
    }

}
