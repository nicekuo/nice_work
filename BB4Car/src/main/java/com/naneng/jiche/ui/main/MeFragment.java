package com.naneng.jiche.ui.main;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.core.bean.BaseBean;
import com.core.util.IntentUtil;
import com.core.widget.image.SFImageView;
import com.naneng.jiche.R;
import com.naneng.jiche.background.ConfigValue;
import com.naneng.jiche.background.JICHEApplication;
import com.naneng.jiche.background.RequestAPI;
import com.naneng.jiche.background.account.Account;
import com.naneng.jiche.background.db.TableCart;
import com.naneng.jiche.core.AbstractActivity;
import com.naneng.jiche.core.AbstractFragment;
import com.naneng.jiche.ui.setting.ActivityAboutJICHe_;
import com.naneng.jiche.ui.setting.ActivityPersonalCenter_;
import com.naneng.jiche.ui.setting.MeSettingAdapter;
import com.naneng.jiche.ui.setting.MineBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sufun_job on 2016/2/16.
 *
 * @description 用户个人主页`
 */
@EFragment(R.layout.setting_me_layout)
public class MeFragment extends AbstractFragment {


}
