package com.core.wigets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.core.R;
import com.core.cart.ViewCartMenuItem;
import com.core.util.AndroidUtil;

public class TitleView extends FrameLayout {

    public ImageView mLeftBtn;
    public TextView mRightBtn;
    public ImageView title_right_image;
    public TextView mTitle;

    private OnClickListener mOnLeftButtonClickListener;
    private OnClickListener mOnRightButtonClickListener;
    private OnClickListener mOnRightTwoButtonClickListener;

    private OnClickListener mOnCartMenuClickListener;

    public IBackListener iBackListener;
    private IHomeListener iHomeListener;
    private Resources res;

    private ViewCartMenuItem cartMenuItem;
    private ImageView title_right_image_two;

    public TitleView(Context context) {
        this(context, null);
        res = context.getResources();
    }

    public TitleView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        res = context.getResources();
    }

    public TitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.template_title, this, true);
        mLeftBtn = (ImageView) view.findViewById(R.id.title_left_btn);
        title_right_image = (ImageView) findViewById(R.id.title_right_image);
        cartMenuItem = (ViewCartMenuItem) findViewById(R.id.cart);
        cartMenuItem.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnCartMenuClickListener!=null){
                    mOnCartMenuClickListener.onClick(view);
                }
            }
        });
        title_right_image.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRightButtonClickListener != null) {
                    mOnRightButtonClickListener.onClick(v);
                }
            }
        });
        mLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (iBackListener != null) {
                    iBackListener.backListener();
                } else if (mOnLeftButtonClickListener != null) {
                    mOnLeftButtonClickListener.onClick(v);
                }
            }
        });
        mRightBtn = (TextView) view.findViewById(R.id.title_right_text);
        mRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnRightButtonClickListener != null) {
                    mOnRightButtonClickListener.onClick(v);
                }
            }
        });
        mTitle = (TextView) view.findViewById(R.id.title_text);
        title_right_image_two = (ImageView) view.findViewById(R.id.title_right_image_two);
        title_right_image_two.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnRightTwoButtonClickListener != null) {
                    mOnRightTwoButtonClickListener.onClick(view);
                }
            }
        });
    }

    public void setTitleName(String text) {
        mTitle.setVisibility(View.VISIBLE);
        mTitle.setText(text);
    }

    public void setTitleName(int stringID) {
        setTitleName(res.getString(stringID));
    }

    public void setTitleLeftImageButton(int imgID, OnClickListener listener) {
        mLeftBtn.setBackgroundResource(imgID);
        mLeftBtn.setVisibility(View.VISIBLE);
        mOnLeftButtonClickListener = listener;
    }

    public void setTitleLeftButtonListener(OnClickListener listener) {
        mOnLeftButtonClickListener = listener;
    }

    public void showCartItem(OnClickListener listener) {
        mOnCartMenuClickListener = listener;
        cartMenuItem.setVisibility(View.VISIBLE);
    }

    public void updateCartItem(int count) {
        cartMenuItem.setVisibility(View.VISIBLE);
        cartMenuItem.updateCount(count);
    }

    /**
     * 设置右按钮文本和事件
     */
    public void setTitleRightButtonText(String text, OnClickListener listener) {
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setText(text);
        mRightBtn.setOnClickListener(listener);
        mRightBtn.setCompoundDrawables(null, null, null, null);
    }


    /**
     * 设置右按钮文本和事件
     */
    public void setTitleRightButtonTextAndErrow(String text, OnClickListener listener) {
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setText(text);
        mRightBtn.setOnClickListener(listener);
        mRightBtn.setMinWidth(AndroidUtil.dip2px(getContext(), 65));
        Drawable drawable = getResources().getDrawable(R.drawable.errow_drop_down_white);
        drawable.setBounds(0, 0, 28, 15);
        mRightBtn.setCompoundDrawables(null,null,null,drawable);
    }

    public void updateRightButtonText(String text){
        mRightBtn.setText(text);
    }


    /**
     * 设置右按钮背景和事件
     */
    public void setTitleRightImageButton(int imageResId, OnClickListener listener) {
        title_right_image.setBackgroundResource(imageResId);
        title_right_image.setVisibility(View.VISIBLE);
        mOnRightButtonClickListener = listener;
    }

    /**
     * 设置右按钮背景和事件
     */
    public void setTitleRightImageButtonTwo(int imageResId, OnClickListener listener) {
        title_right_image_two.setBackgroundResource(imageResId);
        title_right_image_two.setVisibility(View.VISIBLE);
        mOnRightTwoButtonClickListener = listener;
    }


    public void hiddenTitleLeftButton() {
        mLeftBtn.setVisibility(View.INVISIBLE);
    }

    public void hiddenTitleRightButton() {
        mRightBtn.setVisibility(View.INVISIBLE);
    }

    public void showTitleRightButton() {
        mRightBtn.setVisibility(View.VISIBLE);
    }


    public interface IBackListener {
        void backListener();
    }

    public interface IHomeListener {
        void goHomeListener();
    }
}
