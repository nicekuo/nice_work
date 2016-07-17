package com.nice.work.push;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;

import com.core.util.NiceLogUtil;
import com.nice.work.background.CommonPreference;
import com.nice.work.background.ConfigValue;
import com.tencent.android.tpush.XGPushBaseReceiver;
import com.tencent.android.tpush.XGPushClickedResult;
import com.tencent.android.tpush.XGPushRegisterResult;
import com.tencent.android.tpush.XGPushShowedResult;
import com.tencent.android.tpush.XGPushTextMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by sreay on 14-9-18.
 */
public class XGPushReceiver extends XGPushBaseReceiver {

    @Override
    public void onRegisterResult(Context context, int errorCode, XGPushRegisterResult message) {
        if (context == null || message == null) {
            return;
        }
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
            String token = message.getToken();
            NiceLogUtil.d("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onRegisterResult  xinge token--:" + token);
            if (!TextUtils.isEmpty(token)) {
                CommonPreference.saveToken(token);
                Intent intent = new Intent(ConfigValue.kPushTokenUpdate);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        } else {
        }
    }

    @Override
    public void onUnregisterResult(Context context, int errorCode) {
        if (context == null) {
            return;
        }
        if (errorCode == XGPushBaseReceiver.SUCCESS) {
        } else {
        }
    }

    @Override
    public void onSetTagResult(Context context, int errorCode, String tagName) {
    }

    @Override
    public void onDeleteTagResult(Context context, int errorCode, String tagName) {
    }

    // 通知展示
    @Override
    public void onNotifactionShowedResult(Context context, XGPushShowedResult notifiShowedRlt) {
        if (context == null || notifiShowedRlt == null) {
            return;
        }
        XGNotification notific = new XGNotification();
        notific.setMsg_id(notifiShowedRlt.getMsgId());
        notific.setTitle(notifiShowedRlt.getTitle());
        notific.setContent(notifiShowedRlt.getContent());
        notific.setNotificationActionType(notifiShowedRlt.getNotificationActionType());
        notific.setActivity(notifiShowedRlt.getActivity());
        notific.setUpdate_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()));
//        TableNotification.save(notific);
    }

    // 通知点击回调 actionType=1为该消息被清除，actionType=0为该消息被点击
    @Override
    public void onNotifactionClickedResult(Context context, XGPushClickedResult message) {
        NiceLogUtil.D("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onNotifactionClickedResult  = " + message.getActionType());
    }

    public static int NOFIFY_ID_START = 100000;
    public static int NOFIFY_ID_GROUP_CHAT_START = 1000000;

    // 消息透传
    @Override
    public void onTextMessage(Context context, XGPushTextMessage message) {


        NiceLogUtil.D("~~~~~~~``````````````()()()()()()()()()()()()()()``````````````````````````~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~onTextMessage  = " + message.getCustomContent());


        try {
            String title = message.getTitle();
            String content = message.getContent();
            JSONObject info = new JSONObject(message.getCustomContent());
            int type = info.optInt("mtype");
            String value = info.optString("value");

            NotificationUtils.showNotifiComments(context, title, content, type, value, NOFIFY_ID_START);

            NOFIFY_ID_START++;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
