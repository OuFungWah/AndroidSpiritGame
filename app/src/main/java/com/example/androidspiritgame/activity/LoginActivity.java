package com.example.androidspiritgame.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.androidspiritgame.R;

/**
 * Created by 区枫华 on 2017/5/24.
 */

public class LoginActivity extends BaseActivity {

    private ImageView login_img;
    private ImageView logout_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(R.layout.login_activity);
    }

    @Override
    protected void initObject() {

    }

    @Override
    protected void initView() {
        login_img = findView(R.id.login_img);
        logout_img = findView(R.id.logout_img);
    }

    @Override
    protected void setView() {
        login_img.setOnClickListener(this);
        logout_img.setOnClickListener(this);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_img:
                startActivityWithTerminated(MainActivity.class);
                break;
            case R.id.logout_img:
                finish();
                break;
        }
    }
}
