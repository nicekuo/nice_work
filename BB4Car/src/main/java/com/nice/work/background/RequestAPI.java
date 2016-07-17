package com.nice.work.background;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author caibing.zhang
 * @createdate 2015年1月31日 下午12:25:09
 * @Description: 请求API
 */
public class RequestAPI {

    public static final String OFFICIAL_BASE_URL = "http://192.130.02.1/";//正式访问地址。

    public static final String DEVELOP_BASE_URL = "http://192.130.02.1/";//测试访问地址。


    /*正式url*/
    public static final String API_MEMBER_LOGIN_MOBILE = "member/loginMobile";

    public static final String API_MEMBER_LOGIN_NORMAL = "member/loginNormal";

    public static final String API_ADD_CAR_TO_MY_LIST = "memberCar/addMemberCar";

    /**
     * 获取我的车的列表
     **/
    public static final String API_MY_CAR_LIST = "memberCar/findMemberCar";

    public static final String API_ORDER_DETAIL_BY_ID = "orders/getOrdersByMemberId";

    public static final String API_MEMBER_CHANGE_CAR = "memberCar/updateCurrentCar";

    public static final String API_DELETE_MY_CAR = "memberCar/delMemberCar";

    public static final String API_COUPON_GET_COUPON = "memberCoupons/getMyCoupons";

    public static final String API_ORDER_INFO = "orders/getOrderDetail";

    public static final String API_ADD_GOODS_COMMENT = "api/product/addProductComments";

    public static final String API_GET_FAVORIATE_SHOPS = "member/getFavoritesShop";

    public static final String API_CHANGE_PASSWORD = "member/setNamePwd";

    public static final String API_ADD_FAVORITE_SHOPS = "member/addFavoritesShop";

    public static final String API_DELETE_FAVORITE_SHOPS = "member/delFavoritesShop";

    public static final String API_GET_MINE_ALL = "member/getMineAll";

    public static final String API_CHECK_ORDER = "orders/checkOrder";

    public static final String API_GET_SHOP_SERVICES = "orders/getServicesProduct";

    public static final String API_ORDER_CREATE = "orders/save";

    public static final String API_ORDE_PAY = "pay";

    public static final String API_EXCHANGE_SERVICE_GOOD = "api/product/changeProduct";

    public static final String API_ADD_SHOP_COMMENT = "shops/addShopsComments";

    public static final String API_ORDER_CANCLE = "orders/cancelOrder";

    public static final String URL_Image_Upload = "api/upload/uploadImg";

    public static final String API_MODIFY_AVATAR = "member/modifyMemberHead";

    public static final String API_MODIFY_INFO = "member/addMemberInfo";

    public static final String SMS_REQUEST = "sms/request";

    public static final String SMS_VALIDATE = "sms/validate";

    public static final String API_MEMBER_PUSH_TOKEN = "member/getPushToken";

    public static final String API_MEMBER_LOGIN_OUT = "member/loginOut";

    public static final String API_MEMBER_LOGIN_WECHAT = "weChat/loginWeChat";



    private static List<String> needTokenURLList = Arrays.asList(API_MEMBER_CHANGE_CAR, API_ADD_CAR_TO_MY_LIST, API_DELETE_MY_CAR, API_MY_CAR_LIST,
            API_ORDER_DETAIL_BY_ID, API_COUPON_GET_COUPON, API_ORDER_INFO, API_ADD_GOODS_COMMENT, API_GET_FAVORIATE_SHOPS, API_CHANGE_PASSWORD,
            API_DELETE_FAVORITE_SHOPS, API_ADD_FAVORITE_SHOPS, API_GET_MINE_ALL, API_CHECK_ORDER, API_GET_SHOP_SERVICES, API_ORDER_CREATE, API_ORDE_PAY,
            API_EXCHANGE_SERVICE_GOOD, API_ADD_SHOP_COMMENT, API_ORDER_CANCLE, URL_Image_Upload, API_MODIFY_AVATAR,API_MODIFY_INFO,API_MEMBER_LOGIN_OUT
    );


    public static boolean isNeedTokenURL(String url) {
        return needTokenURLList.contains(url);
    }

    public static String getAbsoluteUrl(String relativeUrl) {
        if (JICHEApplication.getInstance().isDevelop()) {
            return RequestAPI.DEVELOP_BASE_URL + relativeUrl;
        } else {
            return RequestAPI.OFFICIAL_BASE_URL + relativeUrl;
        }
    }

    public static Map<String, String> configRequestParams(Map<String, String> params) {
        if (params == null) {
            params = new HashMap<>();
        }
        params.put("app", AppInfo.APP_NAME);
        params.put("via", AppInfo.via);
        params.put("cver", AppInfo.cver_name);
        params.put("ver",AppInfo.ver);
        params.put("version_code", AppInfo.cver_code);
        params.put("uuid", AppInfo.uuid);
        params.put("channel_id", AppInfo.qudao_code);
        return params;
    }


}
