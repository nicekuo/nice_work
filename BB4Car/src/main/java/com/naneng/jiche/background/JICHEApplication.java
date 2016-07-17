package com.naneng.jiche.background;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.baidu.location.LocationClientOption;
import com.core.CoreApplication;
import com.core.util.IntentUtil;
import com.core.util.ToastUtil;
import com.core.util.constants.CoreConstant;
import com.naneng.jiche.BuildConfig;
import com.naneng.jiche.R;
import com.naneng.jiche.background.account.Account;
import com.naneng.jiche.background.db.DBHelper;
import com.naneng.jiche.background.services.LocationService;
import com.naneng.jiche.core.AbstractActivity;
import com.naneng.jiche.ui.account.LoginBean;
import com.naneng.jiche.ui.main.MainActivity_;
import com.naneng.jiche.ui.setting.LoginActivity_;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tendcloud.tenddata.TCAgent;
import com.zhy.http.okhttp.OkHttpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by sufun_job on 2016/1/19.
 */
public class JICHEApplication extends CoreApplication {
    private static JICHEApplication mApplication;
    public LocationService locationService;
    private SQLiteDatabase db;
    private Account account;

    private final String kTalkingdataAppID = "81B6D9CF811B6C215EB6DFEDD1CC190E";

    public final String kPageSize = "pageSize";
    public final String kPage = "pageNumber";

    public final String kWeChatAppID = "wx4973ddb02ce5a03a";
    private IWXAPI api;

    private boolean is_develop_flag = true;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        getAccount();
        /***
         * 初始化定位sdk，建议在Application中创建
         */
        initLocaltionService();

        AppInfo.retrieveAppInfo(this);

        initTalkingData();

        initWXAPI();

        is_develop_flag = BuildConfig.LOG_DEBUG;

        CoreConstant.IS_TEST_FLAG = is_develop_flag;

        initOkHttpClient();

    }


    public boolean isDevelop() {
        return is_develop_flag;
    }

    public void setIs_develop_flag(boolean is_develop_flag) {
        this.is_develop_flag = is_develop_flag;
        CoreConstant.IS_TEST_FLAG = true;
    }


    private void initWXAPI() {
        //微信api
        api = WXAPIFactory.createWXAPI(this, kWeChatAppID, false);
        api.registerApp(kWeChatAppID);
    }

    private void initTalkingData() {
    /*初始化talkingdata统计sdk,建议在Application中创建*/
        TCAgent.LOG_ON = true;
        // App ID: 在TalkingData创建应用后，进入数据报表页中，在“系统设置”-“编辑应用”页面里查看App ID。
        // 渠道 ID: 是渠道标识符，可通过不同渠道单独追踪数据。
        TCAgent.init(getApplicationContext(), kTalkingdataAppID, AppInfo.qudao_code);
        //正式打包后才开启上报错误
        if (!BuildConfig.LOG_DEBUG) {
            TCAgent.setReportUncaughtExceptions(true);
        }
    }

    private void initLocaltionService() {
        locationService = new LocationService(getApplicationContext());
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
        option.setCoorType("bd09ll");
        locationService.setLocationOption(option);
    }

    public static JICHEApplication getInstance() {
        return mApplication;
    }


    private void initOkHttpClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                        //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }


    public IWXAPI getWxApi() {
        if (api.isWXAppInstalled()) {
            boolean isPaySupported = api.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
            if (!isPaySupported) {
                ToastUtil.showToastMessage(getApplicationContext(), "目前您的微信版本过低或未安装微信，需要安装微信才能使用");
                return null;
            } else {
                return api;
            }
        } else {
            ToastUtil.showToastMessage(getApplicationContext(), "你还没有安装微信哦");
            return null;
        }
    }

    public SQLiteDatabase getDB() {
        if (db == null) {
            synchronized (DBHelper.class) {
                if (db == null) {
                    DBHelper dbHelper = new DBHelper(this);
                    db = dbHelper.getWritableDatabase();
                }
            }
        }
        return db;
    }

    public JSONObject strConvertToJson(String jsonStr) {
        if (jsonStr == null) {
            return null;
        }
        try {
            JSONObject object = new JSONObject(jsonStr);
            return object;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T> T strConvertToJson(String jsonStr, Class<T> clazz) {
        if (jsonStr == null) {
            return null;
        }
        try {
            T bean = JSON.parseObject(jsonStr, clazz);
            return bean;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Account getAccount() {
        if (account == null) {
            synchronized (Account.class) {
                if (account == null) {
                    account = new Account();
                }
            }
        }
        return account;
    }

    public void saveAccount(LoginBean loginBean) {
        getAccount();
        account.saveAccountInfo(loginBean);
    }

    public void updateAvatar(String avatar) {
        getAccount();
        account.uodateAvatar(avatar);
    }

    public void updateInfo(String key, String value) {
        getAccount();
        account.updateInfo(key, value);
    }

    public void clearToken() {
        account.clear();
    }

    public boolean getLoginState() {
        return !TextUtils.isEmpty(getAccount().token);
    }

    public void gotoLogin(AbstractActivity abstractActivity) {
        LoginActivity_.intent(abstractActivity).start();
    }

    public void gotoLoginForResult(AbstractActivity abstractActivity, int requestcode) {
        IntentUtil.intentFowardResult(abstractActivity, LoginActivity_.intent(abstractActivity).get(), requestcode);
    }

    public void gotoHome(Activity activity) {
        Intent intent = new Intent(activity, MainActivity_.class);
        intent.putExtra(ConfigValue.kIntentType, ConfigValue.kIntentTypeInApp);
        intent.putExtra(ConfigValue.kLaunchMainAcKey, ConfigValue.kLaunchMainAcTypeShowHome);
        activity.startActivity(intent);
    }

    public void showJsonErrorToast() {
        ToastUtil.showToastMessage(getApplicationContext(), getResources().getString(R.string.json_error));
    }

}
