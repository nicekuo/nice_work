package com.nice.work.background;

import android.text.TextUtils;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class ImageUrlUtil {

    public final static int kImageTypeBig = 1;
    public final static int kImageTypeSmall = 2;
    public final static int kImageTypeThumb = 3;

    private final static String kBigStr = ".big";
    private final static String kSmallStr = ".small";
    private final static String kThumbStr = ".thumb";

    public static String getImageUrlByType(int type, String fromUrl) {
        if (TextUtils.isEmpty(fromUrl)) {
            return "";
        }
        String endStr = "";

        switch (type) {
            case kImageTypeBig:
                endStr = kBigStr;
                break;
            case kImageTypeSmall:
                endStr = kSmallStr;
                break;
            case kImageTypeThumb:
                endStr = kThumbStr;
                break;
        }

        if (TextUtils.isEmpty(endStr)) {
            endStr = kSmallStr;
        }

        String gotoUrl = fromUrl + endStr;
        return gotoUrl;
    }

}
