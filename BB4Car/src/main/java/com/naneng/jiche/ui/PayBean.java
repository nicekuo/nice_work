package com.naneng.jiche.ui;

import com.core.bean.BaseBean;

/**
 * Created by nice on 16/4/11.
 */
public class PayBean extends BaseBean{

    private String data_alipay;

    private DataWeixinBean data_weixin;

    public String getData_alipay() {
        return data_alipay;
    }

    public void setData_alipay(String data_alipay) {
        this.data_alipay = data_alipay;
    }

    public DataWeixinBean getData_weixin() {
        return data_weixin;
    }

    public void setData_weixin(DataWeixinBean data_weixin) {
        this.data_weixin = data_weixin;
    }

    public static class DataWeixinBean {
        private String sign;
        private String timestamp;
        private String partnerid;
        private String noncestr;
        private String prepayid;
        private String pkg;
        private String appid;

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPkg() {
            return pkg;
        }

        public void setPkg(String pkg) {
            this.pkg = pkg;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }
    }
}
