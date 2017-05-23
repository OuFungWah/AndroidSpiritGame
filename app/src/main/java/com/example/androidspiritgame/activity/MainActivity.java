package com.example.androidspiritgame.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.androidspiritgame.R;
import com.example.androidspiritgame.adapter.MyVPAdapter;
import com.example.androidspiritgame.fragment.HomePageFragment;
import com.example.androidspiritgame.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity {

    //标识值
    private final int RETRACT_ANIMATION_COMPLETE = 0;
    private final int POP_ANIMATION_COMPLETE = 1;
    private final int REFRESH_PROGRESSBAR = 2;
    private final String TAG = "MainActivity";

    //进度
    private int progress = 0;

    //动画进行时间
    private final int SUPER_MENU_ANIMATION_TIME = 700;

    private LinearLayout home_bg_ll;
    //底部菜单的所有按钮
    private ImageView home_bottom_btn_img;
    private ImageView level_bottom_btn_img;
    private ImageView rob_bottom_btn_img;
    private ImageView shop_bottom_btn_img;
    private ImageView friends_bottom_btn_img;
    private ImageView setting_bottom_btn_img;
    private ImageView left_top_today_mission_img;
    //二级菜单
    private RelativeLayout super_menu_rl;
    //二级菜单的所有按钮
    private RelativeLayout backpage_super_btn_rl;
    private RelativeLayout honor_super_btn_rl;
    private RelativeLayout message_super_btn_rl;
    private RelativeLayout mission_super_btn_rl;
    private RelativeLayout battle_super_btn_rl;
    private RelativeLayout marge_super_btn_rl;
    //二级菜单收起按钮
    private RelativeLayout retract_btn_rl;
    //二级菜单弹起按钮
    private RelativeLayout pop_menu_btn_rl;
    //按键缩放动画
    private Animation narrowAnim;
    //菜单栏伸缩动画
    private Animation retractAnim;
    private Animation popMenuAnim;
    //进度条
    private ProgressBar leftProgess;
    private ProgressBar rightProgess;

    //填充ProgressBar的线程
    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //清零
                if (progress >= 100) progress = 0;
                progress++;
                handler.sendEmptyMessage(REFRESH_PROGRESSBAR);
            }
        }
    });

    //Runnale
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(RETRACT_ANIMATION_COMPLETE);
        }
    };

    //响应器
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case RETRACT_ANIMATION_COMPLETE:
                    pop_menu_btn_rl.setVisibility(View.VISIBLE);
                    break;
                case REFRESH_PROGRESSBAR:
                    leftProgess.setProgress(progress);
                    rightProgess.setProgress(progress);
                    break;
            }
            return true;
        }
    });

    //
    private List<Fragment> list = new ArrayList();

    //ViewPager
    private ViewPager viewPager;
    private MyVPAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        init(R.layout.activity_main);
    }

    @Override
    protected void initObject() {
        //初始化list
        list.add(new HomePageFragment());
        list.add(new ShopFragment());
        //初始化动画
        narrowAnim = AnimationUtils.loadAnimation(this, R.anim.btn_anim_narrow);
        retractAnim = AnimationUtils.loadAnimation(this, R.anim.super_menu_anim);
        popMenuAnim = AnimationUtils.loadAnimation(this, R.anim.super_menu_pop_anim);
        //设置过度动画时间
        retractAnim.setDuration(SUPER_MENU_ANIMATION_TIME);
        popMenuAnim.setDuration(SUPER_MENU_ANIMATION_TIME);
        //新建适配器
        adapter = new MyVPAdapter(getSupportFragmentManager(), list);
    }

    @Override
    protected void initView() {
        home_bg_ll = findView(R.id.home_bg_ll);

        leftProgess = findView(R.id.main_progress_left_pb);
        rightProgess = findView(R.id.main_progress_right_pb);

        home_bottom_btn_img = findView(R.id.home_bottom_btn_img);
        level_bottom_btn_img = findView(R.id.level_bottom_btn_img);
        rob_bottom_btn_img = findView(R.id.rob_bottom_btn_img);
        shop_bottom_btn_img = findView(R.id.shop_bottom_btn_img);
        friends_bottom_btn_img = findView(R.id.friends_bottom_btn_img);
        setting_bottom_btn_img = findView(R.id.setting_bottom_btn_img);
        left_top_today_mission_img = findView(R.id.left_top_today_mission_img);

        super_menu_rl = findView(R.id.super_menu_rl);
        retract_btn_rl = findView(R.id.retract_btn_rl);
        pop_menu_btn_rl = findView(R.id.pop_menu_btn_rl);
        backpage_super_btn_rl = findView(R.id.backpage_super_btn_rl);
        honor_super_btn_rl = findView(R.id.honor_super_btn_rl);
        message_super_btn_rl = findView(R.id.message_super_btn_rl);
        mission_super_btn_rl = findView(R.id.mission_super_btn_rl);
        battle_super_btn_rl = findView(R.id.battle_super_btn_rl);
        marge_super_btn_rl = findView(R.id.marge_super_btn_rl);

        viewPager = findView(R.id.main_viewpager);
    }

    @Override
    protected void setView() {
        home_bg_ll.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        viewPager.setAdapter(adapter);
        leftProgess.setMax(100);
        rightProgess.setMax(100);
        leftProgess.setProgress(progress);
        rightProgess.setProgress(progress);
        thread.start();
    }

    @Override
    protected void initListener() {
        home_bottom_btn_img.setOnClickListener(this);
        level_bottom_btn_img.setOnClickListener(this);
        rob_bottom_btn_img.setOnClickListener(this);
        shop_bottom_btn_img.setOnClickListener(this);
        friends_bottom_btn_img.setOnClickListener(this);
        setting_bottom_btn_img.setOnClickListener(this);
        left_top_today_mission_img.setOnClickListener(this);

        pop_menu_btn_rl.setOnClickListener(this);
        retract_btn_rl.setOnClickListener(this);
        backpage_super_btn_rl.setOnClickListener(this);
        honor_super_btn_rl.setOnClickListener(this);
        message_super_btn_rl.setOnClickListener(this);
        mission_super_btn_rl.setOnClickListener(this);
        battle_super_btn_rl.setOnClickListener(this);
        marge_super_btn_rl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(narrowAnim);
        switch (v.getId()) {
            case R.id.home_bottom_btn_img:
                viewPager.setCurrentItem(0);
                break;
            case R.id.shop_bottom_btn_img:
                viewPager.setCurrentItem(1);
                break;
            case R.id.backpage_super_btn_rl:
                break;
            case R.id.retract_btn_rl:
                super_menu_rl.startAnimation(retractAnim);
                handler.postDelayed(runnable, SUPER_MENU_ANIMATION_TIME - 50);
                break;
            case R.id.pop_menu_btn_rl:
                pop_menu_btn_rl.setVisibility(View.GONE);
                super_menu_rl.clearAnimation();
                break;
        }
    }
}
