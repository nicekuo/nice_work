package com.naneng.jiche.ui.setting;

import android.view.View;
import android.widget.TextView;

import com.naneng.jiche.R;
import com.naneng.jiche.background.AppInfo;
import com.naneng.jiche.core.AbstractActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by nice on 16/4/22.
 */


@EActivity(R.layout.activity_about_jiche)
public class ActivityAboutJICHe extends AbstractActivity {

    @ViewById(R.id.version)
    TextView version;

    @ViewById(R.id.desc1)
    TextView desc1;

    @ViewById(R.id.desc2)
    TextView desc2;

    @ViewById(R.id.appinfo)
    TextView appinfo;

    @ViewById(R.id.rootView)
    View rootView;

    int clickCount = 0;


    @AfterViews
    void initView() {
        setTitleName("关于我们");
        version.setText("v" + AppInfo.cver_name);

//        desc2.setText("新人注册送大礼，专业养车，千家门店，到店保养，安全省心");
        desc2.setVisibility(View.GONE);
        appinfo.setText(AppInfo.getAppInfoString());
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickCount<6){
                    clickCount++;
                }else {
                    appinfo.setVisibility(View.VISIBLE);
                    clickCount = 0;
                }
            }
        });
    }

    @Override
    protected void onClickBack() {
        finish();
    }
}
