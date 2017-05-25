package com.example.androidspiritgame.activity;

import android.app.Dialog;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.example.androidspiritgame.R;
import com.example.androidspiritgame.adapter.MyRecyclerAdapter;
import com.example.androidspiritgame.adapter.MyVPAdapter;
import com.example.androidspiritgame.bean.Hero;
import com.example.androidspiritgame.bean.InitHeroList;
import com.example.androidspiritgame.fragment.HomePageFragment;
import com.example.androidspiritgame.fragment.ShopFragment;
import com.example.androidspiritgame.util.Tools;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseFragmentActivity implements View.OnTouchListener {

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
    private Animation enlargeAnim;
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

    //对话框
    private Dialog dialog;
    private View dialogView;
    int dialogHeight;
    int dialogWidth;

    //对话框控件
    private ImageView dialog_close_btn;
    private RelativeLayout marge_all_dialog_rl;
    private RelativeLayout attack_dialog_rl;
    private RelativeLayout sell_dialog_rl;
    private RelativeLayout upgrade_dialog_rl;
    private RelativeLayout evolve_dialog_rl;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;

    private MyRecyclerAdapter myRecyclerAdapter;

    private List<Hero> heroList;

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
        enlargeAnim = AnimationUtils.loadAnimation(this, R.anim.btn_anim_enlarge);

        narrowAnim.setFillAfter(true);
        enlargeAnim.setFillAfter(true);

        retractAnim = AnimationUtils.loadAnimation(this, R.anim.super_menu_anim);
        popMenuAnim = AnimationUtils.loadAnimation(this, R.anim.super_menu_pop_anim);
        //设置过度动画时间
        retractAnim.setDuration(SUPER_MENU_ANIMATION_TIME);
        popMenuAnim.setDuration(SUPER_MENU_ANIMATION_TIME);
        //新建适配器
        adapter = new MyVPAdapter(getSupportFragmentManager(), list);

        //初始化对话框及其宽高参数
        dialog = new Dialog(this, R.style.MyDialog);
        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        dialogHeight = displayMetrics.heightPixels;
        dialogWidth = displayMetrics.widthPixels;

        gridLayoutManager = new GridLayoutManager(this, 5, LinearLayoutManager.VERTICAL, false);

        heroList = InitHeroList.getHeroList(25);

        myRecyclerAdapter = new MyRecyclerAdapter(heroList);

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

        dialogView = LayoutInflater.from(this).inflate(R.layout.bag_dialog, null);

        dialog_close_btn = findView(dialogView, R.id.dialog_close_btn);
        marge_all_dialog_rl = findView(dialogView, R.id.marge_all_dialog_rl);
        attack_dialog_rl = findView(dialogView, R.id.attack_dialog_rl);
        sell_dialog_rl = findView(dialogView, R.id.sell_dialog_rl);
        upgrade_dialog_rl = findView(dialogView, R.id.upgrade_dialog_rl);
        evolve_dialog_rl = findView(dialogView, R.id.evolve_dialog_rl);
        recyclerView = findView(dialogView, R.id.recycle_list);

    }

    @Override
    protected void setView() {

        dialog.setContentView(dialogView, new ViewGroup.LayoutParams(dialogWidth, dialogHeight));

        home_bg_ll.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        viewPager.setAdapter(adapter);
        leftProgess.setMax(100);
        rightProgess.setMax(100);
        leftProgess.setProgress(progress);
        rightProgess.setProgress(progress);
        thread.start();

        recyclerView.setAdapter(myRecyclerAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void initListener() {

        dialog_close_btn.setOnTouchListener(this);
        marge_all_dialog_rl.setOnTouchListener(this);
        attack_dialog_rl.setOnTouchListener(this);
        sell_dialog_rl.setOnTouchListener(this);
        upgrade_dialog_rl.setOnTouchListener(this);
        evolve_dialog_rl.setOnTouchListener(this);

        home_bottom_btn_img.setOnTouchListener(this);
        level_bottom_btn_img.setOnTouchListener(this);
        rob_bottom_btn_img.setOnTouchListener(this);
        shop_bottom_btn_img.setOnTouchListener(this);
        friends_bottom_btn_img.setOnTouchListener(this);
        setting_bottom_btn_img.setOnTouchListener(this);
        left_top_today_mission_img.setOnTouchListener(this);
        pop_menu_btn_rl.setOnTouchListener(this);
        retract_btn_rl.setOnTouchListener(this);
        backpage_super_btn_rl.setOnTouchListener(this);
        honor_super_btn_rl.setOnTouchListener(this);
        message_super_btn_rl.setOnTouchListener(this);
        mission_super_btn_rl.setOnTouchListener(this);
        battle_super_btn_rl.setOnTouchListener(this);
        marge_super_btn_rl.setOnTouchListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.home_bottom_btn_img:
                viewPager.setCurrentItem(0);
                break;
            case R.id.shop_bottom_btn_img:
                viewPager.setCurrentItem(1);
                break;
            case R.id.backpage_super_btn_rl:
                dialog.show();
                break;
            case R.id.retract_btn_rl:
                //收起菜单栏
                Tools.moveYTo(super_menu_rl, super_menu_rl.getHeight(), SUPER_MENU_ANIMATION_TIME);
                handler.postDelayed(runnable, SUPER_MENU_ANIMATION_TIME - 100);
                break;
            case R.id.pop_menu_btn_rl:
                //拉出菜单栏
                Tools.moveYTo(super_menu_rl, -super_menu_rl.getHeight(), SUPER_MENU_ANIMATION_TIME);
                pop_menu_btn_rl.setVisibility(View.GONE);
                break;
            case R.id.dialog_close_btn:
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
        }
    }

    /**
     * 触摸事件
     *
     * @param v
     * @param event
     * @return
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                //按下
                Tools.btnPress(v);
                break;
            case MotionEvent.ACTION_UP:
                //松开
                Tools.btnRelease();
                onClick(v);
                break;
        }
        return true;
    }

}
