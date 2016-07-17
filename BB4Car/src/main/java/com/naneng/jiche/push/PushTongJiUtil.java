package com.naneng.jiche.push;

import android.content.Intent;
import android.text.TextUtils;

import com.core.util.NiceLogUtil;

/**
 * Created by sreay on 15-7-30.
 */
public class PushTongJiUtil {
    //收到push后的回调协议  1--收到  2--点击
    public static void receivePush(Intent intent) {
//        String pushId = intent.getStringExtra(NotificationUtils.keyPushId);
//        NiceLogUtil.d("receive pushID------:" + pushId);
//        if (TextUtils.isEmpty(pushId)) {
//            return;
//        }
        NiceLogUtil.d("收到push上报bi");
//        /**收到push上报bi**/
//        BIUtil.pushStatistics(BIUtil.ACTION_PUSH_SHOW, pushId);
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("push_id", pushId));
//        nameValuePairs.add(new BasicNameValuePair("type", "1"));
//        Request.doRequest(HiGo.getInstance(), nameValuePairs, ServerConfig.URL_PUSH_TONHJI, Request.POST, new Request.RequestListener() {
//            @Override
//            public void onException(Request.RequestException e) {
//            }
//
//            @Override
//            public void onComplete(String response) {
//            }
//        });
    }

    //收到push后的回调协议  1--收到  2--点击
    public static void clickPush(Intent intent) {
//        String pushId = intent.getStringExtra(NotificationUtils.keyPushId);
//        NiceLogUtil.d("click pushID------:" + pushId);
//        if (TextUtils.isEmpty(pushId)) {
//            return;
//        }
        /**push被点击,上报bi**/
        NiceLogUtil.D("push被点击,上报bi");
//        BIUtil.pushStatistics(BIUtil.ACTION_PUSH_CLICK, pushId);
//        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
//        nameValuePairs.add(new BasicNameValuePair("push_id", pushId));
//        nameValuePairs.add(new BasicNameValuePair("type", "2"));
//        Request.doRequest(HiGo.getInstance(), nameValuePairs, ServerConfig.URL_PUSH_TONHJI, Request.POST, new Request.RequestListener() {
//            @Override
//            public void onException(Request.RequestException e) {
//            }
//
//            @Override
//            public void onComplete(String response) {
//            }
//        });
    }
}
