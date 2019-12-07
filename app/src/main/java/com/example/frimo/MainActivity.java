package com.example.frimo;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.frimo.activity.BaseActivity;
import com.example.frimo.fragments.HomeFrg;
import com.example.frimo.fragments.TrendsFrg;
import com.example.frimo.fragments.UserFrg;
import com.example.frimo.receiver.LoginReceiver;
import com.example.frimo.utils.Constants;
import com.example.frimo.utils.FragmentUtil;
import com.example.frimo.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private static final String TAG = "MainActivity";
    private Boolean isLogin = false;//是否登录,默认没登陆
    private RadioGroup mRadioGroup;
    private FragmentManager fm;
    private List<Fragment> mFragments;
    private HomeFrg mHomeFrg;
    private TrendsFrg mTrendsFrg;
    private UserFrg mUserFrg;

    private static boolean mBackKeyPressed = false;//记录是否有首次按键

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        SystemUtil.initSystemBarTint(this, getResources().getColor(R.color.transparent_bg));
        initView();
    }

    /**
     * 初始化主页三个fragment，并添加进fragments里面
     */
    private void initFragments() {
        fm = getSupportFragmentManager();
        mFragments = new ArrayList<>();
        mHomeFrg = new HomeFrg();
        mTrendsFrg = new TrendsFrg();
        mUserFrg = new UserFrg();
        mFragments.add(mHomeFrg);
        mFragments.add(mTrendsFrg);
        mFragments.add(mUserFrg);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        mRadioGroup = findViewById(R.id.tab_bar);
        initFragments();
//        setTabSelection(0);
        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(0), isLogin);
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.tab_home:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(0), isLogin);
                        break;
                    case R.id.tab_trends:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(1), isLogin);
                        break;
                    case R.id.tab_user:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(2), isLogin);
                        break;
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (!mBackKeyPressed) {
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mBackKeyPressed = true;
            new Timer().schedule(new TimerTask() {//延时两秒，如果超出则擦错第一次按键记录
                @Override
                public void run() {
                    mBackKeyPressed = false;
                }

            }, 2000);
        } else {//退出程序
            this.finish();
            System.exit(0);
        }
    }
}
