package com.core.adapter;

/**
 * Created by sufun_job on 2016/1/19.
 * 当用户点击某个条目时候的回调事件
 */
public interface IOnItemtClickListerner<T> {
    void OnItemClick(int postition,int eventId,T model);
}
