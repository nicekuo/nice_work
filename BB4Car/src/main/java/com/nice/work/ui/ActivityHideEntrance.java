package com.nice.work.ui;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.nice.work.R;
import com.nice.work.background.AppInfo;
import com.nice.work.background.ConfigValue;
import com.nice.work.background.JICHEApplication;
import com.nice.work.background.RequestAPI;
import com.nice.work.core.AbstractActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
@EActivity(R.layout.activity_hide_entrance)
public class ActivityHideEntrance extends AbstractActivity {

    @ViewById(R.id.appinfo)
    TextView appinfo;

    @ViewById(R.id.group)
    RadioGroup group;

    @ViewById(R.id.official)

    RadioButton official;

    @ViewById(R.id.develop)
    RadioButton develop;


    @AfterViews
    void init() {
        appinfo.setVisibility(View.VISIBLE);
        appinfo.setText(AppInfo.getAppInfoString());
        String baseUrl = RequestAPI.getAbsoluteUrl("");
        if (RequestAPI.OFFICIAL_BASE_URL.equals(baseUrl)) {
            official.setChecked(true);
        } else {
            develop.setChecked(true);
        }
        initRadioGroup();
    }

    private void initRadioGroup() {
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                boolean flag = i == official.getId() ? false : true;
                JICHEApplication.getInstance().setIs_develop_flag(flag);
                appinfo.setText(AppInfo.getAppInfoString());
                doQuite();
            }
        });
    }


    private void doQuite() {
        JICHEApplication.getInstance().clearToken();

        Intent intent = new Intent(ConfigValue.ACTION_LOGOIN_STATUS_CHANGED);
        intent.putExtra(ConfigValue.ACTION_DATA_KEY, ConfigValue.ACTION_DATA_VALUE_OUT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        Intent intent2 = new Intent(ConfigValue.kPushTokenUpdate);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);
    }

    @Override
    protected void onClickBack() {
        finish();
    }
}
