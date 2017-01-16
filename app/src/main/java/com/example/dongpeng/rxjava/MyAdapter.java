package com.example.dongpeng.rxjava;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by dongpeng on 2017/1/16.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<String> data;
    public MyAdapter(List<String> data) {
        this.data=data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.iv.setImageResource(R.mipmap.aaa);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public void removeItem(int position){
        //循环
        data.add(data.get(position));
        data.remove(position);
        notifyDataSetChanged();
//        notifyItemRemoved(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv,iv_left,iv_right;

        public MyViewHolder(View itemView) {
            super(itemView);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            iv_left = (ImageView) itemView.findViewById(R.id.iv_left);
            iv_right = (ImageView) itemView.findViewById(R.id.iv_right);
        }
    }
}
