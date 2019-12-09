package com.example.frimo.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.frimo.R;


public class HomeFrg extends Fragment {
    private TextView main_top_txt_title;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.frg_home, null);
        initView(view);
        return view;
    }
    private void initView(View view){
        main_top_txt_title=view.findViewById(R.id.main_top_txt_title);
        main_top_txt_title.setText(getResources().getString(R.string.main_txt_homepage));
    }
}
