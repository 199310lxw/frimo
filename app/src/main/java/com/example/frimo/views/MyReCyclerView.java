package com.example.frimo.views;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class MyReCyclerView extends RecyclerView {
    private String TAG = "MyReCyclerView";
    private float mDownX;
    private float mDownY;
    private static int sHeaderViewMeasuredHeight;
    private View mHeaderView;
    private float mDx;
    private float mDy;
    private boolean isFirstLoad;
    public MyReCyclerView(@NonNull Context context) {
        super(context);
    }
    public MyReCyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public MyReCyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        isFirstLoad=true;
    }
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d(TAG, "onLayout: " + sHeaderViewMeasuredHeight);
        if (isFirstLoad) {
            //将sHeaderViewMeasuredHeight设置为static，只要是为了防止页面间切换出现bug
            if (sHeaderViewMeasuredHeight == 0) {//只有当第一次layout时才会为sHeaderViewMeasuredHeight赋值
                sHeaderViewMeasuredHeight = mHeaderView.getMeasuredHeight();//获取HeaderView的高度
            }
            updateMargin(-sHeaderViewMeasuredHeight);//修改HeaderView的TopMargin值使其隐藏
        }
    }
    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }
    //修改marginTop控制HeaderView的显示
    private void updateMargin(int marginTop) {
        mHeaderView.setTop(marginTop);//由于单独修改margin，top没变，所以top也要修改
        LayoutParams layoutParams = (LayoutParams) mHeaderView.getLayoutParams();
        layoutParams.setMargins(layoutParams.leftMargin, marginTop, layoutParams.rightMargin, layoutParams.bottomMargin);
        mHeaderView.setLayoutParams(layoutParams);
        isFirstLoad = false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        //下拉刷新处理
        if (mHeaderView == getChildAt(0)) {
            switch (e.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    mDownX = e.getX();
                    mDownY = e.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    float moveX = e.getX();
                    float moveY = e.getY();
                    mDx = moveX - mDownX;
                    mDy = moveY - mDownY;
                    if (Math.abs(mDy) > Math.abs(mDx) && mDy > 0) {
                        if (mDy < 2 * sHeaderViewMeasuredHeight) {//小于两倍height时，逐渐修改TopMargin,使HeaderView逐渐显现出来
                            updateMargin(-sHeaderViewMeasuredHeight + (int) mDy);
//                            if (mOnPullDownListener != null) {
//                                mOnPullDownListener.onPullDownProgress(mDy / sHeaderViewMeasuredHeight);//将占比返回给监听者
//                            }
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    float upX = e.getX();
                    float upY = e.getY();
                    mDx = upX - mDownX;
                    mDy = upY - mDownY;
                    if (Math.abs(mDy) > Math.abs(mDx) && mDy > 0 && mHeaderView.getTop() > -sHeaderViewMeasuredHeight) {
                        if (mDy < sHeaderViewMeasuredHeight * 2 / 3) {//小于两倍height时，TopMargin置为-sHeaderViewMeasuredHeight，使其隐藏
                            updateMargin(-sHeaderViewMeasuredHeight);
                        } else {//否则使其完全显现
                            updateMargin(0);
//                            if (mOnRefreshListener != null) {
//                                //下拉释放后开始刷新
//                                mOnRefreshListener.onStartRefresh();//通知监听者开始刷新
//                            }
                        }
                    }
                    break;
            }
        }
        return super.onTouchEvent(e);
    }
}
