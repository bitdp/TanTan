package com.example.dongpeng.rxjava;

/**
 * Created by dongpeng on 2017/1/16.
 */

public interface ISwipeListener {
    /**
     * @param flag 0为左滑，1为右滑
     * @param position
     */
    public void selectedItem(int flag,int position);
}
