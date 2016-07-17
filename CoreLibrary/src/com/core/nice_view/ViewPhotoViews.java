package com.core.nice_view;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.R;
import com.core.activity.AbstractCoreActivity;
import com.core.nice_view.photoview.HackyViewPager;
import com.core.nice_view.photoview.PhotoView;
import com.core.nice_view.photoview.PhotoViewAttacher;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2014-608 on 15/3/17.
 */
public class ViewPhotoViews extends LinearLayout implements PhotoViewAttacher.OnPhotoTapListener {

    private HackyViewPager viewPager;
    private CirPageIndicator circlePageIndicator;
    private AbstractCoreActivity activity;

    private OnClickDismissDialogListener listener;


    public interface OnClickDismissDialogListener {
        public void onDismssDialog();
    }

    public void register(OnClickDismissDialogListener listener) {
        this.listener = listener;
    }

    public ViewPhotoViews(Context context) {
        super(context);
        initView(context);
    }

    public ViewPhotoViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        activity = (AbstractCoreActivity) context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_viewpaer_dialog, this, true);
        this.viewPager = (HackyViewPager) findViewById(R.id.viewPager);
        this.circlePageIndicator = (CirPageIndicator) findViewById(R.id.cirPageIndicator);
    }

    public void setData(List<String> images) {
        try {
            this.viewPager.setAdapter(new BaseViewPagerAdapter(images));
            viewPager.getAdapter().notifyDataSetChanged();
            circlePageIndicator.setViewPager(viewPager);
            circlePageIndicator.setAutoScroll(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void show(int index) {
        viewPager.setCurrentItem(index);
    }

    public void dismiss() {
        if (listener != null) {
            listener.onDismssDialog();
        }
    }

    public int getVisible() {
        return getVisibility();
    }

    @Override
    public void onPhotoTap(View view, float x, float y) {
        dismiss();
    }

    public class BaseViewPagerAdapter extends PagerAdapter {

        private List<String> lists;

        public void setData(List<String> lists) {
            this.lists = lists;
        }

        public BaseViewPagerAdapter(List<String> lists) {
            this.lists = lists;
        }

        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = View.inflate(activity, R.layout.goods_pics_preview_item, null);
            PhotoView photoView = (PhotoView) view.findViewById(R.id.img_view);
            photoView.SFSetImageUrl(lists.get(position));
            photoView.setOnPhotoTapListener(ViewPhotoViews.this);
            container.addView(view);
            return view;
        }
    }
}
