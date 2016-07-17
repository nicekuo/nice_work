package com.nice.work.ui.setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.core.bean.BaseBean;
import com.core.util.IntentUtil;
import com.core.util.StringUtil;
import com.core.util.ToastUtil;
import com.nice.work.R;
import com.nice.work.background.ConfigValue;
import com.nice.work.background.JICHEApplication;
import com.nice.work.background.RequestAPI;
import com.nice.work.core.AbstractActivity;
import com.nice.work.ui.account.LoginBean;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.openapi.IWXAPI;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by BaoLongxiang on 2016/2/18.
 * 登陆
 */
@EActivity(R.layout.setting_login_layout)
public class LoginActivity extends AbstractActivity {

    @ViewById(R.id.id_ev_phone)
    EditText idEvPhone;
    @ViewById(R.id.id_btn_code)
    TextView idBtnCode;
    @ViewById(R.id.id_ev_code)
    EditText idEvCode;
    @ViewById(R.id.id_btn_login)
    TextView idBtnLogin;
    @ViewById(R.id.id_txv_login_type_switch)
    TextView idTxvLoginTypeSwitch;
    @ViewById(R.id.checkbox)
    CheckBox checkBox;
    @ViewById(R.id.text)
    TextView text;
    @ViewById(R.id.weichat_login)
    View weichat_login;

    private CountDownTimer mCountDownTimer;
    private final int kLoginNormalRequstCode = 1043;
    public static final String kLoginBean = "loginbean";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCountDownTimer != null) {
            mCountDownTimer.cancel();
            mCountDownTimer = null;
        }
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @AfterViews
    void initView() {

        titleView.mTitle.setText("登录");
        text.setText(StringUtil.getHtmlStr("我已阅读并接受", "《协议事宜》"));
        registerReceiver();
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = RequestAPI.OFFICIAL_BASE_URL + "registrationAgreement/index";
//                ActivityWebView_.intent(LoginActivity.this).url(url).start();
            }
        });
        checkBox.setChecked(true);
        mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                String string = String.format(getString(R.string.authcode_shengyu), millisUntilFinished / 1000);
                idBtnCode.setText(string);
            }

            @Override
            public void onFinish() {
                countDownFinish();
            }
        };

        weichat_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginWeChat();
            }
        });


    }

    public void loginWeChat() {
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo_test";
        IWXAPI iwxapi = JICHEApplication.getInstance().getWxApi();
        if (iwxapi != null) {
            iwxapi.sendReq(req);
        }
    }


    private void countDownFinish() {
        idBtnCode.setEnabled(true);
        idBtnCode.setText(getString(R.string.get_authcode));
    }

    private void setUnable() {
        idBtnCode.setEnabled(false);
        if (mCountDownTimer != null) {
            mCountDownTimer.start();
        }
    }


    @Click({R.id.id_btn_login, R.id.id_txv_login_type_switch, R.id.id_btn_code})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_btn_login://登陆按钮
                if (checkBox.isChecked()) {
                    String phoneStr = idEvPhone.getText().toString();
                    String code = idEvCode.getText().toString();
                    if (TextUtils.isEmpty(phoneStr)) {
                        ToastUtil.showToastMessage(LoginActivity.this, "没有手机号怎么登录呢?");
                        return;
                    }
                    if (TextUtils.isEmpty(code)) {
                        ToastUtil.showToastMessage(LoginActivity.this, "没有验证码怎么登录呢?");
                        return;
                    }
                    checkCodeByHttp(phoneStr, code);
                } else {
                    ToastUtil.showToastMessage(LoginActivity.this, "您还没有同意协议事宜哦!");
                }
                break;
            case R.id.id_txv_login_type_switch://切换登陆状态
                IntentUtil.intentFowardResult(LoginActivity.this, ActivityPwdLogin_.intent(this).get(), kLoginNormalRequstCode);
                break;
            case R.id.id_btn_code://获取验证码按钮
                String phoneStr = idEvPhone.getText().toString();
                if (TextUtils.isEmpty(idEvPhone.getText().toString())) {
                    ToastUtil.showToastMessage(LoginActivity.this, "没有手机号码,怎么给您发验证码呢?");
                    return;
                }
                getCodeByHttp(phoneStr);
                setUnable();
                break;
        }
    }


    private void doLogin() {

        Map<String, String> params = new HashMap<>();
        params.put("mobile", idEvPhone.getText().toString());

        new NiceAsyncTask(false) {

            @Override
            public void loadSuccess(BaseBean bean) {
                LoginBean loginBean = (LoginBean) bean;
                if (loginBean != null) {
                    if (loginBean != null && loginBean.getData() != null && !TextUtils.isEmpty(loginBean.getData().getToken())) {
                        doLoginSucess(loginBean);
                    } else {
                        JICHEApplication.getInstance().showJsonErrorToast();
                    }
                }
            }

            @Override
            public void exception() {

            }
        }.post(true, RequestAPI.API_MEMBER_LOGIN_MOBILE, params, LoginBean.class);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == kLoginNormalRequstCode) {
            if (resultCode == RESULT_OK) {
                LoginBean loginBean = (LoginBean) data.getSerializableExtra(kLoginBean);
                doLoginSucess(loginBean);
            }
        }
    }

    private void doLoginSucess(LoginBean loginBean) {
        JICHEApplication.getInstance().saveAccount(loginBean);
        ToastUtil.showToastMessage(getApplicationContext(), "登录成功");
        Intent intent = new Intent(ConfigValue.ACTION_LOGOIN_STATUS_CHANGED);
        intent.putExtra(ConfigValue.ACTION_DATA_KEY, ConfigValue.ACTION_DATA_VALUE_IN);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        Intent intent1 = new Intent(ConfigValue.kMeFragmentLogin);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent1);


        Intent intent2 = new Intent(ConfigValue.kPushTokenUpdate);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);

        setResult(RESULT_OK);
        finish();
    }

    @Override
    protected void onClickBack() {
        finish();
    }


    private void getCodeByHttp(String phone) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        new NiceAsyncTask(false) {

            @Override
            public void loadSuccess(BaseBean bean) {

            }

            @Override
            public void exception() {

            }
        }.post(true, RequestAPI.SMS_REQUEST, params, BaseBean.class);
    }


    private void checkCodeByHttp(String phone, String code) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("code", code);
        new NiceAsyncTask(false) {

            @Override
            public void loadSuccess(BaseBean bean) {
                doLogin();
            }

            @Override
            public void exception() {

            }
        }.post(true, RequestAPI.SMS_VALIDATE, params, BaseBean.class);
    }

    private void loginByWechat(String code) {
        Map<String, String> params = new HashMap<>();
        params.put("code", code);
        new NiceAsyncTask(false) {

            @Override
            public void loadSuccess(BaseBean bean) {
                LoginBean loginBean = (LoginBean) bean;
                doLoginSucess(loginBean);
            }

            @Override
            public void exception() {

            }
        }.post(true, RequestAPI.API_MEMBER_LOGIN_WECHAT, params, LoginBean.class);
    }


    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (ConfigValue.ActionWeChatLogin.equals(action)) {
                String code = intent.getStringExtra(ConfigValue.kWeChatLoginKey);
                loginByWechat(code);

            }
        }
    };


    private void registerReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConfigValue.ActionWeChatLogin);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, intentFilter);
    }


}
