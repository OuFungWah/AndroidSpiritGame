package com.example.androidspiritgame.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by 区枫华 on 2017/5/24.
 */

public class InitHeroList {

    private static List<Hero> list;
    private static Random random = new Random();

    //获取静态英雄对象列表
    public static List<Hero> getHeroList(int num) {
        int tempBg;
        int tempStar;
        int tempHead;
        int tempNature;
        int tempRank;
        int tempLv;
        if (list == null) {
            list = new ArrayList<>();
            for (int i = 0; i < num; i++) {
                tempBg = ResBean.BACKGROUND_ID[random.nextInt(ResBean.BACKGROUND_ID.length)];
                tempStar = random.nextInt(5);
                tempHead = ResBean.HERO_HEAD_ID[random.nextInt(ResBean.HERO_HEAD_ID.length)];
                tempNature = ResBean.NATURE_ID[random.nextInt(ResBean.NATURE_ID.length)];
                tempRank = ResBean.RANK_ID[random.nextInt(ResBean.RANK_ID.length)];
                tempLv = random.nextInt(100);
                list.add(new Hero(tempBg, tempHead, tempNature, tempRank, tempStar, tempLv));
            }
        }
        return list;
    }

}
