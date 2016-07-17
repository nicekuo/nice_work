package com.core.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.core.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author wu
 * 2015年4月20日 14:14:16
 * @description 基类的适配器
 * 
 */
public abstract class SFBaseAdapter<T extends BaseBean> extends BaseAdapter {


	private View mLoadingViewContent=null;
	/**
	 * 加载的视图的效果
	 */
	private int loading_res_layout_id=0;

	/**
	 * 用于存储的新数据
	 */
	protected List<T> mData=new ArrayList<T>();
	/**创建视图的上下文**/
	protected Context mContext;
	/**调用方的上下文档**/
	protected Object mobject;
	/**此字段用于后期的一些扩展字段**/
	protected Object mexObj;
	/**
	 * 
	 * @param data   用于展示的列表数据
	 * @param context  传入的上下文
	 * @param object   引用者
	 * @param exObj    此字段用于后期的一些扩展字段，一般没有效果
	 */
	public SFBaseAdapter(List<T> data, Context context, Object object, Object exObj) {
		// TODO Auto-generated constructor stub
		this.mobject=object;
		this.mContext=context;
		this.mData.addAll(data);
		this.mexObj=exObj;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
       return mData.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public abstract View getView(int position, View convertView, ViewGroup parent);
	/**向数据源里面添加数据**/
	public void addData(List<T> list)
	{
		this.mData.addAll(list);
	}
	/**设置相关的数据源**/
	public void setData(List<T> list)
	{
		if(this.mData!=null) {
			this.mData.clear();
		}
		this.mData.addAll(list);
	}
}
