package com.core.adapter;

import android.content.Context;
import android.view.View;

import com.core.bean.BaseBean;

import java.util.List;

/**
 * Created by sufun_job on 2016/1/19.
 *
 * 用于测试的测试Adapter
 */
public class DemoAdapter extends SFBaseRecyclerAdapter<BaseBean> {

    public DemoAdapter(Context mContext, List<BaseBean> mdatas, IOnItemtClickListerner<BaseBean> clickListerner, Object other) {
        super(mContext, mdatas, clickListerner, other);
    }

    @Override
    public SFBaseViewHolder getViewHolder(View v) {
        CoreHolder  holder=new CoreHolder(v);
        return holder;
    }

    public class CoreHolder extends SFBaseViewHolder
    {
        public CoreHolder(View view) {
            super(view);
        }

        @Override
        public void initViewHolder(View view) {

        }

        @Override
        public void onBindData(int position) {
               //绑定数据
        }
    }
}
