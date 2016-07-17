package com.core.nice_view;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.core.activity.AbstractCoreActivity;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class PopupWindowUtil {


    public static PopupWindow getMacthParentPopupWindow(View containerView, final AbstractCoreActivity activity) {
        final PopupWindow mPopupWindow = new PopupWindow(containerView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable(activity.getResources(), (Bitmap) null));
        mPopupWindow.getContentView().setFocusableInTouchMode(true);
        mPopupWindow.getContentView().setFocusable(true);
        mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU
                        && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow.dismiss();
                    }
                    return true;
                }
                return false;
            }
        });
        final WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = 0.4f; // 0.0-1.0
        activity.getWindow().setAttributes(lp);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                lp.alpha = 1.0f;
                activity.getWindow().setAttributes(lp);
            }
        });
        return mPopupWindow;
    }


}
