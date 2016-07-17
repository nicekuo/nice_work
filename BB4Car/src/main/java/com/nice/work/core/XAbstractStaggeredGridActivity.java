package com.nice.work.core;

import com.core.widget.staggeredgrid.XStaggeredGridView.IXListViewListener;

public abstract class XAbstractStaggeredGridActivity 
		extends AbstractActivity implements IXListViewListener{

	/**
	 * @author caibing.zhang
	 * @createdate 2015年1月21日 下午2:08:35
	 * @Description: 
	 * @param type 1：点击item，2：点击Like，3：点击Buy
	 * @param position
	 */
	public abstract void setOnItemClick(int type,final int position);
}
