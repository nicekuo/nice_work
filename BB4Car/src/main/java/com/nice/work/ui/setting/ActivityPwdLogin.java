package com.nice.work.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.core.bean.BaseBean;
import com.core.util.encrypt.MD5Util;
import com.nice.work.R;
import com.nice.work.background.JICHEApplication;
import com.nice.work.background.RequestAPI;
import com.nice.work.core.AbstractActivity;
import com.nice.work.ui.account.LoginBean;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by admin on 2016/3/24.
 */
@EActivity(R.layout.activity_user_name_login)
public class ActivityPwdLogin extends AbstractActivity {

    @ViewById(R.id.id_et_user_name)
    EditText idUserName;
    @ViewById(R.id.id_et_pwd)
    EditText idPwd;
    @ViewById(R.id.id_btn_pwd_login)
    TextView idPwdLogin;

    @Click(R.id.id_btn_pwd_login)
    void onClick(View v) {
        login();

    }

    @AfterViews
    void initView() {
        titleView.mTitle.setText("登录");
    }

    private void login() {
        Map<String,String> params = new HashMap<>();
        params.put("username", idUserName.getText().toString());
        params.put("password", MD5Util.md5(idPwd.getText().toString()));
        new NiceAsyncTask(false) {

            @Override
            public void loadSuccess(BaseBean bean) {
                LoginBean loginBean = (LoginBean) bean;
                if (loginBean != null && loginBean.getData() != null && !TextUtils.isEmpty(loginBean.getData().getToken())) {
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(LoginActivity.kLoginBean, loginBean);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    JICHEApplication.getInstance().showJsonErrorToast();
                }

            }

            @Override
            public void exception() {


            }
        }.post(true, RequestAPI.API_MEMBER_LOGIN_NORMAL, params, LoginBean.class);

    }


    @Override
    protected void onClickBack() {
        finish();
    }
}
