package com.naneng.jiche.ui.setting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.core.adapter.SFBaseAdapter;

import java.util.List;

/**
 * Created by sufun_job on 2016/2/16.
 */
public class MeSettingAdapter extends SFBaseAdapter<MineBean.DataBean.MinePromptsBean> {

    public MeSettingAdapter(List<MineBean.DataBean.MinePromptsBean> data, Context context, Object object, Object exObj) {
        super(data, context, object, exObj);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewSettingItem settingItem = null;
        if (convertView == null) {
            settingItem = new ViewSettingItem(mContext);
        } else {
            settingItem = (ViewSettingItem) convertView;
        }
        settingItem.setData((MineBean.DataBean.MinePromptsBean) getItem(position));
        return settingItem;
    }
}
