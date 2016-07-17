package com.nice.work.ui.main;

import android.support.v4.widget.SwipeRefreshLayout;

import com.nice.work.R;
import com.nice.work.core.AbstractFragment;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import cn.finalteam.loadingviewfinal.ListViewFinal;
import cn.finalteam.loadingviewfinal.OnLoadMoreListener;
import cn.finalteam.loadingviewfinal.SwipeRefreshLayoutFinal;


/**
 * Created by sufun_job on 2016/2/16.
 *
 * @description 商家列表主界面
 */
@EFragment(R.layout.home_shop_list_layout)
public class ShopListFragment extends AbstractFragment implements SwipeRefreshLayout.OnRefreshListener, OnLoadMoreListener {


    @ViewById(R.id.refresh_layout)
    SwipeRefreshLayoutFinal refresh_layout;

    @ViewById(R.id.rv_games)
    ListViewFinal rv_games;


    @AfterViews
    void init() {
        refresh_layout.setColorSchemeColors(getResources().getColor(R.color.blue));
        refresh_layout.setOnRefreshListener(this);
        rv_games.setOnLoadMoreListener(this);

    }

    @Override
    public void onRefresh() {
        curPage = 1;
//        getDatas();
    }

    @Override
    public void loadMore() {
        curPage++;
//        getDatas();
    }


}
