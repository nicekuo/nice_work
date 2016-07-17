package com.core.map;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.core.R;

import java.util.List;

/**
 * Created by nice on 16/4/22.
 */
public class ViewAvaliableMapList extends LinearLayout {

    private LinearLayout container;
    private MapDataModel dataModel;
    private OnScannMapListListener listListener;
    private Context context;

    public interface OnScannMapListListener {

        public void notFoundAvaliableMap();

        public void onClickOneMap();

        public void foundAvaliableMap();

    }

    public void register(OnScannMapListListener listListener) {
        this.listListener = listListener;
    }

    public ViewAvaliableMapList(Context context, MapDataModel dataModel) {
        super(context);
        this.dataModel = dataModel;
        initView(context);
    }

    public ViewAvaliableMapList(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater inflater = LayoutInflater.from(context);
        inflater.inflate(R.layout.view_map_avaliable_list, this, true);
        container = (LinearLayout) findViewById(R.id.container);
    }

    public void scannMap() {
        List<MapModel> maps = MapUtil.scranAvaliableMap();
        if (maps == null || maps.size() == 0) {
            if (listListener != null) {
                listListener.notFoundAvaliableMap();
            }
        } else {

            if (listListener != null) {
                listListener.foundAvaliableMap();
            }
            for (MapModel mapModel : maps) {
                ViewMapAvaliableItem item = new ViewMapAvaliableItem(context);
                item.setData(mapModel, dataModel);
                item.register(new ViewMapAvaliableItem.OnClickOneAvaliableMapListener() {
                    @Override
                    public void onClickMap() {
                        if (listListener != null) {
                            listListener.onClickOneMap();
                        }
                    }
                });
                container.addView(item);
            }
        }
    }
}
