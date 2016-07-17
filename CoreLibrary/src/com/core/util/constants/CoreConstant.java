package com.core.util.constants;

import com.core.R;

/**
 * @author miaoxin.ye
 * @createdate 2013-12-16 下午6:54:35
 * @Description: 常量
 */
public class CoreConstant {
    public static boolean IS_TEST_FLAG = true;        //日志打印,试运行环境、测试环境为true,生产环境为false
    public static boolean IS_UMENG = false;                //友盟统计控制开关,true允许使用,false则相反,配置后下次启动生效
    public static final String PREFERENCE_NAME = "club"; //SharedPreference文件名
    //缓存文件目录0为system目录,1为SD卡目录
    public static final int CACHE_DIR_SYSTEM = 0;
    public static final int CACHE_DIR_SD = 1;

    //版本更新
    public static boolean IS_CLIENTUP_DATE;               //客户端有升级，需要进行提示
    public static int NOW_VERSION;                        //客户端当前版本号
    public static int LOADING_PROCESS = 0;              //下载进度
    public static String DOWNLOAD_CLIENT_PATH;          //应用存储路径

    //36位UUID
    public static final String UUID = "uuid";
    //用户的登陆状态
    public static final String LOGIN_STATE = "LOGIN_STATE";
    //目标窗口的状态
    public static final String MAIN_AMBITION_STATE = "MAIN_AMBITION_STATE";
    //应该打开的网络界面
    public static final String WAP_SHOW_URL = "Web_Show_URl";

    public static final String CGM_PROJECT_NUMBER = ""; //google的推送CGM服务id


    public static final String PACKAGE_NAME = "com.core";


    /**
     * @param code
     * @return
     * @author caibing.zhang
     * @createdate 2015年3月18日 下午2:06:09
     * @Description: 成功状态码
     */
    public static boolean successCode(int code) {
        boolean flag = false;
        switch (code) {
            case 0: // 错误--》0：暂无沟通信息
                flag = true;
                break;
            case 1: // 成功
                flag = true;
                break;
            case 10: // 暂无物流信息
                flag = true;
                break;
            case 17: // 获取数据为空(暂时没有数据返回)
                flag = true;
                break;
            case 28: // 送货地址不存在，购买商品时需要跳转到地址填写界面，
                flag = true;
                break;
            case 24: // 商品金额已经全部用钱包支付,不需要跳转到paypal
                flag = true;
                break;
/*		case 25: // 在购物车进行商品支付的时候，商品的库存不足够
            flag = true;
			break;*/
            case 43: // 代表不存在SecretCode
                flag = true;
                break;
            case 44:// 代表过期 SecretCode
                flag = true;
                break;
            case 12:// 根据用户tokenid查不到 SecretCode
                flag = false;
                break;
            case 45: // 已经被使用过了
                flag = true;
                break;
            case 41://没有可抵
                flag = true;
                break;
            case 55://退款编号无效
                flag = false;
                break;
            default:
                flag = false;
                break;

        }
        return flag;
    }

    /**
     * @return
     * @author caibing.zhang
     * @createdate 2015年3月18日 下午2:05:43
     * @Description: 失败错误码
     */
    public static int errorCode(int code) {
        int resStringId = R.string.error_code_parameter_anomaly;
        switch (code) {
            // case 0: //错误
            // resStringId=R.string.error_code_error;
            // break;
            case 2: // 参数异常     用户有可能是以匿名状态的登陆，导致有一些接口返回提示参数异常。
                resStringId = R.string.error_code_parameter_anomaly;
                break;
            default:
                resStringId = R.string.error_code_unknown_error;
                break;
        }
        return resStringId;
    }

    public static final int CODE_COD_ERROR = 52;  //货到付款创建订单失败

    public static final int CODE_COD_ERROR_UNUPLOAD = 0;  //审核需上传照片

    public static final int CODE_COD_ERROR_PROCESSING = 1;  //待审核

    public static final int CODE_COD_ERROR_PASSED = 2;   //审核通过

    public static final int CODE_COD_ERROR_FAIL = 3;    //审核失败

    public static final int UPLOAD_PICTURE_TAKE_HEAD = 51;//头像拍照
    public static final int UPLOAD_PICTURE_HEAD = 50;//头像从手机选择照片
    public static final int UPLOAD_PICTURE_CROP = 53;//头像拍照


    public static final String URL = "url";//跳转到wap页所使用的url数据
}
