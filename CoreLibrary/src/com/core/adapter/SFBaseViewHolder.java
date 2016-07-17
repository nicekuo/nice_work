package com.core.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by sufun_job on 2016/1/19.
 * 基础的viewholder类
 */
public abstract class SFBaseViewHolder extends RecyclerView.ViewHolder{

    public int mposition=0;

    private View mView;
    public SFBaseViewHolder(View view)
    {
        super(view);
        this.mView=view;
        initViewHolder(mView);
    }

    /**
     * 初始化实例化相关的viewholder
     * @param view
     */
    public abstract void initViewHolder(View view);

    /**
     * 将前端的数据与position进行绑定
     * @param position
     */
    public abstract void onBindData(int position);
}
