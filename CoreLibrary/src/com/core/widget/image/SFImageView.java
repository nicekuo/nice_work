package com.core.widget.image;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PaintFlagsDrawFilter;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.core.CoreApplication;
import com.core.R;

import java.io.File;

/**
 * @author sufun
 * @time 2015年4月21日 16:39:14
 * @describe 用于向用户展示图片的一个ImageView ，
 */
public class SFImageView extends ImageView {

//	private DisplayImageOptions option;

    private boolean needCornerOption = false;
    /**
     * 用于展示图片正在加时一一个效果
     */
    private View mloadingView = null;

    Context mContext;
    /**
     *
     */
    String img_url_key = "";

    public boolean isNeedCornerOption() {
        return needCornerOption;
    }

    public void setNeedCornerOption(boolean needCornerOption) {
        this.needCornerOption = needCornerOption;
        // 此处打开，会有相关的参数问题
        // initOption();
    }

    /**
     * @param loadingView
     */
    public void setLoadingView(View loadingView) {
        mloadingView = loadingView;
    }

    public SFImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TypedArray a = context.obtainStyledAttributes(attrs,
        // R.styleable.SFImageView_NeedCornerStyle);

        TypedArray arr = context.obtainStyledAttributes(attrs,
                R.styleable.myview_sf_imageview);
        needCornerOption = arr.getBoolean(
                R.styleable.myview_sf_imageview_NeedCornerStyle, false);

        // TODO Auto-generated constructor stub
        // this.setScaleType(ScaleType.FIT_XY);

        // 最后一定要使用这个方法。将设置好的a返回给StyledAttributes，后面可以重新使用。
        arr.recycle();
        mContext = context;

        //initGifLoadingView(context);
    }

    public SFImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        // this.setScaleType(ScaleType.FIT_XY);

        TypedArray arr = context.obtainStyledAttributes(attrs,
                R.styleable.myview_sf_imageview);
        needCornerOption = arr.getBoolean(
                R.styleable.myview_sf_imageview_NeedCornerStyle, false);
        // 最后一定要使用这个方法。将设置好的a返回给StyledAttributes，后面可以重新使用。
        arr.recycle();
        mContext = context;
    }

    public SFImageView(Context context) {
        super(context);
        // this.setScaleType(ScaleType.FIT_XY);
        // TODO Auto-generated constructor stub
        mContext = context;
    }

    /**
     * 设置图片所在的地址
     */
    public void SFSetTagUrl(String url) {
        if (url == null || "".equals(url)) {
            this.setBackgroundResource(R.drawable.default_icon);
            return;
        }

        if (url.equals(img_url_key)) {
            return;
        } else {
            img_url_key = url;
        }

    }

    /**
     * 使用默认的图片设置器
     *
     * @param url
     */
    public void SFSetDefaultImageUrl(final String url) {
        if (url == null || "".equals(url)) {
            //setImageResource(R.drawable.icon_nodata);
            return;
        }
        if (url != null && url.contains("http://") || url.contains("https://")) {
//			CoreApplication.getInstance().mPicasso.load(url+"")
//			.placeholder(R.drawable.wallpapermgr_thumb_default)
//			.error(R.drawable.icon_nodata).into(this);
            Glide.with(CoreApplication.getInstance())
                    .load(url)
                    .listener(new RequestListener<String, GlideDrawable>() {
                        @Override
                        public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                            return false;
                        }
                    })
                    .placeholder(R.drawable.default_icon)
                    .error(R.drawable.default_icon)
                    .into(this);
        } else {
            if (url.indexOf("/") == -1) {
                // Drawable的资源文件
                // ImageLoader.getInstance().displayImage("drawable://" +
                // url,this,option,listerner);
                Integer resId = 0;
                try {
                    resId = Integer.parseInt(url);
                } catch (Exception ex) {

                }
                Glide.with(CoreApplication.getInstance())
                        .load(resId)
                        .placeholder(R.drawable.default_icon)
                        .error(R.drawable.default_icon)
                        .into(this);

            } else {
                //加载本地的图片
                Glide.with(CoreApplication.getInstance())
                        .load(new File(url))
                        .placeholder(R.drawable.default_icon)
                        .error(R.drawable.default_icon)
                        .into(this);
            }
        }
    }

    /**
     * @param url
     * @author sufun 2015年4月22日 10:52:06 带有下载状态回调的图片下载器
     */
    public void SFSetImageUrl(final String url) {
//		this.setScaleType(ScaleType.CENTER_CROP);
        if (url == null || "".equals(url)) {
//            setImageResource(R.drawable.default_icon);
            return;
        }
        if (url != null && url.contains("http://") || url.contains("https://")) { //加载网络上面的图片
            Glide.with(CoreApplication.getInstance()).load(url).listener(new RequestListener<String, GlideDrawable>() {
                @Override
                public boolean onException(Exception e, String s, Target<GlideDrawable> target, boolean b) {
                    if (s == null) {

                    }
                    return false;
                }

                @Override
                public boolean onResourceReady(GlideDrawable glideDrawable, String s, Target<GlideDrawable> target, boolean b, boolean b1) {
                    if (glideDrawable == null) {

                    }

                    return false;
                }
            })
//					.placeholder(R.drawable.default_icon)
                    .error(R.drawable.default_icon)
                    .into(this);
        } else {
            if (url.indexOf("/") == -1) {
                // Drawable的资源文件
                // ImageLoader.getInstance().displayImage("drawable://" +
                // url,this,option,listerner);
                Integer resId = 0;
                try {
                    resId = Integer.parseInt(url);
                } catch (Exception ex) {

                }
                Glide.with(CoreApplication.getInstance())
                        .load(resId)
                        .placeholder(R.drawable.default_icon)
                        .error(R.drawable.default_icon)
                        .into(this);

            } else {
                //加载本地的图片
                Glide.with(CoreApplication.getInstance())
                        .load(new File(url))
                        .placeholder(R.drawable.default_icon)
                        .error(R.drawable.default_icon)
                        .into(this);
            }

        }
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        //消除因进行转动的变化而发生的锯齿效应
        PaintFlagsDrawFilter mSetfil = new PaintFlagsDrawFilter(0, Paint.FILTER_BITMAP_FLAG);
        canvas.setDrawFilter(mSetfil);
        super.onDraw(canvas);
    }

    @Override
    protected void onDetachedFromWindow() {
        // TODO Auto-generated method stub
        super.onDetachedFromWindow();
    }

    /**
     * 图片的加载回调
     *
     * @author sufun_job
     */
    public interface ISFCallBack {
        void OnSuccess();

        void OnFail();
    }
}
