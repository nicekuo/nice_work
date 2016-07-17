package com.core.nice_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.core.R;
import com.core.widget.image.SFImageView;

import java.util.ArrayList;
import java.util.List;


public class ViewBannerAutoScroll extends FixValueFrameLayoutForScaleHeight implements ViewPagerChangCallback {
    public ViewPagerAutoScroll bannerImages;
    private List<String> models;
    private HeaderAdapter adapter;
    private Context activity;
    private ArrayList<SFImageView> viewList;
    private ArrayList<Integer> indexList = new ArrayList<>();
    private BannerCirPageIndicator indicator;

    private OnClickOneImageListener listener;

    private ImageView.ScaleType scaleType;


    public interface OnClickOneImageListener {
        public void onClickOne(int index);
    }

    public void register(OnClickOneImageListener listener) {
        this.listener = listener;
    }

    public ViewBannerAutoScroll(Context context) {
        super(context);
        initView(context, null);
    }

    public ViewBannerAutoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        float ratio = 1.0f;
        if (attrs != null) {
            TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewBannerForScaleHeight);
            ratio = array.getFloat(R.styleable.ViewBannerForScaleHeight_ratio_banner, 1.0f);
            array.recycle();
        }
        this.activity = context;
        setRatio(ratio);
        LayoutInflater.from(context).inflate(R.layout.layout_home_banner, this);
        bannerImages = (ViewPagerAutoScroll) findViewById(R.id.bannerImages);
        indicator = (BannerCirPageIndicator) findViewById(R.id.cirPageIndicator);
    }

    public void setData(List<String> models) {
        this.models = models;
        if (this.models != null && this.models.size() > 0) {
            initViewList();
            adapter = new HeaderAdapter();
            bannerImages.setAdapter(adapter);
            bannerImages.setSpeed(4000);
            bannerImages.setSmoothScroll(false);
            bannerImages.setMethod(ViewPagerAutoScroll.METHOD_RIGHT);
            bannerImages.startScroll();
            indicator.setViewPager(bannerImages);
            indicator.setChangeViewCallback(this);
            indicator.setAutoScroll(true);
            bannerImages.setCurrentItem(1);
        }
    }

    public void setScaleType(ImageView.ScaleType scaleType){
        this.scaleType = scaleType;
    }

    public void onStart() {
        bannerImages.startScroll();
    }

    public void onStop() {
        bannerImages.stopScroll();
    }

    @Override
    public void onPageSelected(int pageNum) {
        if (viewList != null && viewList.size() > 1) {
            if (pageNum == 0) {
                bannerImages.setCurrentItem(viewList.size() - 2, false);
            } else if (pageNum == viewList.size() - 1) {
                bannerImages.setCurrentItem(1, false);
            }
        }
    }

    private class HeaderAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            if (viewList == null) {
                return 0;
            } else {
                return viewList.size();
            }
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SFImageView imageView = viewList.get(position);
            if (!TextUtils.isEmpty(models.get(indexList.get(position)))) {
                imageView.SFSetImageUrl(models.get(indexList.get(position)));
            }
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    private void initViewList() {
        if (viewList == null) {
            viewList = new ArrayList<>();
        }
        viewList.clear();
        for (int i = 0; i < models.size(); i++) {
            SFImageView imageview = getImageViewItem(i);
            if (models.size() > 1 && i == 0) {
                viewList.add(getImageViewItem(models.size() - 1));
                indexList.add(models.size() - 1);
            }
            viewList.add(imageview);
            indexList.add(i);
            if (models.size() > 1 && i == models.size() - 1) {
                viewList.add(getImageViewItem(0));
                indexList.add(0);
            }
        }
    }

    private SFImageView getImageViewItem(final int index) {
        SFImageView imageview = new SFImageView(activity);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        imageview.setLayoutParams(params);
        if (scaleType!=null){
            imageview.setScaleType(scaleType);
        }
        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onClickOne(index);
                }
            }
        });
        return imageview;
    }
}
