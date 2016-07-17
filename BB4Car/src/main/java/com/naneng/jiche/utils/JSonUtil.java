package com.naneng.jiche.utils;

import com.alibaba.fastjson.JSON;

/**
 * Created by nice on 16/3/21.
 */
public class JSonUtil {

    private static JSonUtil JSonUtil;


    public static JSonUtil getIntence(){
        if (JSonUtil == null){
            JSonUtil = new JSonUtil();
        }
        return JSonUtil;
    }

    public String gsonToStr(Object src){
        return JSON.toJSONString(src);
    }

    public <T> T fromJsonWithNoException(String json, Class<T> classOfT) {
        Object object = null;
        try {
            object = JSON.parseObject(json, classOfT);
        } catch (Exception var5) {

        }
        return (T) object;
    }

}
