package com.naneng.jiche.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.naneng.jiche.background.JICHEApplication;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {


	private IWXAPI api;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		api = WXAPIFactory.createWXAPI(this, JICHEApplication.getInstance().kWeChatAppID);
		api.handleIntent(getIntent(), this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
		api.handleIntent(intent, this);
	}


	@Override
	public void onReq(BaseReq baseReq) {

	}

	@Override
	public void onResp(BaseResp baseResp) {

//		Intent mIntent = new Intent(ActivityCheckout.ACTION_NAME);//// TODO: 16/4/11
//
//		if (baseResp instanceof PayResp) {
//			switch (baseResp.errCode) {
//			case BaseResp.ErrCode.ERR_USER_CANCEL:
//				mIntent.putExtra(ActivityCheckout.ACTION_KEY, ActivityCheckout.ACTION_VALUE_CANCLE);
//				break;
//			case BaseResp.ErrCode.ERR_OK:
//				mIntent.putExtra(ActivityCheckout.ACTION_KEY, ActivityCheckout.ACTION_VALUE_OK);
//				break;
//			case BaseResp.ErrCode.ERR_SENT_FAILED:
//				mIntent.putExtra(ActivityCheckout.ACTION_KEY, ActivityCheckout.ACTION_VALUE_FAILD);
//				break;
//			}
//		}
//		LocalBroadcastManager.getInstance(this).sendBroadcast(mIntent);
		finish();
	}
}