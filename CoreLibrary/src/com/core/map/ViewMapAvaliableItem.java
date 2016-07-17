package com.core.map;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.core.R;

/**
 * Created by nice on 16/4/22.
 */
public class ViewMapAvaliableItem extends LinearLayout {

    private ImageView image;
    private TextView name;
    private Activity activity;
    private OnClickOneAvaliableMapListener listener;

    public interface OnClickOneAvaliableMapListener {
        public void onClickMap();
    }

    public ViewMapAvaliableItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ViewMapAvaliableItem(Context context) {
        super(context);
        initView(context);
    }


    public void register(OnClickOneAvaliableMapListener listener) {
        this.listener = listener;
    }

    private void initView(Context context) {
        activity = (Activity) context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_map_avaliable_item, this, true);
        image = (ImageView) findViewById(R.id.image);
        name = (TextView) findViewById(R.id.name);
    }

    public void setData(final MapModel mapModel, final MapDataModel dataModel) {
        image.setImageResource(mapModel.getMap_image_resource());
        name.setText(mapModel.getMap_name());
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                if (MapUtil.kBaiduMap.equals(mapModel.getKey())) {
                    MapUtil.baiduMap(dataModel, activity);
                }

                if (MapUtil.kGaodeMap.equals(mapModel.getKey())) {
                    MapUtil.gaode(dataModel, activity);
                }
                if (listener != null) {
                    listener.onClickMap();
                }
            }
        });
    }
}
