package com.naneng.jiche.background;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.core.util.image.BitmapUtil;
import com.naneng.jiche.R;
import com.naneng.jiche.core.AbstractActivity;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 * Created by sreay on 14-11-4.
 */
public class ShareUtil {

//    //分享到朋友圈(文字，图片)
//    public void shareToWeChat(String title, String content, ImageView imageView, String shareUrl, boolean isTimeline) {
//        if (TextUtils.isEmpty(title)) {
//            title = "JICHE";
//        }
//        Bitmap bitmap = ((BitmapDrawable) (imageView.getDrawable())).getBitmap();
//        Bitmap thumbBmp = BitmapUtil.rotateAndScale(bitmap, 0, 100, false);
//        shareToWeChat(title, content, shareUrl, thumbBmp, isTimeline);
//    }
//
//    //分享到朋友圈(文字，图片)
//    public void shareToWeChat(String title, String content, String shareUrl, int resId, boolean isTimeline) {
//        if (TextUtils.isEmpty(title)) {
//            title = "JICHE";
//        }
//        Bitmap bitmap = BitmapFactory.decodeResource(JICHEApplication.getInstance().getResources(), resId);
//        shareToWeChat(title, content, shareUrl, bitmap, isTimeline);
//    }

    public void shareToWeChat(AbstractActivity activity, String title, String content, String shareUrl, String pic_url, boolean isTimeline) {
        downloadImage(activity, title, content, shareUrl, pic_url, isTimeline);

    }

    private void shareToWeChat(String title, String content, String shareUrl, Bitmap thumBmp, boolean isTimeline) {


//        Bitmap thumBmp = null;
//        if (bitmap != null) {
//            thumBmp = BitmapUtil.rotateAndScale(bitmap, 0, 100, false);
//        } else {
//            thumBmp = BitmapFactory.decodeResource(JICHEApplication.getInstance().getResources(), R.drawable.ic_launcher);
//        }
//
        if (TextUtils.isEmpty(title)) {
            title = "JICHE";
        }

        WXWebpageObject webpage = new WXWebpageObject();
        webpage.webpageUrl = shareUrl;

        WXMediaMessage msg = new WXMediaMessage(webpage);
        msg.title = title;
        msg.description = content;
        msg.setThumbImage(thumBmp);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        if (isTimeline) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        IWXAPI api  = JICHEApplication.getInstance().getWxApi();
        if (api!=null){
            api.sendReq(req);
        }
    }

    private void downloadImage(final AbstractActivity activity, final String title, final String content, final String shareUrl, String pic_url, final boolean isTimeline) {
        activity.showLoadDialog(true);
        Glide.with(JICHEApplication.getInstance()).load(pic_url).asBitmap().fitCenter().into(new SimpleTarget<Bitmap>(100, 100) {
            @Override
            public void onResourceReady(Bitmap bitmap, GlideAnimation<? super Bitmap> glideAnimation) {
                activity.dissmissWaitingDialog();
                shareToWeChat(title, content, shareUrl, bitmap, isTimeline);
            }
        });
    }


    //分享到朋友圈(图片)
    public void shareToWeChat(Bitmap bitmap) {
        WXImageObject imageObject = new WXImageObject(bitmap);

        WXMediaMessage msg = new WXMediaMessage();
        msg.mediaObject = imageObject;

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = msg;
        req.scene = SendMessageToWX.Req.WXSceneTimeline;
        JICHEApplication.getInstance().getWxApi().sendReq(req);
    }

}
