package com.naneng.jiche.background;

/**
 * Created by sufun_job on 2016/2/24.
 *
 * @author sufun_job
 * @description 系统常量存放
 */
public class ConfigValue {

    /**
     * 每一面显示的商品数据
     **/
    public static final int pageSize = 20;

    public static final int serviceReturnTokenInvalid = 30000;

    public static final String kOrderTypeGoods = "goods";

    public static final String kOrderTypeServices = "services";

    public static final String kOrderListTypeNotPay = "0";

    public static final String kOrderListTypeHasPaid = "2";

    public static final String kOrderListTypeNotComment = "5";


    /*购物车数量广播常量*/
    public static final String kProductCartCountBroadcast = "product_cart_count_bt";


    /*个人中心书刷新整个页面广播常量*/
    public static final String kMeFragmentLogin = "me_fragment_login";

    /*个人中心提示广播常量*/
    public static final String kMeFragmentTips = "me_fragment_bt";

    /*个人中心提示广播常量*/
    public static final String kMeFragmentAvatar = "me_fragment_avatar";

    /*订单列表刷新广播常量*/
    public static final String kOrderListUpdateBroadcast = "order_list_bt";

    /*刷新首页我的爱车广播常量*/
    public static final String kMainHomeCarBroadcast = "main_home_car_bt";


    public static final String kLaunchMainAcKey = "lanuch_main_activity_key";

    public static final String kLaunchMainAcTypeShowHome = "lanuch_main_activity_show_home";

    public static final String ACTION_LOGOIN_STATUS_CHANGED = "login_status_change";
    public static final String ACTION_DATA_KEY = "is_login";
    public static final String ACTION_DATA_VALUE_OUT = "out";
    public static final String ACTION_DATA_VALUE_IN = "in";


    public static final int kHTML_JS_LOGIN_REQUEST_CODE = 1238;



    /*scheme*/

    public static final int SCHEME_TYPE_GOODINFO = 1025;

    public static final int SCHEME_TYPE_SHOW_MESSAGE = 20000;

    public static final int SCHEME_TYPE_GET_USER_AND_APP_INFO = 20001;

    public static final int SCHEME_TYPE_LOGIN_AND_GET_USERINFO = 20002;

    public static final int SCHEME_TYPE_GOTO_COUPON_LIST = 20003;


    /*包名*/
    public static final String alipayCom = "com.eg.android.AlipayGphone";


    /*提示信息*/
    public static final String kMessageNetWorkError = "java.net.ConnectException";
    public static final String kMessageServiceError = "org.json.JSONException";

    public static final String AvaliableMessageNet = "网络跑的有点慢,请稍后再试!";
    public static final String AvaliableMessageService = "服务器连接失败,请稍后再试!";


    /*push 相关*/
    public static final String kPushType = "push_type";
    public static final String kPushValue = "push_value";


    /*intent 相关*/
    public static final String kIntentType = "intent_type";
    public static final String kIntentTypeInApp = "intent_in_app";
    public static final String kIntentTypeScheme = "intent_scheme";
    public static final String kIntentTypePush = "intent_push";

    /*push token*/
    public static final String kPushTokenUpdate = "push_token_update";



    /*微信登录*/
    public static final String kWeChatLoginKey = "wechat_login_key";
    public static final String ActionWeChatLogin = "action_wechat_login";

}
