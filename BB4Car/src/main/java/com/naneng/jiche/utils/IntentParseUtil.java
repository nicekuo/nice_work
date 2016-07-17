package com.naneng.jiche.utils;

import android.content.Intent;
import android.text.TextUtils;

import com.naneng.jiche.background.ConfigValue;
import com.naneng.jiche.background.JICHEApplication;
import com.naneng.jiche.background.JICHEShareModel;
import com.naneng.jiche.push.MessageNew;
import com.naneng.jiche.ui.main.MainActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class IntentParseUtil {

    private static IntentParseUtil instance;


    public static IntentParseUtil getInstance() {
        if (instance == null) {
            instance = new IntentParseUtil();
        }
        return instance;
    }

    public void parseIntentType(Intent intent, MainActivity mainActivity) {
        if (intent == null) {
            return;
        }

        String intentType = intent.getStringExtra(ConfigValue.kIntentType);
        if (!TextUtils.isEmpty(intentType)) {
            switch (intentType) {
                case ConfigValue.kIntentTypeInApp:
                    parseIntentInApp(intent, mainActivity);
                    break;
                case ConfigValue.kIntentTypeScheme:

                    break;
                case ConfigValue.kIntentTypePush:
                    parseIntentPush(intent, mainActivity);
                    break;
            }
        }
    }

    private void parseIntentInApp(Intent intent, MainActivity mainActivity) {
        if (intent == null) {
            return;
        }
        mainActivity.parseIntentInMainActivity(intent);
    }

    private void parseIntentPush(Intent intent, MainActivity mainActivity) {
        if (intent == null) {
            return;
        }

        int type = intent.getIntExtra(ConfigValue.kPushType, 0);
        String value = intent.getStringExtra(ConfigValue.kPushValue);
        if (TextUtils.isEmpty(value)) {
            return;
        }
        switch (type) {
            case MessageNew.PUSH_TYPE_APP:
                break;
            case MessageNew.PUSH_TYPE_WEBVIEW:
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (jsonObject == null) {
                    return;
                }
                String url = jsonObject.optString("url");
                String share = jsonObject.optString("share");
                JICHEShareModel shareModel = null;
                if (!TextUtils.isEmpty(share)) {
                    shareModel = JICHEApplication.getInstance().strConvertToJson(share, JICHEShareModel.class);
                }
                if (TextUtils.isEmpty(url)) {
                    return;
                }
//                if (shareModel == null) {
//                    ActivityWebView_.intent(mainActivity).url(url).start();
//                } else {
//                    ActivityWebView_.intent(mainActivity).url(url).share(shareModel).start();
//                }
                break;

//            case MessageNew.PUSH_TYPE_PRODUCTINFO:
//                ActivityProductInfo_.intent(mainActivity).good_id(value).start();
//                break;
//
//            case MessageNew.PUSH_TYPE_ORDERINFO:
//                ActivityOrderInfo_.intent(mainActivity).order_id(value).start();
//                break;
        }

    }

}
