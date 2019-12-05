package com.example.frimo;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.frimo.activity.BaseActivity;
import com.example.frimo.fragments.HomeFrg;
import com.example.frimo.fragments.TrendsFrg;
import com.example.frimo.fragments.UserFrg;
import com.example.frimo.utils.FragmentUtil;
import com.example.frimo.utils.SystemUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {
    private RadioGroup mRadioGroup;
    private FragmentManager fm;
    private List<Fragment> mFragments;
    private HomeFrg mHomeFrg;
    private TrendsFrg mTrendsFrg;
    private UserFrg  mUserFrg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SystemUtil.setAndroidNativeLightStatusBar(this, false);
        initSystemBarTint(getResources().getColor(R.color.transparent_bg));
        initView();
    }
    /**
     * 初始化主页三个fragment，并添加进fragments里面
     */
    private void initFragments(){
        fm=getSupportFragmentManager();
        mFragments=new ArrayList<>();
        mHomeFrg=new HomeFrg();
        mTrendsFrg=new TrendsFrg();
        mUserFrg=new UserFrg();
        mFragments.add(mHomeFrg);
        mFragments.add(mTrendsFrg);
        mFragments.add(mUserFrg);
    }
    /**
     * 初始化界面
     */
    private void initView(){
        mRadioGroup=findViewById(R.id.tab_bar);
       initFragments();
//        setTabSelection(0);
       new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(0));
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId){
                    case R.id.tab_home:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(0));
//                        setTabSelection(0);
                        break;
                    case R.id.tab_trends:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(1));
//                        setTabSelection(1);
                        break;
                    case R.id.tab_user:
                        new FragmentUtil(fm).showFragment(R.id.frg_container, mFragments.get(2));
//                        setTabSelection(2);
                        break;
                }
            }
        });
    }
        /**
     * 设置状态栏颜色
     */
    public void initSystemBarTint(int color) {
        Window window = getWindow();
        // 设置状态栏全透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        // 沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //5.0以上使用原生方法
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //4.4-5.0使用三方工具类
            /*getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(Color.WHITE);*/
        }
    }

}
