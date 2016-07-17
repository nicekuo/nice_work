package com.nice.work.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nice.work.R;

/**
 * Created by nice on 16/4/11.
 */
public class ErrorViewForReload extends TextView {


    private OnClickForReloadListener listener;

    public interface OnClickForReloadListener{
        void onClickReload();
    }


    public ErrorViewForReload(Context context) {
        super(context);
        initView(context);
    }

    public ErrorViewForReload(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    public void register(OnClickForReloadListener listener){
        this.listener = listener;
    }

    private void initView(Context context){
        setBackgroundColor(getResources().getColor(R.color.white));
        setGravity(Gravity.CENTER);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.txt_size_item_title));
        setText("加载失败,点击重新加载");
        setTextColor(getResources().getColor(R.color.black));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onClickReload();
                }
            }
        });
    }
}
