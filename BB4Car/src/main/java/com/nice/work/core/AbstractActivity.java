package com.nice.work.core;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.core.DownLoadTask;
import com.core.R;
import com.core.activity.AbstractCoreActivity;
import com.core.bean.BaseBean;
import com.core.crouton.Crouton;
import com.core.crouton.Style;
import com.core.util.CoreUtil;
import com.core.util.NiceLogUtil;
import com.core.util.NetWorkUtil;
import com.core.util.ToastUtil;
import com.core.util.constants.CoreConstant;
import com.core.util.file.FileLocalCache;
import com.core.util.file.FileUtil;
import com.core.util.image.ImageUtil;
import com.core.widget.sweetalert.SweetAlertDialog;
import com.core.wigets.TitleView;
import com.nice.work.background.ConfigValue;
import com.nice.work.background.JICHEApplication;
import com.nice.work.background.RequestAPI;
import com.nice.work.ui.main.UpgradeBean;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import okhttp3.Call;
import okhttp3.Response;


/**
 * @createdate 2013-12-17 下午2:44:50
 * @Description: Activity基类
 */
public abstract class AbstractActivity extends AbstractCoreActivity {

    protected boolean isTemplate = true; // 是否使用模板
    private View waitingView;
    public TitleView titleView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CoreUtil.addAppActivity(this);
        if (savedInstanceState != null) {
            NiceLogUtil.e("------------恢复------------->>");
        }
        super.setContentView(R.layout.template);
        mainBody = (LinearLayout) findViewById(R.id.view_mainBody);
        titleView = (TitleView) findViewById(R.id.title_iv_id);
        rootView = (FrameLayout) findViewById(R.id.fl_panent_id);
        if (isTemplate) {
            titleView.setVisibility(View.VISIBLE);
            titleView.setTitleLeftButtonListener(new View.OnClickListener() {
                @Override
                public void onClick(View button) {
                    onClickBack();
                }
            });
            titleView.mRightBtn.setVisibility(View.GONE);
        } else {
            titleView.setVisibility(View.GONE);
        }
    }

    protected abstract void onClickBack();

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void setContentView(int layoutResID) {
        if (layoutResID == R.layout.template) {
            super.setContentView(layoutResID);
        } else {
            if (mainBody != null) {
                mainBody.removeAllViews();
                // mainBody.addView(this.getLayoutInflater().inflate(layoutResID,null));
                mainBody.addView(
                        this.getLayoutInflater().inflate(layoutResID, null),
                        new LayoutParams(LayoutParams.MATCH_PARENT,
                                LayoutParams.MATCH_PARENT));
            } else {
                super.setContentView(layoutResID);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    public boolean isImageViewShowing() {
        if (rootView != null && rootView.getChildCount() > 1) {
            rootView.removeViewAt(1);
            return true;
        }
        return false;
    }

    public boolean isDialogShowing() {
        if (dialog != null && dialog.isShowing()) {
            dissmissWaitingDialog();
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        CoreUtil.removeAppActivity(this);
        OkHttpUtils.getInstance().cancelTag(this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }


    /**
     * @param message
     * @author caibing.zhang
     * @createdate 2012-9-24 上午9:10:36
     * @Description: 显示Toast消息
     */
    public void showToastCroutonMessage(Style style, String message) {
        Crouton.makeText(this, message, style, R.id.fl_panent_id).show();
    }

    public void showToastCroutonMessage(Style style, int resString) {
        showToastCroutonMessage(style, getString(resString));
    }

    /**
     * @param message
     * @author caibing.zhang
     * @createdate 2012-9-24 上午9:10:36
     * @Description: 显示Toast消息
     */
    public void showToastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // Crouton.makeText(this, message,style).show();
    }

    public void showToastMessage(int resString) {
        showToastMessage(getString(resString));
    }

    /**
     * @author caibing.zhang
     * @createdate 2015年1月31日 上午10:47:10
     * @Description: 加载失败显示的页面【显示默认的图片】
     */
    public void showBodyInfoToLoadError(String info) {
        templateView = getLayoutInflater()
                .inflate(R.layout.template_fail, null);
        super.showBodyInfo(info);
    }

    /**
     * @param resStr 文字资源
     * @author caibing.zhang
     * @createdate 2015年1月31日 上午10:47:10
     * @Description: 加载失败显示的页面【显示默认的图片】
     */
    public void showBodyInfoToLoadError(int resStr) {
        showBodyInfoToLoadError(getString(resStr));
    }

    /**
     *
     */
    /**
     * @param resImg 图片资源
     * @param resStr 文字资源
     * @author caibing.zhang
     * @createdate 2015年1月31日 上午10:46:36
     * @Description: 加载失败显示的页面【设置图片】
     */
    public void showBodyInfoToLoadError(int resImg, int resStr) {
        templateView = getLayoutInflater()
                .inflate(R.layout.template_fail, null);
        ImageView image = (ImageView) templateView.findViewById(R.id.icon_id);
        image.setBackgroundResource(resImg);
        super.showBodyInfo(getString(resStr));
    }

    /**
     * sufun
     *
     * @param message
     * @param cancelable
     * @return
     */
    public Dialog createWaitingDatlog(String message, boolean cancelable) {
        Dialog mdialog = new Dialog(this, R.style.dialog_transparent);
        View waitingView = getLayoutInflater().inflate(
                R.layout.circular_progress, null);
        // waitingView.setBackgroundResource(R.drawable.img_loading_super_bg);
        waitingView.invalidate();
        mdialog.setContentView(waitingView);
        mdialog.setCancelable(cancelable);
        mdialog.show();
        return mdialog;
    }

    /**
     * 显示加载对话框
     */
    @Override
    public Dialog showWaitDialog(String message, boolean cancelable) {
        if (null == dialog) {
            dialog = new Dialog(this, R.style.dialog_transparent);
            waitingView = getLayoutInflater().inflate(
                    R.layout.circular_progress, null);
            waitingView.invalidate();
            dialog.setContentView(waitingView);
        }
        try {
            dialog.setCancelable(cancelable);
            dialog.show();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return dialog;
    }

    /**
     * 2015年12月25日 18:23:58
     *
     * @description 展示ok的对话框
     */
    public void showOkDialog(SweetAlertDialog.OnSweetClickListener confirmClickListener, String content, String title) {
        SweetAlertDialog dialog = new SweetAlertDialog(AbstractActivity.this, SweetAlertDialog.NORMAL_TYPE);
        dialog.setTitleText(title)
                .setConfirmText(getString(R.string.dialog_ok))
                .setContentText(content)
                .setCancelText(getString(R.string.dialog_cancel))
                .setConfirmClickListener(confirmClickListener);
        dialog.showCancelButton(true);
        dialog.show();
    }

    public abstract class NiceAsyncTask {
        // 是否需要进行网络判断,true判断(默认),false不需要判断
        private boolean isNetWork = true;
        // 是否覆盖mainBody显示showWaitDialog, true覆盖显示showWaitDialog,false覆盖，不显示
        private boolean isCover;
        private String completeUrl;
        private String absoluteUrl;
        private String apiUrl;
        private Map<String, String> params;

        private Class<? extends BaseBean> clazz;

        public NiceAsyncTask() {
            this.isCover = false;
        }

        /**
         * @param isCover 是否覆盖mainBody显示showWaitDialog,
         *                true覆盖显示showWaitDialog,false不覆盖显示showWaitDialog，
         */
        public NiceAsyncTask(boolean isCover) {
            this.isCover = isCover;
        }

        /**
         * @param isCover   是否覆盖mainBody显示showWaitDialog,
         *                  true覆盖显示showWaitDialog,false不覆盖显示showWaitDialog
         * @param isNetWork 是否需要进行网络判断,true判断(默认),false不需要判断
         */
        public NiceAsyncTask(boolean isCover, boolean isNetWork) {
            this.isCover = isCover;
            this.isNetWork = isNetWork;
        }

        /**
         * @author caibing.zhang
         * @createdate 2015年1月17日 下午3:10:26
         * @Description: 网络加载成功
         */
        public abstract void loadSuccess(BaseBean bean);

        /**
         * @author caibing.zhang
         * @createdate 2015年1月17日 下午3:10:49
         * @Description: 网络加载失败：异常处理
         */
        public abstract void exception();


        public void updaloadImage(String path, String filename, Class<? extends BaseBean> clazz) {
            Map<String, String> params = new HashMap<>();
            doRequestForFile(true, RequestAPI.URL_Image_Upload, params, clazz, path, filename);
        }


        /**
         * @param url
         * @param params 参数
         * @param clazz  json解析的对象
         * @author caibing.zhang
         * @createdate 2015年1月20日 下午9:57:39
         * @Description: post
         */
        public void post(String url, Map<String, String> params,
                         final Class<? extends BaseBean> clazz) {
            post(true, url, params, clazz);
        }

        /**
         * @param isLoading     是否显示加载对话框
         * @param url
         * @param requestParams param params    参数
         * @param clazz         json解析的对象
         * @author caibing.zhang
         * @createdate 2015年1月20日 下午9:57:39
         * @Description: post
         */

        public void post(boolean isLoading, String url, Map<String, String> requestParams,
                         Class<? extends BaseBean> clazz) {
            doRequest(isLoading, url, requestParams, clazz, true);
        }

        /**
         * @param isLoading     是否显示加载对话框
         * @param url           参数
         * @param requestParams
         * @param clazz         json解析的对象
         * @author caibing.zhang
         * @createdate 2015年1月20日 下午9:57:39
         * @Description: post
         */
        public void get(boolean isLoading, String url, Map<String, String> requestParams,
                        Class<? extends BaseBean> clazz) {
            doRequest(isLoading, url, requestParams, clazz, false);
        }

        private void doRequest(boolean isLoading, String url, Map<String, String> requestParams,
                               Class<? extends BaseBean> clazz, boolean isPost) {
            if (RequestAPI.isNeedTokenURL(url)) {
                if (!JICHEApplication.getInstance().getLoginState()) {
                    //需要token的url,并且此时还没有登录,需要弹出登录页面
                    JICHEApplication.getInstance().gotoLogin(AbstractActivity.this);
                    return;
                } else {
                    requestParams.put("token", JICHEApplication.getInstance().getAccount().token);
                }
            }

            absoluteUrl = RequestAPI.getAbsoluteUrl(url);
            params = RequestAPI.configRequestParams(requestParams);

            // 没有网络或不需要网络判断
            if (isNetWork && !NetWorkUtil.NETWORK) {
                ToastUtil.showToastMessage(AbstractActivity.this, getResources().getString(R.string.not_network));
                exception();
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(absoluteUrl);
            stringBuffer.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey());
                stringBuffer.append("=");
                stringBuffer.append(entry.getValue());
                stringBuffer.append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            this.completeUrl = stringBuffer.toString();
            this.clazz = clazz;
            this.apiUrl = url;
            if (isLoading) {
                showLoadDialog(true);
            }
//            if (isPost) {
//                doRequestPOSTByOkHttpUtils(absoluteUrl, params);
//            } else {
                doRequestGETByOkHttpUtils(absoluteUrl, params);
//            }
            NiceLogUtil.request(apiUrl, completeUrl, params);
        }

        private void doRequestGETByOkHttpUtils(String url, Map<String, String> params) {
            OkHttpUtils
                    .get()
                    .url(url)
                    .params(params)
                    .tag(AbstractActivity.this)
                    .build()
                    .execute(new NiceOkHttpCallBack());
        }

        private void doRequestPOSTByOkHttpUtils(String url, Map<String, String> params) {
            OkHttpUtils
                    .post()
                    .url(url)
                    .params(params)
                    .tag(AbstractActivity.this)
                    .build()
                    .execute(new NiceOkHttpCallBack());
        }

        private void doUploadPOSTByOkHttpUtil(String path, String name) {
            File file = new File(path);
            if (!file.exists()) {
                ToastUtil.showToastMessage(AbstractActivity.this, "文件不存在");
                return;
            }
            OkHttpUtils.post()
                    .addFile("mFile", name, file)//
                    .url(absoluteUrl)//
                    .params(params)//
                    .tag(AbstractActivity.this)
                    .build()//
                    .execute(new NiceOkHttpCallBack());
        }


        private void doRequestForFile(boolean isLoading, String url, Map<String, String> requestParams,
                               Class<? extends BaseBean> clazz,String path,String filename) {
            if (RequestAPI.isNeedTokenURL(url)) {
                if (!JICHEApplication.getInstance().getLoginState()) {
                    //需要token的url,并且此时还没有登录,需要弹出登录页面
                    JICHEApplication.getInstance().gotoLogin(AbstractActivity.this);
                    return;
                } else {
                    requestParams.put("token", JICHEApplication.getInstance().getAccount().token);
                }
            }

            absoluteUrl = RequestAPI.getAbsoluteUrl(url);
            params = RequestAPI.configRequestParams(requestParams);

            // 没有网络或不需要网络判断
            if (isNetWork && !NetWorkUtil.NETWORK) {
                ToastUtil.showToastMessage(AbstractActivity.this, getResources().getString(R.string.not_network));
                exception();
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(absoluteUrl);
            stringBuffer.append("?");
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey());
                stringBuffer.append("=");
                stringBuffer.append(entry.getValue());
                stringBuffer.append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
            this.completeUrl = stringBuffer.toString();
            this.clazz = clazz;
            this.apiUrl = url;
            if (isLoading) {
                showLoadDialog(true);
            }
            doUploadPOSTByOkHttpUtil(path,filename);
            NiceLogUtil.request(apiUrl, completeUrl, params);
        }


        private class NiceOkHttpCallBack extends Callback<String> {

            @Override
            public String parseNetworkResponse(Response response, int id) throws Exception {
                NiceLogUtil.D("NiceOkHttpCallBack parseNetworkResponse   = " + response.body().toString());
                return response.body().string();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                dissmissWaitingDialog();
                exception();
                NiceLogUtil.error(apiUrl, completeUrl, e.getMessage());
                if (e instanceof UnknownHostException) {
                    ToastUtil.showToastMessage(AbstractActivity.this, "网络连接异常,请检查网络设置");
                } else if (e instanceof IOException) {
                    ToastUtil.showToastMessage(AbstractActivity.this, "服务器连接异常,请稍后再试");
                } else {
                    ToastUtil.showToastMessage(AbstractActivity.this, "未知错误,请稍后再试");
                }
            }

            @Override
            public void onResponse(String response, int id) {
                dissmissWaitingDialog();
                NiceLogUtil.response(apiUrl, completeUrl, response);
                String responseInfo = response.toString();
                FileLocalCache.saveFile(completeUrl, responseInfo);
                BaseBean bean = JICHEApplication.getInstance().strConvertToJson(responseInfo, clazz);
                if (bean == null) {
                    ToastUtil.showToastMessage(AbstractActivity.this, "服务器数据错误,无法解析");
                    exception();
                } else {
                    if (bean.result == ConfigValue.serviceReturnTokenInvalid) {
                        //token失效 踢下线
                        //清除account
                        JICHEApplication.getInstance().clearToken();
                        //发送下线的广播
                        Intent intent = new Intent(ConfigValue.ACTION_LOGOIN_STATUS_CHANGED);
                        intent.putExtra(ConfigValue.ACTION_DATA_KEY, ConfigValue.ACTION_DATA_VALUE_OUT);
                        LocalBroadcastManager.getInstance(AbstractActivity.this).sendBroadcast(intent);

                        //弹出登录页面
                        JICHEApplication.getInstance().gotoLogin(AbstractActivity.this);

                        AbstractActivity.this.finish();

                    } else if (bean.result == 1) {
                        loadSuccess(bean);
                    } else {
                        ToastUtil.showToastMessage(AbstractActivity.this, bean.error_info);
                    }
                }
            }
        }


        private void showAvaliableMessage(String message) {
            dissmissWaitingDialog();
            String avaliableM = "";
            if (!TextUtils.isEmpty(message)) {
                if (message.startsWith(ConfigValue.kMessageNetWorkError)) {
                    avaliableM = ConfigValue.AvaliableMessageNet;
                    ToastUtil.showToastMessage(AbstractActivity.this, avaliableM);
                } else if (message.startsWith(ConfigValue.kMessageServiceError)) {
                    avaliableM = ConfigValue.AvaliableMessageService;
                    ToastUtil.showToastMessage(AbstractActivity.this, avaliableM);
                }

            }
        }

        /**
         * 将异常信息转化成字符串
         *
         * @param t
         * @return
         * @throws IOException
         */
        private String analysisException(Throwable t) throws IOException {
            if (t == null)
                return null;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                t.printStackTrace(new PrintStream(baos));
            } finally {
                baos.close();
            }
            return baos.toString();
        }

    }

    /**
     * @param cancelable 是否支持取消
     * @Description: 显示加载对话框前初始化
     */
    public void showLoadDialog(boolean cancelable) {
        dialog = showWaitDialog(null, cancelable);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.dismiss();
                AbstractActivity.this.finish();
            }
        });
    }

    /**
     * 设置标题
     */
    public void setTitleName(String titleName) {
        titleView.setTitleName(titleName);
    }

    /**
     * 设置标题
     */
    protected void setTitleName(int stringID) {
        titleView.setTitleName(stringID);
    }

    /**
     * 设置左按钮事件
     */
    protected void setTitleLeftButtonListener(View.OnClickListener listener) {
        titleView.setTitleLeftButtonListener(listener);
    }

    /**
     * 设置左按钮背景和事件
     */
    protected void setTitleLeftImageButton(int imgID,
                                           View.OnClickListener listener) {
        titleView.setTitleLeftImageButton(imgID, listener);
    }

    protected void setTitleRightButtonText(String text, View.OnClickListener listener) {
        titleView.setTitleRightButtonText(text, listener);
    }

    protected void setTitleRightButtonTextAndErrow(String text, View.OnClickListener listener) {
        titleView.setTitleRightButtonTextAndErrow(text, listener);
    }

    protected void updateTitleRightButtonText(String text) {
        titleView.updateRightButtonText(text);
    }


    /**
     * 设置右按钮文本和事件
     *
     * @param text
     * @param listener
     */
    protected void setTitleRightButton(String text,
                                       View.OnClickListener listener) {
        titleView.setTitleRightButtonText(text, listener);
    }

    protected void showCartMenuItem(View.OnClickListener listener) {
        titleView.showCartItem(listener);
    }

    protected void updateCartMenuItem(int count) {
        titleView.updateCartItem(count);
    }

    /**
     * 设置右按钮背景和事件
     */
    protected void setTitleRightImageButton(int imageResId,
                                            View.OnClickListener listener) {
        titleView.setTitleRightImageButton(imageResId, listener);
    }

    /**
     * 设置右按钮背景和事件
     */
    protected void setTitleRightImageButtonTwo(int imageResId,
                                            View.OnClickListener listener) {
        titleView.setTitleRightImageButtonTwo(imageResId, listener);
    }



    /**
     * 隐藏右侧按钮
     */
    protected void hiddenTitleRightButton() {
        titleView.hiddenTitleRightButton();
    }

    /**
     * 隐藏左侧按钮
     */
    protected void hiddenTitleLeftButton() {
        titleView.hiddenTitleLeftButton();
    }

    /**
     * 隐藏右侧按钮
     */
    protected void showTitleRightButton() {
        titleView.showTitleRightButton();
    }

    public void updateVersion(final UpgradeBean upgradeBean) {
        SweetAlertDialog dialog = new SweetAlertDialog(
                AbstractActivity.this, SweetAlertDialog.Upgrade_type)
                .setTitleText(getString(R.string.update_version_title))
                .setContentText(upgradeBean.getData().getDescription())
                .setCancelText(getString(R.string.upgrade_immediately))
                .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                    @Override
                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                        new DownLoadTask(AbstractActivity.this).startDownLoad(upgradeBean.getData().getUrl());
                    }
                });
        // 0=强制升级
        if (upgradeBean.getData().isIsForce()) {
            dialog.setCancelText(getString(R.string.upgrade_immediately));
            dialog.setCancelables(false);
        } else {
            dialog.setCancelables(true);
        }
        dialog.show();
    }

    /*------------------------------------------------相机的相关方法（begin）---------------------------------------------------------------------*/
    /**
     * 拍照头像路径
     */
    private String photoPath;
    /**
     * 上传头像路径
     */
    public String uploadPhotoPath;

    private Uri uri;

    private ITakePhotoUrl photoUrl;

    public void setPhotoUrl(ITakePhotoUrl photoUrl) {
        this.photoUrl = photoUrl;
    }

    /**
     * 打开相机 拍照
     */
    public void takePhoto() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        updateURIAndPath();
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        startActivityForResult(cameraIntent, CoreConstant.UPLOAD_PICTURE_TAKE_HEAD);
    }

    /**
     * 打开相册
     */
    public void getPhoto() {
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setDataAndType(MediaStore.Images.Media.INTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CoreConstant.UPLOAD_PICTURE_HEAD);
    }

    private void updateURIAndPath() {
        String fileDir = FileUtil.getSDCardPath() + "DCIM/Camera/";
        FileUtil.checkDir(fileDir);
        uri = Uri.fromFile(new File(fileDir, DateFormat.format("yyyy-MM-dd-hh-mm-ss", new Date()) + ".jpg"));
        photoPath = uri.toString().replace("file://", "");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {
            case CoreConstant.UPLOAD_PICTURE_TAKE_HEAD:// 头像拍照返回
                cropImageUri(uri, 500, 500, CoreConstant.UPLOAD_PICTURE_CROP);
                break;
            case CoreConstant.UPLOAD_PICTURE_HEAD:// 头像从手机相册选择
                cropImageUri(data.getData(), 500, 500, CoreConstant.UPLOAD_PICTURE_CROP);
                break;
            case CoreConstant.UPLOAD_PICTURE_CROP://裁剪返回
                if (uri.toString().startsWith("file")) {
                    getPath();
                } else if (uri.toString().startsWith("content")) {
                    getUriToPath(data);
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void getUriToPath(Intent data) {
        if (data == null) {
            return;
        }
        Uri uri = data.getData();
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                try {
                    photoPath = cursor.getString(column_index);
                    uploadPhotoPath = ImageUtil.compressionImage(photoPath);
                    if (photoUrl != null) {
                        photoUrl.getUploadPhotoPath(uploadPhotoPath);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
        }
    }

    private void getPath() {
        try {
            uploadPhotoPath = ImageUtil.compressionImage(photoPath);
            NiceLogUtil.d("------->uploadPhotoPath=" + uploadPhotoPath);
            if (photoUrl != null) {
                photoUrl.getUploadPhotoPath(uploadPhotoPath);
            }
        } catch (Exception e) {
            NiceLogUtil.i("---------->exception=" + e.toString());
        }
    }


    /**
     * 获取上传图片的回调函数
     */
    public interface ITakePhotoUrl {
        void getUploadPhotoPath(String uploadPhotoPath);
    }


    private void cropImageUri(Uri uri, int outputX, int outputY, int requestCode) {

        Intent intent = new Intent("com.android.camera.action.CROP");

        intent.setDataAndType(uri, "image/*");

        intent.putExtra("crop", "true");

        intent.putExtra("aspectX", 1);

        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", outputX);

        intent.putExtra("outputY", outputY);

        intent.putExtra("scale", true);

        updateURIAndPath();

        intent.putExtra(MediaStore.EXTRA_OUTPUT, this.uri);

        intent.putExtra("return-data", false);

        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());

        intent.putExtra("noFaceDetection", true); // no face detection

        startActivityForResult(intent, requestCode);

    }



	/*------------------------------------------------相机的相关方法（end）---------------------------------------------------------------------*/


    /**
     * @return
     * @author caibing.zhang
     * @createdate 2015年3月21日 上午11:55:49
     * @Description: 获取版本号
     */
    private int getVersionCode() {
        try {
            // 获取packagemanager的实例
            PackageManager packageManager = getPackageManager();
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            PackageInfo packInfo = packageManager.getPackageInfo(
                    this.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * 打开软键盘
     */
    public void openKeyboard() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }, 100);
    }

    /**
     * 拷贝到剪贴板
     *
     * @param content 需要拷贝的内容
     * @description 2015年12月31日 12:14:58
     * @author sufun
     */
    public void doCopyToPaste(String content) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        clipboardManager.setText(content);
    }
}
