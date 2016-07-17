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
public class ViewInCenterTowChoice extends LinearLayout {


    TextView title;
    TextView one;
    TextView two;

    private OnViewInCenterTwoChoiceListener listener;

    public interface OnViewInCenterTwoChoiceListener {
        public void onClickOne();

        public void onClickTwo();
    }

    public void register(OnViewInCenterTwoChoiceListener listener) {
        this.listener = listener;
    }

    public ViewInCenterTowChoice(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ViewInCenterTowChoice(Context context) {
        super(context);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_center_two_choice, this, true);
        title = (TextView) findViewById(R.id.title);
        one = (TextView) findViewById(R.id.one);
        two = (TextView) findViewById(R.id.two);
        one.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickOne();
                }
            }
        });
        two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickTwo();
                }
            }
        });
    }

    public void setData(String title, String one, String two) {
        this.title.setText(title);
        this.one.setText(one);
        this.two.setText(two);
    }
}
