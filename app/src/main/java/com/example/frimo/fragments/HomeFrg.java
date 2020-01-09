package com.example.frimo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.frimo.R;
import com.example.frimo.adapters.MyAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;


public class HomeFrg extends Fragment implements View.OnClickListener {

    private ImageView img_location;
    private final String TAG = "HomeFragment";
    private MyAdapter mAdapter;
    private RecyclerView mRv;
    private List<String> list_data = new ArrayList<>();

    private int page_count = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frg_home, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        img_location=view.findViewById(R.id.img_location);
        initSmartRefresh(view);
        initRv(view);
        initEvent();
    }
    private void initEvent(){
        img_location.setOnClickListener(this);
    }

    private void initSmartRefresh(View view) {
        RefreshLayout refreshLayout = (RefreshLayout) view.findViewById(R.id.mRefreshLayout);
        refreshLayout.setEnableLoadMoreWhenContentNotFull(false);//取消内容不满一页时开启上拉加载功能
        refreshLayout.setEnableAutoLoadMore(false);//使上拉加载具有弹性效果
        refreshLayout.setEnableOverScrollDrag(false);//禁止越界拖动（1.0.4以上版本）
        refreshLayout.setEnableOverScrollBounce(false);//关闭越界回弹功能
        refreshLayout.setEnableAutoLoadMore(true);//没有上拉，快速滚动列表，Footer自己冒上来了？
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshData();
                mAdapter.notifyDataSetChanged();
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                //加载结束之后的逻辑
                LoadMoreData();
                if (page_count < 4) {
                    refreshLayout.finishLoadMore();
                } else {
                    refreshLayout.finishLoadMoreWithNoMoreData();//显示全部加载完成，并不再触发加载更事件
                }
                mAdapter.notifyDataSetChanged();
                refreshLayout.finishLoadMore(2000);
            }
        });
        refreshLayout.autoRefresh();//自动刷新
    }

    private void initRv(View view) {
        mRv = view.findViewById(R.id.recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(getActivity(), refreshData());
        mRv.setAdapter(mAdapter);
    }

    private List<String> refreshData() {

        if (list_data != null) {
            list_data.clear();
            page_count = 0;
        }
        for (int i = 0; i < 20; i++) {
            list_data.add("我是第" + i + "项");
        }
        return list_data;
    }

    private void LoadMoreData() {
        page_count++;
        for (int i = 0; i < 20; i++) {
            list_data.add("我是第" + i + "项");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.img_location:
                break;
        }
    }
}
