package com.naneng.jiche.ui.webview;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.PopupWindow;

import com.core.nice_view.PopupWindowUtil;
import com.core.nice_view.ViewWhereTogo;
import com.core.util.NiceLogUtil;
import com.core.util.ToastUtil;
import com.naneng.jiche.background.AppInfo;
import com.naneng.jiche.background.ConfigValue;
import com.naneng.jiche.background.JICHEApplication;
import com.naneng.jiche.core.AbstractActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class NiceJavaScriptInterface {

    AbstractActivity activity;
    WebView webView;
    int type;
    String value;
    String callback;
    String uniqKey;


    public NiceJavaScriptInterface(AbstractActivity activity, WebView webView) {
        this.activity = activity;
        this.webView = webView;
    }


    @JavascriptInterface
    public void jicheJSChannel(String message) {
        NiceLogUtil.D("JavascriptInterface   jicheJSChannel  ==============      " + message);

        if (TextUtils.isEmpty(message)) {
            return;
        }
        HtmlJSBean jsBean = JICHEApplication.getInstance().strConvertToJson(message, HtmlJSBean.class);
        type = jsBean.getType();
        value = jsBean.getValue();
        callback = jsBean.getCallBack();
        uniqKey = jsBean.getKey();

        switch (type) {
            case ConfigValue.SCHEME_TYPE_GET_USER_AND_APP_INFO:
                getUserAndAppInfo();
                break;
            case ConfigValue.SCHEME_TYPE_LOGIN_AND_GET_USERINFO:
                loginAndPostUserinfo();
                break;
            case ConfigValue.SCHEME_TYPE_SHOW_MESSAGE:
                showMessage(value);
                break;
            case ConfigValue.SCHEME_TYPE_GOTO_COUPON_LIST:
                showGotoCouponListDialog();
                updateMeFragmentTips();
                break;

        }
    }


    private void updateMeFragmentTips(){
        Intent intent = new Intent(ConfigValue.kMeFragmentTips);
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
    }


    private void showGotoCouponListDialog() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ViewWhereTogo viewWhereTogo = new ViewWhereTogo(activity);
                final PopupWindow window = PopupWindowUtil.getMacthParentPopupWindow(viewWhereTogo, activity);
                viewWhereTogo.register(new ViewWhereTogo.OnClickWhereToGoListener() {
                    @Override
                    public void onClickCancle() {
                        window.dismiss();
                    }

                    @Override
                    public void onClickOk() {
//                        MyCouponActivity_.intent(activity).start();
                        activity.finish();
                    }
                });
                viewWhereTogo.setData("恭喜领券成功,去查看优惠券?", "取消", "确定");
                window.showAtLocation(activity.rootView, Gravity.CENTER, 0, 0);
            }
        });

    }


    private void showMessage(String message) {
        ToastUtil.showToastMessage(activity, message);
    }

    private void getUserAndAppInfo() {
        if (!TextUtils.isEmpty(callback)) {
            reverseBackCommom(getAppInfo());
        }
    }

    private void reverseBackCommom(final String str) {
        webView.post(new Runnable() {
            @Override
            public void run() {
                webView.loadUrl("javascript:" + callback + "('" + uniqKey + "','" + str + "')");
                NiceLogUtil.D("JavascriptInterface   loadUrl  ==============      " + "javascript:" + callback + "('" + uniqKey + "','" + str + "')");
            }
        });

    }

    /*获取App信息*/
    private String getAppInfo() {
        JSONObject object = new JSONObject();
        JSONObject userobject = new JSONObject();
        JSONObject appinfoObject = new JSONObject();
        try {
            object.put("app_info", appinfoObject);

            appinfoObject.put("cver_name", AppInfo.cver_name);
            appinfoObject.put("cver_code", AppInfo.cver_code);
            appinfoObject.put("qudaoid", AppInfo.qudao_code);
            appinfoObject.put("ver", AppInfo.ver);
            appinfoObject.put("via", AppInfo.via);
            appinfoObject.put("app", AppInfo.APP_NAME);
            appinfoObject.put("uuid", AppInfo.uuid);
            appinfoObject.put("imei", AppInfo.imei);
            appinfoObject.put("mac", AppInfo.mac);

            if (JICHEApplication.getInstance().getLoginState()) {
                object.put("user_info", userobject);
                com.naneng.jiche.background.account.Account account = JICHEApplication.getInstance().getAccount();
                if (account != null) {
                    userobject.put("token", account.token);
                    userobject.put("avatar_url", account.avatar);
                    userobject.put("name", account.nickName);
                    userobject.put("member_id", account.member_id);
                    userobject.put("mobile", account.mobile);
                    userobject.put("realname", account.realname);
                    userobject.put("born", account.born);
                    userobject.put("userName", account.userName);
                }
            }


        } catch (JSONException e) {
            NiceLogUtil.e("js获取配置信息时抛异常  " + e.getMessage());
        }
        return object.toString();
    }

    /*登录并返回用户个人信息*/
    private void loginAndPostUserinfo() {
        JICHEApplication.getInstance().gotoLoginForResult(activity, ConfigValue.kHTML_JS_LOGIN_REQUEST_CODE);
    }

    /*登录成功后返回用户个人信息*/
    public void loginSucessAndPostUserInfo() {
        String userinfoJson = getAppInfo();
        if (!TextUtils.isEmpty(userinfoJson) && !TextUtils.isEmpty(callback)) {
            reverseBackCommom(userinfoJson);
        }
    }

}
