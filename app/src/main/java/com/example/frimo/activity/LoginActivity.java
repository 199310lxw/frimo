package com.example.frimo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.frimo.R;

public class LoginActivity extends BaseActivity {
    private TextView txt_register,txt_forget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }
    private void initView(){
        txt_forget=findViewById(R.id.txt_forget);
        txt_register=findViewById(R.id.txt_register);
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(in);
            }
        });
    }
}
