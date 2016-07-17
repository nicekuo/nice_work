package com.core.nice_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.core.R;


/**
 * Created by a2014-608 on 15-1-9
 * Updated by J!nl!n on 2015-12-05 新增暴露设置比例的方法
 */
public class FixValueFrameLayoutForScaleHeight extends FrameLayout {
    private float ratio = 1f;

    public FixValueFrameLayoutForScaleHeight(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs);
    }

    public FixValueFrameLayoutForScaleHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public FixValueFrameLayoutForScaleHeight(Context context) {
        super(context);
        init(context, null);
    }


    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FixValueFrameLayoutForScaleHeight);
            ratio = array.getFloat(R.styleable.FixValueFrameLayoutForScaleHeight_ratio, 1.0f);
            array.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(widthSize, (int) (widthSize * ratio));
        measureChildren(MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY), MeasureSpec.makeMeasureSpec((int) (widthSize * ratio), MeasureSpec.EXACTLY));
    }

    /**
     * 设置宽高比
     * @param ratio
     */
    public void setRatio(float ratio) {
        this.ratio = ratio;
    }
}
