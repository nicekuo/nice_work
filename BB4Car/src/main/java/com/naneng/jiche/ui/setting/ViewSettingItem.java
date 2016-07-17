package com.naneng.jiche.ui.setting;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.core.util.IntentUtil;
import com.naneng.jiche.R;
import com.naneng.jiche.core.AbstractActivity;

/**
 * Created by nice on 16/4/12.
 */
public class ViewSettingItem extends LinearLayout {

    private ImageView idIcon;
    private TextView idTvTitle;
    private ImageView idIconRight;
    private TextView idTvTip;
    private MineBean.DataBean.MinePromptsBean settingModel;

    public ViewSettingItem(Context context) {
        super(context);
        initView(context);
    }

    public ViewSettingItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        final AbstractActivity activity = (AbstractActivity) context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.setting_me_item, this, true);
        idIcon = (ImageView) findViewById(R.id.id_icon);
        idTvTitle = (TextView) findViewById(R.id.id_tv_title);
        idIconRight = (ImageView) findViewById(R.id.id_icon_right);
        idTvTip = (TextView) findViewById(R.id.id_tv_tip);
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (settingModel == null || TextUtils.isEmpty(settingModel.getClassify())) {
                    return;
                }
//                switch (settingModel.getClassify()) {
//                    case "0"://我的订单
//                        IntentUtil.intentForward(activity, ActivityOrderList_.intent(activity).get());
//                        break;
//                    case "1"://我的购物车
//                        IntentUtil.intentForward(activity, ActivityShoppingCar_.intent(activity).get());
//                        break;
//                    case "2"://我的优惠券
//                        IntentUtil.intentForward(activity, MyCouponActivity_.intent(activity).fromType(MyCouponActivity.kMyCouponList).get());
//                        break;
//                    case "3"://我的爱车
//                        IntentUtil.intentForward(activity, MyCarActicity_.intent(activity).get());
//                        break;
//                    case "4"://我的收藏
//                        IntentUtil.intentForward(activity, MyCollectActivity_.intent(activity).get());
//                        break;
//                    case "5"://我的消息
//                        IntentUtil.intentForward(activity, MyMessageActivity_.intent(activity).get());
//                        break;
//                }
            }
        });
    }

    public void setData(MineBean.DataBean.MinePromptsBean model) {
        if (model == null) {
            return;
        }
        this.settingModel = model;
        idIcon.setBackgroundResource(model.getIcon_id());
        idTvTitle.setText(model.getName());
        if (!TextUtils.isEmpty(model.getPrompt())) {
            idTvTip.setText(model.getPrompt());
        } else {
            idTvTip.setText("");
        }
    }
}
