package com.example.androidspiritgame.bean;

import com.example.androidspiritgame.R;

/**
 * Created by 区枫华 on 2017/a5/24.
 */

public class Hero {

    private int backgroundRes;
    private int stars;
    private int headRes;
    private int natureRes;
    private int rankRes;
    private int lv;

    public Hero() {

    }

    /**
     * @param backgroundRes 背景资源
     * @param headRes       头像资源
     * @param natureRes     属性资源
     * @param rankRes       等级资源
     * @param stars         星等
     * @param lv            等级
     */
    public Hero(int backgroundRes, int headRes, int natureRes, int rankRes, int stars, int lv) {
        setBackgroundRes(backgroundRes);
        setHeadRes(headRes);
        setNatureRes(natureRes);
        setStars(stars);
        setRankRes(rankRes);
        setLv(lv);
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public void setBackgroundRes(int backgroundRes) {
        this.backgroundRes = backgroundRes;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getHeadRes() {
        return headRes;
    }

    public void setHeadRes(int headRes) {
        this.headRes = headRes;
    }

    public int getNatureRes() {
        return natureRes;
    }

    public void setNatureRes(int natureRes) {
        this.natureRes = natureRes;
    }

    public int getRankRes() {
        return rankRes;
    }

    public void setRankRes(int rankRes) {
        this.rankRes = rankRes;
    }


    public int getLv() {
        return lv;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
}
