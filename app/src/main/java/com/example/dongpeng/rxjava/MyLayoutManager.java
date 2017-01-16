package com.example.dongpeng.rxjava;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dongpeng on 2017/1/16.
 */

public class MyLayoutManager extends RecyclerView.LayoutManager {
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        int itemCount=getItemCount();
        for (int i = 0; i < itemCount; i++) {
            View view=recycler.getViewForPosition(i);
            addView(view);
            measureChildWithMargins(view,0,0);
            int widthSpace=getWidth()-getDecoratedMeasuredWidth(view);
            int heightSpace=getHeight()-getDecoratedMeasuredHeight(view);
            layoutDecoratedWithMargins(view,widthSpace/2,heightSpace/2,widthSpace/2+getDecoratedMeasuredWidth(view),heightSpace/2+getDecoratedMeasuredHeight(view));
            int level=i+1;
            if (i>itemCount-CardConfig.MAX_COUNT-1){
                view.setScaleY(1-(itemCount-level)*0.05f);
                view.setScaleX(1-(itemCount-level)*0.05f);
                view.setTranslationY((itemCount-level)*50f);
            }else{
                view.setScaleY(CardConfig.MAX_COUNT*0.05f);
                view.setScaleX(CardConfig.MAX_COUNT*0.05f);
                view.setTranslationY(CardConfig.MAX_COUNT*50f);
            }
        }
    }
}
