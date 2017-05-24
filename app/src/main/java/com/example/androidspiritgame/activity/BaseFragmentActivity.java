package com.example.androidspiritgame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Toast;

import com.example.androidspiritgame.application.ContextHolder;

/**
 * Created by 区枫华 on 2017/a5/23.
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements View.OnClickListener {

    static Toast toast = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /**
     * 带布局资源初始化
     *
     * @param id 布局的Id
     */
    protected void init(int id) {
        setContentView(id);
        initView();
        initObject();
        setView();
        initListener();
    }

    /**
     * 展示单例Toast
     *
     * @param message
     */
    protected void showShort(String message) {
        //若toast为空
        if (toast == null) {
            toast = Toast.makeText(ContextHolder.getContext(), message, Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 展示单例Toast
     *
     * @param message
     */
    protected void showLong(String message) {
        //若toast为空
        if (toast == null) {
            toast = Toast.makeText(ContextHolder.getContext(), message, Toast.LENGTH_LONG);
        } else {
            toast.setText(message);
        }
        toast.show();
    }

    /**
     * 初始化对象
     */
    protected abstract void initObject();

    /**
     * 初始化View
     */
    protected abstract void initView();

    /**
     * 设置View
     */
    protected abstract void setView();

    /**
     * 初始化Listener
     */
    protected abstract void initListener();

    /**
     * 跳转页面
     *
     * @param T
     */
    protected void startActivity(Class T) {
        startActivity(new Intent(this, T));
    }

    /**
     * 带Bundle转跳
     *
     * @param T
     * @param bundle
     */
    protected void startActivity(Class T, Bundle bundle) {
        Intent i = new Intent(this, T);
        i.putExtras(bundle);
        startActivity(i);
    }

    /**
     * 实例化 View
     *
     * @param id
     */
    protected <T extends View> T findView(int id) {
        View view = findViewById(id);
        return (T) view;
    }

    /**
     * 实例化 View
     *
     * @param id
     */
    protected <T extends View> T findView(View parent, int id) {
        View view = parent.findViewById(id);
        return (T) view;
    }

    /**
     * 结束当前Activity转跳
     *
     * @param T
     */
    protected void startActivityWithTerminated(Class T) {
        Intent i = new Intent(this, T);
        startActivity(i);
        this.finish();
    }

    /**
     * 结束当前Activity带参数转跳
     *
     * @param T
     * @param bundle
     */
    protected void startActivityWithTerminated(Class T, Bundle bundle) {
        Intent i = new Intent(this, T);
        i.putExtras(bundle);
        startActivity(i);
        this.finish();
    }

}
