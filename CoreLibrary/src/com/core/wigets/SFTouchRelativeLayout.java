package com.core.wigets;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
public class SFTouchRelativeLayout extends RelativeLayout {

	private Drawable default_dra=null;	
	private Context mContext;
	public SFTouchRelativeLayout(Context context, AttributeSet attrs,
								 int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		default_dra=getBackground();
		mContext=context;
	}

	public SFTouchRelativeLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		default_dra=getBackground();
		mContext=context;
	}

	public SFTouchRelativeLayout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		default_dra=getBackground();
		mContext=context;
	}
	
	@SuppressLint("NewApi")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
//		int actionType=event.getAction();
//		switch (actionType) {
//		case MotionEvent.ACTION_DOWN:
//			setBackgroundResource(R.color.menu_item_bg);
//			//this.setBackgroundResource(mContext.getResources().getColor(R.color.menu_item_bg));
//			break;
//		case MotionEvent.ACTION_UP:
//			if(default_dra!=null)
//			setBackground(default_dra);
//			break;
//
//		case MotionEvent.ACTION_MOVE:
//			
//			break;
//		default:
//			if(default_dra!=null)
//			setBackground(default_dra);
//			break;
//		}
//		
		return super.onTouchEvent(event);
	}

}
