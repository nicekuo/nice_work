package com.core.util;

import android.util.Log;

import com.core.util.constants.CoreConstant;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

import java.util.Map;

/**
 * @author caibing.zhang
 * @createdate 2012-9-17 下午4:01:04
 * @Description: 日志
 */
public class NiceLogUtil {

    private static final String KEY = "--jiche--";

    static {
        if (CoreConstant.IS_TEST_FLAG) {
            Logger.init(KEY).setMethodCount(0).hideThreadInfo()
                    .setLogLevel(CoreConstant.IS_TEST_FLAG ? LogLevel.FULL : LogLevel.NONE);
        }
    }

    /*返回信息相关打印*/
    public static void response(String url, String completeUrl, String message) {
        if (CoreConstant.IS_TEST_FLAG) {
            Logger.init("  "+url);
            Logger.i(completeUrl);
            Logger.json(message);
        }
    }

    /*请求信息相关打印*/
    public static void request(String url, String completeUrl, Map<String, String> params) {
        if (CoreConstant.IS_TEST_FLAG) {
            Logger.init("  "+url);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(completeUrl);
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append("api = " + url);
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey() + " = " + entry.getValue());
                stringBuffer.append("\n");
                stringBuffer.append("\n");
            }
            Logger.i(stringBuffer.toString());
        }
    }

    public static void error(String url, String completeUrl, String message) {
        if (CoreConstant.IS_TEST_FLAG) {
            Logger.init("  "+url);
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(completeUrl);
            stringBuffer.append("\n");
            stringBuffer.append("\n");
            stringBuffer.append(message);
            Logger.e(stringBuffer.toString());
        }
    }

    public static void i(String message) {
        if (CoreConstant.IS_TEST_FLAG) {
            Log.i(KEY, message);
        }
    }

    public static void e(String message) {
        if (CoreConstant.IS_TEST_FLAG) {
            Log.e(KEY, message);
        }
    }

    public static void d(String message) {
        if (CoreConstant.IS_TEST_FLAG) {
            Log.d(KEY, message);
        }
    }

    public static void D(String message) {
        if (CoreConstant.IS_TEST_FLAG) {
            Log.d(KEY, message);
        }
    }

    public static void w(String message) {
        if (CoreConstant.IS_TEST_FLAG) {
            Log.w(KEY, message);
        }
    }

    public static void w(String message, Throwable tr) {
        if (CoreConstant.IS_TEST_FLAG) {
            Log.w(KEY, message, tr);
        }
    }
}
