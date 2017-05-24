package com.example.androidspiritgame.fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.androidspiritgame.R;

/**
 * Created by 区枫华 on 2017/5/23.
 */

public class ShopFragment extends Fragment implements View.OnClickListener {

    //商店按钮布局
    private RelativeLayout charge_money_shop_rl;
    private RelativeLayout buy_shop_rl;
    private RelativeLayout expand_backpack_shop_rl;
    private RelativeLayout charge_energy_shop_rl;
    private RelativeLayout change_spirit_shop_rl;
    private ImageView dialog_close_btn;
    private RelativeLayout change_money_dialog_rl;
    private RelativeLayout change_exchange_dialog_rl;
    private RelativeLayout be_vip_btn_dialog_rl;
    private RelativeLayout money_btn1_dialog_rl;
    private RelativeLayout money_btn2_dialog_rl;
    private RelativeLayout money_btn3_dialog_rl;
    //动画
    private Animation btnAnim;
    //对话框
    private Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_fragment, null);

        charge_money_shop_rl = (RelativeLayout) view.findViewById(R.id.charge_money_shop_rl);
        buy_shop_rl = (RelativeLayout) view.findViewById(R.id.buy_shop_rl);
        expand_backpack_shop_rl = (RelativeLayout) view.findViewById(R.id.expand_backpack_shop_rl);
        charge_energy_shop_rl = (RelativeLayout) view.findViewById(R.id.charge_energy_shop_rl);
        change_spirit_shop_rl = (RelativeLayout) view.findViewById(R.id.change_spirit_shop_rl);

        charge_money_shop_rl.setOnClickListener(this);
        buy_shop_rl.setOnClickListener(this);
        expand_backpack_shop_rl.setOnClickListener(this);
        charge_energy_shop_rl.setOnClickListener(this);
        change_spirit_shop_rl.setOnClickListener(this);

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnAnim = AnimationUtils.loadAnimation(getContext(), R.anim.btn_anim_narrow);

        dialog = new Dialog(getContext(), R.style.MyDialog);

        //实现对话框布局
        View view = LayoutInflater.from(getContext()).inflate(R.layout.self_dialog_include, null);
        //查找相应布局控件
        dialog_close_btn = (ImageView) view.findViewById(R.id.dialog_close_btn);
        change_money_dialog_rl = (RelativeLayout) view.findViewById(R.id.change_money_dialog_rl);
        change_exchange_dialog_rl = (RelativeLayout) view.findViewById(R.id.change_exchange_dialog_rl);
        be_vip_btn_dialog_rl = (RelativeLayout) view.findViewById(R.id.be_vip_btn_dialog_rl);
        money_btn1_dialog_rl = (RelativeLayout) view.findViewById(R.id.money_btn1_dialog_rl);
        money_btn2_dialog_rl = (RelativeLayout) view.findViewById(R.id.money_btn2_dialog_rl);
        money_btn3_dialog_rl = (RelativeLayout) view.findViewById(R.id.money_btn3_dialog_rl);

        //设置对话框按钮监听
        dialog_close_btn.setOnClickListener(this);
        change_money_dialog_rl.setOnClickListener(this);
        change_exchange_dialog_rl.setOnClickListener(this);
        be_vip_btn_dialog_rl.setOnClickListener(this);
        money_btn1_dialog_rl.setOnClickListener(this);
        money_btn2_dialog_rl.setOnClickListener(this);
        money_btn3_dialog_rl.setOnClickListener(this);

        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        dialog.setContentView(view, new ViewGroup.LayoutParams(width, height));

        //第二种设置高度的方法
//        dialog.setContentView(view);
//        Window dialog_window = dialog.getWindow();
//        //获取到LayoutParams
//        WindowManager.LayoutParams dialog_window_attributes = dialog_window.getAttributes();
//        //设置宽度
//        dialog_window_attributes.width = width;
//        //设置高度
//        dialog_window_attributes.height = height;
//        dialog_window.setAttributes(dialog_window_attributes);
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(btnAnim);
        switch (v.getId()) {
            case R.id.charge_money_shop_rl:
                dialog.show();
                break;
            case R.id.dialog_close_btn:
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                break;
        }
    }
}

