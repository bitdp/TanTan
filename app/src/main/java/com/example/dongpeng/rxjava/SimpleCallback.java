package com.example.dongpeng.rxjava;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

/**
 * Created by dongpeng on 2017/1/16.
 */

public class SimpleCallback extends ItemTouchHelper.Callback {
    private MyAdapter adapter;
    private boolean swipeLeft = false;
    private ISwipeListener listener;

    public SimpleCallback(ISwipeListener listener,MyAdapter adapter) {
        this.adapter = adapter;
        this.listener=listener;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int swipeFlag = makeFlag(ItemTouchHelper.ACTION_STATE_SWIPE, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP | ItemTouchHelper.DOWN);
        return makeMovementFlags(0, swipeFlag);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (swipeLeft){
            listener.selectedItem(0,viewHolder.getAdapterPosition());
        }else{
            listener.selectedItem(1,viewHolder.getAdapterPosition());
        }
        viewHolder.itemView.setRotation(0);
        adapter.removeItem(viewHolder.getAdapterPosition());
    }

    /**
     * 拖拽时绘制childview
     * getMoveThreshold 移动的阈值,默认为0.5f,也就是移动的距离为RecyclerView的高或宽的一半时才认为是移动，否则回到原位
     * getSwipeThreshold 移除的阈值,默认为0.5f,也就是移动的距离为RecyclerView的高或宽的一半时才认为是移除，否则回到原位
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        double swipe = Math.sqrt(dX * dX + dY * dY);//拖动的距离
        double fractionX = dX / getThreshold(recyclerView, viewHolder);
        double fraction = swipe / getThreshold(recyclerView, viewHolder);
        if (fraction > 1) {
            fraction = 1;
        }
        if (fractionX > 1) {
            fractionX = 1;
        }
        if (dX > 0) {
            swipeLeft = false;
        } else {
            swipeLeft = true;
        }
        int itemCount = recyclerView.getChildCount();
        for (int i = 0; i < itemCount; i++) {
            View childView = recyclerView.getChildAt(i);
            int level = i + 1;//itemcount=10；
            if (i > itemCount - CardConfig.MAX_COUNT-1) {
                if (i == itemCount - 1) {
                    childView.setRotation((float) (fractionX * 15));
                    if (swipeLeft) {
                        //左滑
                        ((MyAdapter.MyViewHolder) viewHolder).iv_right.setAlpha(Math.abs((float) fractionX));
                    } else {
                        //右滑
                        ((MyAdapter.MyViewHolder) viewHolder).iv_left.setAlpha(Math.abs((float) fractionX));
                    }
                } else {
                    childView.setScaleX((float) (1 - (itemCount - level) * 0.05f + fraction * 0.05f));
                    childView.setScaleY((float) (1 - (itemCount - level) * 0.05f + fraction * 0.05f));
                    childView.setTranslationY((float) ((itemCount - level) * 50 - fraction * 50));
                }

            } else {
                childView.setScaleX((float) (CardConfig.MAX_COUNT * 0.05f + fraction * 0.05f));
                childView.setScaleY((float) (CardConfig.MAX_COUNT * 0.05f + fraction * 0.05f));
                childView.setTranslationY((float) (CardConfig.MAX_COUNT * 50 - fraction * 50));
            }
        }
    }

    private double getThreshold(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return recyclerView.getWidth() * getSwipeThreshold(viewHolder);
    }

    @Override
    public float getSwipeThreshold(RecyclerView.ViewHolder viewHolder) {
        return 0.7f;
    }
}
