package com.core.nice_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Dacer on 2/17/14.
 * <p/>
 * Updated by J!nl!n on 2015-12-05 修复上下padding由xml定义并且高度坐标考虑paddingTop.其实宽度算的不对,但是我不想改了,因为影响不大,可能写的时候已经考略了
 */
public class CirPageIndicator extends View implements ViewPager.OnPageChangeListener {

    //    private ViewPager mViewpager;
    private int mCount = 0;
    private int viewWidth = 0;
    private int actualWidth = 0;
    private int selectedPos = 0;
    private Paint selectedPaint;
    private Paint unselectedPaint;
    private int dotMargin = dip2px(getContext(), 5);// Dot dotMargin Dot dotMargin Dot
    private boolean isAutoScroll = false;

    private int radius = dip2px(getContext(), 3);
    //	private int paddingTop = dip2px(getContext(), 2);
//	private int paddingBottom = dip2px(getContext(), 2);
    private int paddingTop = getPaddingTop();
    private int paddingBottom = getPaddingBottom();
    public final static int UNSELECTED_COLOR = Color.parseColor("#CACACA");
    public final static int SELECTED_COLOR = Color.parseColor("#ff6666");
    private ViewPagerChangCallback changeViewCallback;


    public CirPageIndicator(Context context) {
        this(context, null);
    }

    public CirPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        selectedPaint = new Paint();
        selectedPaint.setAntiAlias(true);
        selectedPaint.setColor(SELECTED_COLOR);
        selectedPaint.setStyle(Paint.Style.FILL);
        unselectedPaint = new Paint(selectedPaint);
        unselectedPaint.setColor(UNSELECTED_COLOR);
    }

    public void setViewPager(ViewPager view) {
        view.setOnPageChangeListener(this);
        mCount = view.getAdapter().getCount();
        actualWidth = dotMargin * (mCount - 1) + radius * 2 * mCount;
        postInvalidate();
    }

    public void setChangeViewCallback(ViewPagerChangCallback callback) {
        changeViewCallback = callback;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDots(canvas);
    }

    public void setAutoScroll(boolean isAutoScroll) {
        this.isAutoScroll = isAutoScroll;
    }

    private void drawDots(Canvas canvas) {
        int x = (viewWidth - actualWidth) / 2 + radius;
        int y = paddingTop + radius + 27;
        for (int i = 0; i < mCount; i++) {
            boolean selected = selectedPos == i;
            canvas.drawCircle(x, y, radius, selected ? selectedPaint : unselectedPaint);
            x += (radius * 2 + dotMargin);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        viewWidth = measureWidth(widthMeasureSpec);
        int mViewHeight = measureHeight(heightMeasureSpec);
        setMeasuredDimension(viewWidth, mViewHeight);
    }

    private int measureWidth(int measureSpec) {
        int preferred = actualWidth;
        //        if (preferred<0) preferred = 0;
        return getMeasurement(measureSpec, preferred);
    }

    private int measureHeight(int measureSpec) {
        int preferred = radius * 2 + paddingBottom + paddingTop;
        return getMeasurement(measureSpec, preferred);
    }

    private int getMeasurement(int measureSpec, int preferred) {
        int specSize = MeasureSpec.getSize(measureSpec);
        int measurement;

        switch (MeasureSpec.getMode(measureSpec)) {
            case MeasureSpec.EXACTLY:
                measurement = specSize;
                break;
            case MeasureSpec.AT_MOST:
                measurement = Math.min(preferred, specSize);
                break;
            default:
                measurement = preferred;
                break;
        }
        return measurement;
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        selectedPos = i;
        postInvalidate();
        if (changeViewCallback != null) {
            changeViewCallback.onPageSelected(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
