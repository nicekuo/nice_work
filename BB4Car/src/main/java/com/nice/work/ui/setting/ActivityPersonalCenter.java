package com.nice.work.ui.setting;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.core.bean.BaseBean;
import com.core.nice_view.PopupWindowUtil;
import com.core.nice_view.ViewInCenterTowChoice;
import com.core.widget.image.SFImageView;
import com.nice.work.R;
import com.nice.work.background.ConfigValue;
import com.nice.work.background.JICHEApplication;
import com.nice.work.background.RequestAPI;
import com.nice.work.background.account.Account;
import com.nice.work.core.AbstractActivity;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */

@EActivity(R.layout.activity_personal_center)
public class ActivityPersonalCenter extends AbstractActivity {


    @ViewById(R.id.avatarView)
    SFImageView avatarView;

    @ViewById(R.id.up_nick_name)
    TextView up_nick_name;

    @ViewById(R.id.real_name)
    TextView real_name;

    @ViewById(R.id.nick_name)
    TextView nick_name;

    @ViewById(R.id.gender)
    TextView gender;

    @ViewById(R.id.birthday)
    TextView birthday;

    @ViewById(R.id.quit)
    TextView quit;

    @ViewById(R.id.rank_view)
    TextView rank_view;

    @ViewById(R.id.real_name_view)
    View real_name_view;
    @ViewById(R.id.nick_name_view)
    View nick_name_view;
    @ViewById(R.id.gender_name_view)
    View gender_name_view;
    @ViewById(R.id.born_name_view)
    View born_name_view;
    @ViewById(R.id.password)
    View password_view;


    private final int kModifyRealNameCode = 1239;
    private final int kModifyNickNameCOde = 1240;
    private final int kModifyBornCode = 1241;
    private final int kModifyGender = 1242;


    @AfterViews
    void init() {
        setTitleName("个人中心");
        updateUserInfo();
        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doQuite();
            }
        });

        avatarView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showGetPhotoWindow();
            }
        });
        setPhotoUrl(new ITakePhotoUrl() {
            @Override
            public void getUploadPhotoPath(final String uploadPhotoPath) {
                if (!TextUtils.isEmpty(uploadPhotoPath)) {
                    File file = new File(uploadPhotoPath);
                    if (file.exists()) {
//                        doUploadImage(uploadPhotoPath,file.getName());
                    }
                }
            }
        });


        real_name_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivtyModifyPersonalInfo_.intent(ActivityPersonalCenter.this).kModifyType(ActivtyModifyPersonalInfo.kRealName).beforeValue(real_name.getText().toString()).startForResult(kModifyRealNameCode);
            }
        });
        nick_name_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivtyModifyPersonalInfo_.intent(ActivityPersonalCenter.this).kModifyType(ActivtyModifyPersonalInfo.kNickName).beforeValue(nick_name.getText().toString()).startForResult(kModifyNickNameCOde);
            }
        });
        born_name_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivtyModifyPersonalInfo_.intent(ActivityPersonalCenter.this).kModifyType(ActivtyModifyPersonalInfo.kBorn).startForResult(kModifyBornCode);
            }
        });
        gender_name_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivtyModifyPersonalInfo_.intent(ActivityPersonalCenter.this).kModifyType(ActivtyModifyPersonalInfo.kGender).beforeValue(gender.getText().toString()).startForResult(kModifyGender);
            }
        });

        password_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ActivityModifyPassWord_.intent(ActivityPersonalCenter.this).start();
            }
        });
    }

    private void updateUserInfo() {
        Account account = JICHEApplication.getInstance().getAccount();
        avatarView.SFSetImageUrl(account.avatar);
        up_nick_name.setText(account.nickName);
        nick_name.setText(account.nickName);
        birthday.setText(account.born);
        gender.setText(account.gender);
        real_name.setText(account.realname);
        rank_view.setText(account.rankname);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            if (requestCode == kModifyNickNameCOde) {
                String value = data.getStringExtra(ActivtyModifyPersonalInfo.kModityKey);
                JICHEApplication.getInstance().updateInfo(Account.kNickName, value);
            }
            if (requestCode == kModifyRealNameCode) {
                String value = data.getStringExtra(ActivtyModifyPersonalInfo.kModityKey);
                JICHEApplication.getInstance().updateInfo(Account.kRealNme, value);
            }
            if (requestCode == kModifyBornCode) {
                String value = data.getStringExtra(ActivtyModifyPersonalInfo.kModityKey);
                JICHEApplication.getInstance().updateInfo(Account.kBorn, value);
            }
            if (requestCode == kModifyGender) {
                String value = data.getStringExtra(ActivtyModifyPersonalInfo.kModityKey);
                JICHEApplication.getInstance().updateInfo(Account.kGender, value);
            }
            updateUserInfo();
        }

    }

    private void doQuite() {

        quiteFromServer();


        JICHEApplication.getInstance().clearToken();
        setResult(RESULT_OK);

        Intent intent = new Intent(ConfigValue.ACTION_LOGOIN_STATUS_CHANGED);
        intent.putExtra(ConfigValue.ACTION_DATA_KEY, ConfigValue.ACTION_DATA_VALUE_OUT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);

        Intent intent2 = new Intent(ConfigValue.kPushTokenUpdate);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent2);

        finish();

    }

    private void quiteFromServer(){

        Map<String,String> params = new HashMap<>();

        new NiceAsyncTask(false){

            @Override
            public void loadSuccess(BaseBean bean) {

            }

            @Override
            public void exception() {

            }
        }.post(RequestAPI.API_MEMBER_LOGIN_OUT,params,BaseBean.class);
    }


    private void showGetPhotoWindow() {
        ViewInCenterTowChoice viewInCenterTowChoice = new ViewInCenterTowChoice(ActivityPersonalCenter.this);
        viewInCenterTowChoice.setData("选择照片", "拍照", "从相册选择");
        final PopupWindow popupWindow = PopupWindowUtil.getMacthParentPopupWindow(viewInCenterTowChoice, ActivityPersonalCenter.this);
        viewInCenterTowChoice.register(new ViewInCenterTowChoice.OnViewInCenterTwoChoiceListener() {
            @Override
            public void onClickOne() {
                popupWindow.dismiss();
                takePhoto();
            }

            @Override
            public void onClickTwo() {
                popupWindow.dismiss();
                getPhoto();
            }
        });
        popupWindow.showAtLocation(this.rootView, Gravity.CENTER, 0, 0);

    }


//    private void doUploadImage(final String path, String filename) {
//        new NiceAsyncTask(false) {
//            @Override
//            public void loadSuccess(BaseBean bean) {
//                UpLoadImageBean imageBean = (UpLoadImageBean) bean;
//                if (imageBean.getData() != null && !TextUtils.isEmpty(imageBean.getData().getSource())) {
//                    modifyAvatar(imageBean.getData().getSource(), path);
//                }
//            }
//
//            @Override
//            public void exception() {
//
//            }
//        }.updaloadImage(path, filename, UpLoadImageBean.class);
//    }

//    private void modifyAvatar(String source, final String path) {
//        Map<String,String> params = new HashMap<>();
//        params.put("source", source);
//        new NiceAsyncTask(false) {
//            @Override
//            public void loadSuccess(BaseBean bean) {
//            }
//
//            @Override
//            public void exception() {
//
//            }
//        }.post(true, RequestAPI.API_MODIFY_AVATAR, params, ModifyAvatarBean.class);
//    }

    private void updateMeFragmentAvatar() {
        Intent intent = new Intent(ConfigValue.kMeFragmentAvatar);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }


    @Override
    protected void onClickBack() {
        finish();
    }
}
