package com.core.wigets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.Button;
import com.core.util.AndroidUtil;

/**
 * 带有小泡泡的按钮
 * 
 * @author sufun_job
 *
 */
@SuppressLint("ResourceAsColor")
public class SFBubbleButtonView extends Button {

	public SFBubbleButtonView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public SFBubbleButtonView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}
    /**本身的高**/
	int mHeight = 0;
    /**本身的宽**/
	int mWidth = 0;
    /**所画的圆年的半径**/
	int circle_r = 0;
	/**所画圆所在的中心x**/
	int circle_x = 0;
	/**所画圆所在的中心Y**/
	int circle_y = 0;
	/**当前消息显示的数量 **/
	private String count_str="0";
    /**是否允许展示出来**/	
	private boolean isShow=false;
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.TRANSPARENT);
		
		mHeight = getHeight();
		mWidth = getWidth();
		circle_r = AndroidUtil.dip2px(getContext(), 10);//getWidth() / 10;
		circle_x = getWidth() - circle_r;
		circle_y = circle_r;
		
		if(isShow==true)
		{
			SFdoDrawWhiteCircle(canvas);
			SFdoDrawRedCircle(canvas);
			SFsetBubbleTextSize(count_str, canvas);
		}
		else//清理原来显示出来的东西
		{
			//canvas.drawColor(Color.TRANSPARENT,PorterDuff.Mode.CLEAR);
			SFdoResetButtonStyle(canvas);
		}

	}
	
	/**
	 * 隐藏bubble
	 * 
	 */
	public void SFdoHideBubble() {
      isShow=false;
      invalidate();
	}

	/**
	 * 显示buuble
	 * 将泡泡显示出来
	 * @param 泡泡上面显示的数据条数
	 */
	public void SFdoShowBubble(String msg) {
		this.count_str=msg;
       isShow=true;      
       invalidate();
	}
	

	/**
	 * 
	 * @param text
	 */
	public void SFsetBubbleTextSize(String text,Canvas canvas) {

		Paint paint = new TextPaint();
		paint.setTextSize(AndroidUtil.dip2px(getContext(), 9));
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);  
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text , circle_x , circle_y+circle_r/4 , paint);
	}

	@SuppressLint("ResourceAsColor")
	void SFdoDrawWhiteCircle(Canvas canvas) {
		Paint p = new Paint();
		p.setColor(Color.WHITE);
		p.setAntiAlias(true);  
		canvas.drawCircle(circle_x, circle_y, circle_r, p);
	}

	void SFdoDrawRedCircle(Canvas canvas) {
		Paint p = new Paint();
		p.setAntiAlias(true);  
		p.setColor(Color.parseColor("#CC0000"));//   0xff6da7  Color.parseColor("0xff6da7")
		//p.setARGB(1, 0x00, 0x6d, 0x07);
		canvas.drawCircle(circle_x, circle_y, circle_r-3,p);
	}
	
	/**
	 * 重新初始化按钮
	 */
	void SFdoResetButtonStyle(Canvas canvas)
	{
		Paint p = new Paint();
		p.setColor(Color.TRANSPARENT);
		p.setAntiAlias(true);  
		canvas.drawCircle(circle_x, circle_y, circle_r-2,p);
	}
}
