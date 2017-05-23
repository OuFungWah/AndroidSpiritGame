package com.example.androidspiritgame.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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
    //动画
    private Animation btnAnim;

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
    }

    @Override
    public void onClick(View v) {
        v.startAnimation(btnAnim);
        switch (v.getId()){

        }
    }
}

