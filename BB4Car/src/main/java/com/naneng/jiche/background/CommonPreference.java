package com.naneng.jiche.background;

import com.core.util.file.SharedPreferenceUtil;

/**
 * Created by nice on 16/3/22.
 */
public class CommonPreference  {

    private static final String kACCOUNT = "account";
    private static final String kADDRESS = "address";
    private static final String kPushToken = "push_token";



    public static void saveAccount(String account){
        SharedPreferenceUtil.saveString(JICHEApplication.getInstance(),kACCOUNT,account);
    }

    public static String getAccount(){
        return SharedPreferenceUtil.getString(JICHEApplication.getInstance(),kACCOUNT);
    }

    public static void saveAddress(String address){
        SharedPreferenceUtil.saveString(JICHEApplication.getInstance(),kADDRESS,address);
    }

    public static String getAddress(){
        return SharedPreferenceUtil.getString(JICHEApplication.getInstance(),kADDRESS);
    }

    public static void saveToken(String token){
        SharedPreferenceUtil.saveString(JICHEApplication.getInstance(),kPushToken,token);
    }

    public static String getToken(){
        return SharedPreferenceUtil.getString(JICHEApplication.getInstance(),kPushToken);
    }


}
