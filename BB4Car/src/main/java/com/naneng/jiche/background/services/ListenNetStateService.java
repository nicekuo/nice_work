package com.naneng.jiche.background.services;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.core.util.NiceLogUtil;
import com.core.util.NetWorkUtil;

import org.androidannotations.annotations.EService;

//监听手机网络状态（包括GPRS，WIFI， UMTS等)
//跳转service：ListenNetStateService_.intent(getApplication()).start();
//停止service：ListenNetStateService_.intent(getApplication()).stop();
@EService
public class ListenNetStateService extends Service {

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                NiceLogUtil.e("网络状态已经改变");
                ConnectivityManager connectivityManager =(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
                NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
                NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();  
                if(wifiInfo != null && wifiInfo.isConnected()){
                	NetWorkUtil.NETWORK=true;
                	//NiceLogUtil.e("当前网络名称：" + activeInfo.getTypeName());
                }else //NiceLogUtil.e("当前网络名称：" + activeInfo.getTypeName());
//NiceLogUtil.e("没有可用网络");
                    NetWorkUtil.NETWORK = mobileInfo != null && mobileInfo.isConnected();
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        IntentFilter mFilter = new IntentFilter();
        mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        LocalBroadcastManager.getInstance(this).registerReceiver(mReceiver, mFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }
}
