package com.naneng.jiche.utils;

import com.naneng.jiche.background.JICHEApplication;
import com.naneng.jiche.ui.PayBean;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 * Created by nice on 16/4/11.
 */
public class PayUtil {


    public static void wechatPay(PayBean.DataWeixinBean payBean) {

        IWXAPI api = JICHEApplication.getInstance().getWxApi();
        if (api == null) {
            return;
        }

        PayReq req = new PayReq();
        req.appId = payBean.getAppid();
        req.partnerId = payBean.getPartnerid();
        req.prepayId = payBean.getPrepayid();
        req.nonceStr = payBean.getNoncestr();
        req.timeStamp = payBean.getTimestamp();
        req.packageValue = payBean.getPkg();
        req.sign = payBean.getSign();
        req.extData = "app data";
        boolean is = api.sendReq(req);
//        ToastUtil.showToastMessage(JICHEApplication.getInstance(),is+"");
    }


}
