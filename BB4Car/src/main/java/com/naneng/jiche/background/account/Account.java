package com.naneng.jiche.background.account;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.naneng.jiche.background.CommonPreference;
import com.naneng.jiche.background.JICHEApplication;
import com.naneng.jiche.ui.account.LoginBean;

import org.json.JSONException;
import org.json.JSONObject;

//本地的用户的简单数据只存储一份
public class Account {
    public static String kToken = "token";
    public static String kMemberId = "member_id";
    public static String kMobil = "mobile";
    public static String kNickName = "nick_name";
    public static String kUSERName = "user_name";
    public static String kAvatarPath = "avatar_path";
    public static String kPushState = "push_state";
    public static String kBorn = "born";
    public static String kGender = "gender";
    public static String kRealNme = "real_name";
    public static String kRankName = "rank_name";

    public String token;
    public String member_id;
    public String mobile;
    public String nickName;
    public String userName;
    public String avatar = "";
    public String born;
    public String gender;
    public String realname;
    public String rankname;
    public int pushState = 1;// 1开启 0关闭

    // 用户选择照片时需要记住用户的偏好的文件夹的key
    public static String kLastSelectDir = "lastSelectDir";
    public String lastSelectDir;

    public Account() {
        loadAccount();
    }

    private void loadAccount() {
        String info = CommonPreference.getAccount();
        if (!TextUtils.isEmpty(info)) {
            JSONObject object = JICHEApplication.getInstance().strConvertToJson(info);
            if (object != null) {
                token = object.optString(kToken);
                member_id = object.optString(kMemberId);
                mobile = object.optString(kMobil);
                nickName = object.optString(kNickName);
                userName = object.optString(kUSERName);
                lastSelectDir = object.optString(kLastSelectDir);
                avatar = object.optString(kAvatarPath);
                realname = object.optString(kRealNme);
                born = object.optString(kBorn);
                gender = object.optString(kGender);
                rankname = object.optString(kRankName);
            }
        }
    }

    public void saveAccountInfo(LoginBean info) {
        if (info.getData() != null && info.getData().getToken() != null) {
            member_id = info.getData().getMember_id();
            mobile = info.getData().getMobile();
            nickName = info.getData().getNickname();
            avatar = info.getData().getAvatar();
            token = info.getData().getToken();
            userName = info.getData().getUsername();
            born = info.getData().getBorn();
            gender = info.getData().getSex();
            realname = info.getData().getRealname();
            rankname = info.getData().getRank_name();
            if (TextUtils.isEmpty(rankname)) {
                rankname = "**会员";
            }
            saveToPreference();
        }
    }


    public void uodateAvatar(String avatar) {
        this.avatar = avatar;
        saveToPreference();
    }

    public void updateInfo(String key, String value) {
        if (key.equals(kBorn)) {
            this.born = value;
        } else if (key.equals(kGender)) {
            this.gender = value;
        } else if (key.equals(kRealNme)) {
            this.realname = value;
        } else if (key.equals(kNickName)) {
            this.nickName = value;
        } else if (key.equals(kRankName)) {
            this.rankname = value;
        }
        saveToPreference();
    }

    private void saveToPreference() {
        try {
            JSONObject object = new JSONObject();
            object.put(kToken, token);
            object.put(kMemberId, member_id);
            object.put(kMobil, mobile);
            object.put(kNickName, nickName);
            object.put(kLastSelectDir, lastSelectDir);
            object.put(kAvatarPath, avatar);
            object.put(kPushState, pushState);
            object.put(kUSERName, userName);
            object.put(kBorn, born);
            object.put(kGender, gender);
            object.put(kRealNme, realname);
            object.put(kRankName, rankname);
            CommonPreference.saveAccount(object.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void clear() {
        token = "";
        member_id = "";
        mobile = "";
        nickName = "";
        avatar = "";
        born = "";
        gender = "";
        realname = "";
        userName = "";
        rankname = "";
        saveToPreference();
    }
}
