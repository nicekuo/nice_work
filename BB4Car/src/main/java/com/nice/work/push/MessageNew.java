package com.nice.work.push;

import java.io.Serializable;

/**
 * Created by sreay on 14-7-24.
 */
public class MessageNew implements Serializable, Comparable<MessageNew> {
    public static final int PUSH_TYPE_APP = 0;
    public static final int PUSH_TYPE_WEBVIEW = 1;
    public static final int PUSH_TYPE_PRODUCTINFO = 2;
    public static final int PUSH_TYPE_ORDERINFO = 3;
    private static final long serialVersionUID = -6656590404064880429L;
    public String title;
    public String content;
    public String mtype;
    public String value;
    public String time;

    public int count;

    @Override
    public int compareTo(MessageNew another) {
        if (another == null) {
            return 1;
        }
        String str = this.time;
        String str1 = another.time;
        return str1.compareTo(str);
    }
}
