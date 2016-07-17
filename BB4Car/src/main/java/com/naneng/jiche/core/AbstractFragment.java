package com.naneng.jiche.core;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.R;
import com.core.widget.progress.CircularProgress;

/**
 * @author miaoxin.ye
 * @Description: Fragment基类
 * @date 2013-12-29 下午11:42:00
 */
public abstract class AbstractFragment extends Fragment {
	protected boolean isTemplate = false; // 是否使用模板
	protected View rootView;
	protected CircularProgress fragment_loading;
	protected View root;

	public int curPage=1; //当前的页码

	private LinearLayout fragment_body;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (isTemplate) {
			root = inflater.inflate(R.layout.mall_template_fragment, container, false);
		} else {
			return rootView;
		}
		return root;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view,savedInstanceState);
		if (savedInstanceState == null && isTemplate) {
			fragment_loading = (CircularProgress) view.findViewById(R.id.fragment_loading);
			fragment_body = (LinearLayout) view.findViewById(R.id.fragment_body);
			fragment_body.addView(rootView);
		}
	}

	/**
	 * 消除加载圈
	 */
	protected void dimssLoadingDialog(){
		fragment_body.setVisibility(View.VISIBLE);
		fragment_loading.setVisibility(View.GONE);
	}
	/**
	 * 显示加载圈
	 */
	protected  void showLoadingDialog(){
//		fragment_body.setVisibility(View.GONE);
		fragment_loading.setVisibility(View.VISIBLE);
	}

	/**
	 * 获取会员信息对象
	 */
//	protected Member getMember(){
//		return ((ClubApplication)getActivity().getApplication()).getMember();
//	}
	
	/**
	 * 设置标题
	 */
	protected void setTitleName(String titleName){
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.setTitleName(titleName);
		}
	}

	/**
	 * 设置标题
	 */
	protected void setTitleName(int stringID) {
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.setTitleName(stringID);
		}
	}

	/**
	 * 设置左按钮事件
	 */
	protected void setTitleLeftButtonListener(View.OnClickListener listener){
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.setTitleLeftButtonListener(listener);
		}
	}

	/**
	 * 设置左按钮背景和事件
	 */
	protected void setTitleLeftImageButton(int imgID,View.OnClickListener listener){
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.setTitleLeftImageButton(imgID, listener);
		}
	}

	/**
	 * 设置右按钮文本
	 */
	protected void setTitleRightButtonText(String text,View.OnClickListener listener) {
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.setTitleRightButtonText(text,listener);
		}
	}

	/**
	 * 设置右按钮文本和事件
	 * @param text
	 * @param listener
	 */
	protected void setTitleRightButton(String text, View.OnClickListener listener) {
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.setTitleRightButtonText(text, listener);
		}
	}

	/**
	 * 设置右按钮背景和事件
	 */
	protected void setTitleRightImageButton(int imageResId,View.OnClickListener listener) {
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.setTitleRightImageButton(imageResId,listener);
		}
	}

	/**
	 * 隐藏右侧按钮
	 */
	public void hiddenTitleRightButton() {
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.hiddenTitleRightButton();
		}
	}
	/**
	 * 显示右侧按钮
	 */
	protected void showTitleRightButton() {
		if(((AbstractActivity)getActivity()).titleView!=null){
			((AbstractActivity)getActivity()).titleView.showTitleRightButton();
		}
	}

	/**
	 * 设置页面背景
	 */
	protected void setPageBackground() {
		if(((AbstractActivity)getActivity()).mainBody!=null){
//			((AbstractActivity)getActivity()).mainBody.setBackgroundResource(R.drawable.page_bg);
		}
	}

	/**
	 * 设置页面背景颜色
	 */
	protected void setPageBackgroundCorlor() {
		if(((AbstractActivity)getActivity()).mainBody!=null){
//			((AbstractActivity)getActivity()).mainBody.setBackgroundColor(getResources().getColor(R.color.gray_bg));
		}
	}

	
	/**
	 * @author caibing.zhang
	 * @createdate 2015年3月21日 上午11:55:49
	 * @Description: 获取版本号
	 * @return
	 */
	private int getVersionCode() {
		try {
			// 获取packagemanager的实例
			PackageManager packageManager = getActivity().getPackageManager();
			// getPackageName()是你当前类的包名，0代表是获取版本信息
			PackageInfo packInfo = packageManager.getPackageInfo(
					getActivity().getPackageName(), 0);
			return packInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 1;
		}
	}
}
