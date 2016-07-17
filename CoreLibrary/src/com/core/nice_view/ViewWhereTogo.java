package com.core.nice_view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.core.R;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class ViewWhereTogo extends LinearLayout {


    private TextView title;
    private TextView cancle;
    private TextView ok;

    private OnClickWhereToGoListener listener;

    public interface OnClickWhereToGoListener {
        public void onClickCancle();

        public void onClickOk();
    }

    public ViewWhereTogo(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ViewWhereTogo(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_where_togo, this, true);
        title = (TextView) findViewById(R.id.title);
        cancle = (TextView) findViewById(R.id.cancle);
        ok = (TextView) findViewById(R.id.ok);
        cancle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickCancle();
                }
            }
        });
        ok.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickOk();
                }
            }
        });
    }

    public void setData(String title, String cancle, String ok) {
        this.title.setText(title);
        this.cancle.setText(cancle);
        this.ok.setText(ok);
    }

    public void register(OnClickWhereToGoListener listener) {
        this.listener = listener;
    }


}
