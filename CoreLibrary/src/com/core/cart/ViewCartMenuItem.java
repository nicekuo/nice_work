package com.core.cart;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.R;
import com.core.util.StringUtil;


/**
 * Created by sreay on 15/8/19.
 */
public class ViewCartMenuItem extends FrameLayout {

    private TextView count;
    private ImageView cart_image;

    public ViewCartMenuItem(Context context) {
        super(context);
        initView(context, null);
    }

    public ViewCartMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.menu_cart, this, true);
        count = (TextView) findViewById(R.id.count);
        cart_image = (ImageView) findViewById(R.id.cart_image);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    public void updateCount(int count) {
        if (count == 0){
            this.count.setVisibility(View.GONE);
        }else {
            this.count.setVisibility(View.VISIBLE);
            this.count.setText(String.valueOf(count));
        }

    }
}
