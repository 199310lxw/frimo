package com.example.frimo.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class FragmentUtil {
    private static FragmentManager manager;
    private static FragmentTransaction transaction;
    /**
     * 用于存放fragment的map
     */
    private static Map<Fragment, Boolean> fragmentBooleanMap;

    public FragmentUtil(FragmentManager manager){
        this.manager=manager;
    }
    /**
     * 显示Fragment  不存在则add添加显示 存在则调用show方法显示
     *
     *
     * @param layoutId FrameLayout布局id
     * @param fragment 显示的Fragment
     */
    public static void showFragment(int layoutId, Fragment fragment) {
        if (fragment == null) {
            fragment = new Fragment();
        }
        //开启transaction
        transaction = manager.beginTransaction();
        if (fragmentBooleanMap == null) {
            fragmentBooleanMap = new HashMap<>(8);
        }
        if (fragmentBooleanMap.size() == 0) {
            transaction.add(layoutId, fragment);
            //将fragment放进map中
            fragmentBooleanMap.put(fragment, true);
        } else {
            if (fragmentBooleanMap.containsKey(fragment)) {
                //判断隐藏掉当前显示的Fragment
                Set<Map.Entry<Fragment, Boolean>> set = fragmentBooleanMap.entrySet();
                for (Map.Entry<Fragment, Boolean> entry : set) {
                    boolean isShow = entry.getValue();
                    if (isShow) {
                        transaction.hide(entry.getKey());
                        entry.setValue(false);
                    }
                    if (entry.getKey() == fragment) {
                        entry.setValue(true);
                    }
                }
                //显示需要显示的fragment
                transaction.show(fragment);
            } else {
                Set<Map.Entry<Fragment, Boolean>> set = fragmentBooleanMap.entrySet();
                for (Map.Entry<Fragment, Boolean> entry : set) {
                    boolean isShow = entry.getValue();
                    if (isShow) {
                        transaction.hide(entry.getKey());
                        entry.setValue(false);
                    }
                }
                transaction.add(layoutId, fragment);
                fragmentBooleanMap.put(fragment, true);
            }
        }
        transaction.commit();
    }
}



