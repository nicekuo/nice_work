package com.core.wigets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by sufun_job on 2015/11/14.
 * @author sufun_job
 * @description 不可滚动但可以使用于ListView中嵌套的listView  可以显示全的
 */
public class SFExpandGridView extends GridView{

    public SFExpandGridView(Context context) {
        super(context);
    }

    public SFExpandGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SFExpandGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /**
     * 用于重新的计算其本身的高度
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
