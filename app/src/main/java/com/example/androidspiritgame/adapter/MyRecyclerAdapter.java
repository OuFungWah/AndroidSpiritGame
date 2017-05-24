package com.example.androidspiritgame.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.androidspiritgame.R;
import com.example.androidspiritgame.bean.Hero;

import java.util.List;

/**
 * Created by 区枫华 on 2017/a5/24.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private List<Hero> list;

    public MyRecyclerAdapter(List<Hero> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    protected final static class ViewHolder extends RecyclerView.ViewHolder {

        protected ImageView background;
        protected ImageView nature;
        protected ImageView stars[] = new ImageView[5];
        protected ImageView rank;
        protected ImageView heroHead;

        public ViewHolder(View itemView) {
            super(itemView);
            this.background = (ImageView) itemView.findViewById(R.id.background_img);
            this.nature = (ImageView) itemView.findViewById(R.id.nature_img);
            this.stars[0] = (ImageView) itemView.findViewById(R.id.star1_img);
            this.stars[1] = (ImageView) itemView.findViewById(R.id.star2_img);
            this.stars[2] = (ImageView) itemView.findViewById(R.id.star3_img);
            this.stars[3] = (ImageView) itemView.findViewById(R.id.star4_img);
            this.stars[4] = (ImageView) itemView.findViewById(R.id.star5_img);
            this.rank = (ImageView) itemView.findViewById(R.id.rank_img);
            this.heroHead = (ImageView) itemView.findViewById(R.id.hero_img);

        }


    }

}
