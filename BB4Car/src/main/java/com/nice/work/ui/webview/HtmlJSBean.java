package com.nice.work.ui.webview;

import com.core.bean.BaseBean;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class HtmlJSBean extends BaseBean {


    /**
     * type : 20001
     * value :
     * callBack : channelCallBack
     * key :
     */

    private int type;
    private String value;
    private String callBack;
    private String key;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCallBack() {
        return callBack;
    }

    public void setCallBack(String callBack) {
        this.callBack = callBack;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
