package com.core.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sufun_job on 2016/1/19.
 * 一个用于最基础的Recycler的Adapter
 */
public abstract class SFBaseRecyclerAdapter<T> extends RecyclerView.Adapter<SFBaseViewHolder> {

    public Context mContext;

    public List<T> mDatas=new ArrayList<T>();

    private IOnItemtClickListerner<T>  delegate;

    public int getChild_layout_id() {
        return child_layout_id;
    }

    public void setChild_layout_id(int child_layout_id) {
        this.child_layout_id = child_layout_id;
    }

    public int child_layout_id=0;//子视图的item

    public SFBaseRecyclerAdapter(Context mContext,List<T> mdatas,IOnItemtClickListerner<T> clickListerner,Object other)
    {
        super();
        this.mContext=mContext;
        this.mDatas=mdatas;
        this.delegate=clickListerner;
    }

    @Override
    public SFBaseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(mContext).inflate(child_layout_id,null);
        return getViewHolder(view);
    }
    /**
     * 执行相关的数据绑定
     * @param viewHolder
     * @param i
     */
    @Override
    public  void onBindViewHolder(SFBaseViewHolder viewHolder, int i){
        viewHolder.mposition=i;
        viewHolder.onBindData(i);
    }

    /**
     * 当前一共所带有数据
     * @return
     */
    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 取得所需要的holder
     * @param v
     * @return
     */
    public abstract SFBaseViewHolder getViewHolder(View v);
}
