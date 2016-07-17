package com.core.wigets;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * 2015年6月11日 17:03:58
 * 
 * @author sufun_job 用于感知控件的放大与缩小的手势的控件
 */
public class SFTouchScaleRelativeLauout extends RelativeLayout {
	/**
	 * 子类相对于父类的偏移量
	 */
	private int center_offset_x = 0;
	/**
	 * 子控件相对于父类的偏移量
	 */
	private int center_offset_y = 0;
	/**
	 * 是否需要进行截断事件的传弟
	 */
	private boolean need_intercept_act = false;

	/**
	 * 当前的用户的放大倍数： 默认为1 即没有放大
	 */
	private float scale_current = 1;
	/**
	 * 是否是处于缩放图片
	 */
	private boolean isZoom = false;

	float oldDist = 0;
	/**
	 * 中心点坐标
	 */
	PointF midPoint = new PointF();
	/**
	 * 中心点的坐标
	 */
	PointF centerPoint = new PointF();

	public SFTouchScaleRelativeLauout(Context context, AttributeSet attrs,
									  int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public SFTouchScaleRelativeLauout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public SFTouchScaleRelativeLauout(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		// 如果是单手操作---> 在放大的情况下：不让他往下传递 在正常的情况下：让他往下传递

		// 如果是又手操作------>无论是何种情况，都不让他往下传递

		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:

			break;
		case MotionEvent.ACTION_POINTER_UP:
			int pointerCount = event.getPointerCount();

			isZoom = false;
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			oldDist = spacing(event);
			midPoint(midPoint, event);
			isZoom = true;
			break;
		case MotionEvent.ACTION_MOVE:
			if (isZoom)//
			{
				// 双手操作时释放不让事件往下传递
				need_intercept_act = true;
				//
				float newDist = spacing(event);

				/**
				 * 表示新的距离比两个手指刚触碰的距离大 ( +10个像素用来延迟一下放大，不然稍微动一点像素,也放大，感觉也太快了。)
				 */
				if (newDist + 10 > oldDist) {
					Log.i("", "放大操作");
				}
				/**
				 * 表示新的距离比两个手指刚触碰的距离小
				 */
				if (newDist + 10 < oldDist) {
					Log.i("", "缩小操作");
				}

			} else// 单手操作的时候
			{
				need_intercept_act = scale_current != 1;
			}
			break;
		default:
			break;
		}

		return super.onTouchEvent(event);
	}

	private float spacing(MotionEvent event) {
		float x = event.getX(0) - event.getX(1);
		float y = event.getY(0) - event.getY(1);
		return FloatMath.sqrt(x * x + y * y);
	}

	private void midPoint(PointF point, MotionEvent event) {
		float x = event.getX(0) + event.getX(1);
		float y = event.getY(0) + event.getY(1);
		point.set(x / 2, y / 2);
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		return need_intercept_act;
	}

	public interface IOnTouchScaleEventHandler {
		/**
		 * 手指进行缩放
		 * 
		 * @param Scale
		 * @param centX
		 * @param centY
		 */
		void OnScale(float Scale, float centX, float centY);

		/**
		 * 单手操作放大情况下，或者是缩小的情况下
		 * 
		 * @param pox
		 * @param poy
		 */
		void OnDrop(float pox, float poy);

		/**
		 * 缩回到正常的情况下
		 */
		void OnZoom2Normal();
	}

	private View mSubViews;

	void initSubViews() {
		if (this.getChildCount() > 0)
			mSubViews = this.getChildAt(0);
	}
}
