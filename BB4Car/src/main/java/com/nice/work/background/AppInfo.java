package com.nice.work.background;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.nice.work.background.account.Account;
import com.tencent.android.tpush.XGPushConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class AppInfo {

    //通用参数
    public static final String SOURCE = "mob";
    public static String uuid = null;
    public final static String DEVICE_ID = "DeviceID";
    public static String imei = null;
    public static String mac = null;
    public static String qudao_code = null;
    public final static String via = "1";
    public static final String APP_NAME = "jiche";

    //客户端版本号
    public static String cver_name = "";
    public static String cver_code;


    public static String ver = "1.0";


    //唯一标示
    public static String Pseudo_Unique_ID = "";
    public static String Android_ID = "";


    /*手机品牌*/
    public static String device_merchant;
    public static String device_mold;
    public static String device_version;

    /*网络状态*/
    public static String net_type;

    public static String netError = "net_error";

    public static String push_token;


    public static String getAppInfoString() {
        StringBuilder stringBuilder = new StringBuilder();

        Account account = JICHEApplication.getInstance().getAccount();
        if (!TextUtils.isEmpty(account.token)) {
            stringBuilder.append("token = " + account.token + "\n");
            stringBuilder.append("member_id = " + account.member_id + "\n");
            stringBuilder.append("nickName = " + account.nickName + "\n");
            stringBuilder.append("userName = " + account.userName + "\n");
            stringBuilder.append("mobile = " + account.mobile + "\n");
        }

        stringBuilder.append("cver_name = " + cver_name + "\n");
        stringBuilder.append("cver_code = " + cver_code + "\n");
        stringBuilder.append("channel_id = " + qudao_code + "\n");
        stringBuilder.append("device_merchant = " + device_merchant + "\n");
        stringBuilder.append("device_mold = " + device_mold + "\n");
        stringBuilder.append("device_version = " + device_version + "\n");
        stringBuilder.append("push_token = " + push_token + "\n");
        stringBuilder.append("base_url = " + RequestAPI.getAbsoluteUrl("") + "\n");
        return stringBuilder.toString();
    }


    public static void combineId() {
        String m_szLongID = imei + Pseudo_Unique_ID
                + Android_ID + mac;
        MessageDigest m = null;
        try {
            m = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        assert m != null;
        m.update(m_szLongID.getBytes(), 0, m_szLongID.length());
        byte p_md5Data[] = m.digest();
        String m_szUniqueID = "";
        for (int i = 0; i < p_md5Data.length; i++) {
            int b = (0xFF & p_md5Data[i]);
            if (b <= 0xF)
                m_szUniqueID += "0";
            m_szUniqueID += Integer.toHexString(b);
        }
        m_szUniqueID = m_szUniqueID.toUpperCase();
        uuid = m_szUniqueID;
    }

    public static void retrieveAppInfo(Context context) {
        retrieveUUID(context);
        retrieveQudaoCode(context);
        getVersion(context);
        getVersionCode(context);
        initWidhei(context);
        device_merchant = Build.BRAND.trim().replaceAll(" ", "");
        device_mold = Build.MODEL.trim().replaceAll(" ", "");
        device_version = Build.VERSION.RELEASE.trim().replaceAll(" ", "");
        ConnectivityManager nw = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = nw.getActiveNetworkInfo();
        if (netinfo != null && netinfo.isAvailable()) {
            net_type = netinfo.getTypeName();
        } else {
            net_type = netError;
        }
        push_token = XGPushConfig.getToken(JICHEApplication.getInstance().getApplicationContext());
    }

    private static void retrieveUUID(Context context) {
        android_id(context);
        initPseudo_Unique_ID(context);
        retrieveImei(context);
        retrieveMac(context);
        // Init device id
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String deviceID = preferences.getString(AppInfo.DEVICE_ID, null);
        if (deviceID == null) {
            // Generate device id and save into pres
            AppInfo.combineId();
            deviceID = uuid;
            if (deviceID != null) {
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(context).edit();
                editor.putString(AppInfo.DEVICE_ID, deviceID);
                editor.commit();
            }
        }
        if (deviceID != null)
            uuid = deviceID;
        else
            uuid = "UNKNOWN";
    }

    /**
     * 获取设备imei号码
     *
     * @param context
     * @return
     */
    private static void retrieveImei(Context context) {// 上传imei号
        TelephonyManager manager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        imei = manager.getDeviceId();
    }

    /**
     * 获取设备mac地址
     *
     * @param context
     * @return
     */
    private static void retrieveMac(Context context) {
        WifiManager wifi = (WifiManager) context
                .getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        mac = info.getMacAddress();
    }

    public synchronized static void android_id(Context context) {
        Android_ID = Secure.getString(context.getContentResolver(), Secure.ANDROID_ID);
    }

    public static void initPseudo_Unique_ID(Context context) {
        String m_szDevIDShort = "35" + //we make this look like a valid IMEI
                Build.BOARD.length() % 10 +
                Build.BRAND.length() % 10 +
                Build.CPU_ABI.length() % 10 +
                Build.DEVICE.length() % 10 +
                Build.DISPLAY.length() % 10 +
                Build.HOST.length() % 10 +
                Build.ID.length() % 10 +
                Build.MANUFACTURER.length() % 10 +
                Build.MODEL.length() % 10 +
                Build.PRODUCT.length() % 10 +
                Build.TAGS.length() % 10 +
                Build.TYPE.length() % 10 +
                Build.USER.length() % 10; //13 digits
        Pseudo_Unique_ID = m_szDevIDShort;
    }

    /**
     * 获取渠道号
     *
     * @param context
     * @return
     */
    private static void retrieveQudaoCode(Context context) {
        try {
            ApplicationInfo appInfo =
                    context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (appInfo.metaData != null) {
                int value = appInfo.metaData.getInt("TD_CHANNEL_ID", 0);
                qudao_code = value + "";
                return;
            } else {
                qudao_code = "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
//        qudao_code = ChannelUtil.getChannel(context, "12000");
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static void getVersion(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            cver_name = info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取版本号
     *
     * @return 当前应用的版本号
     */
    public static void getVersionCode(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            cver_code = String.valueOf(info.versionCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*屏幕分辨率*/

    public static int width;
    public static int height;

    private static void initWidhei(Context context) {
//        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
//        Display display = wm.getDefaultDisplay();
//        Point point = new Point();
//        display.getSize(point);
//        if (point != null) {
//            width = point.x;
//            height = point.y;
//        } else {
//            width = 1080;
//            height = 1920;
//        }
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metric = new DisplayMetrics();
        context.getResources().getDisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metric);
        width = metric.widthPixels;     // 屏幕宽度（像素）
        height = metric.heightPixels;   // 屏幕高度（像素）
    }


}
