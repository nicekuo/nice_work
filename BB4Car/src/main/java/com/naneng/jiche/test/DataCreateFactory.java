package com.naneng.jiche.test;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.core.bean.BaseBean;
import com.core.util.NiceLogUtil;
import com.naneng.jiche.R;
import com.naneng.jiche.ui.setting.CarTypeItem;
import com.naneng.jiche.utils.ExtendFileUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sufun_job on 2016/1/22.
 *
 * 测试数据创建工厂
 */
public class DataCreateFactory {

    public static List<CarTypeItem>  getCarDataSet(Context context)
    {
        List<CarTypeItem>  items=new ArrayList<CarTypeItem>();
        String [] str_cars=context.getResources().getStringArray(R.array.animals);
        for (int i=0;i<str_cars.length;i++)
        {
            CarTypeItem item=new CarTypeItem();
            item.setGroup_name(str_cars[i]);
            item.setCar_name(str_cars[i]+i);
            item.setCar_logo_url(str_cars[i]+i);
            items.add(item);
        }
        return items;
    }

    /**
     * 创建测试数据引用本地的数据文档    ExtendFileUtil.getAssetContentByName("datas", "service_shops.json",mContext);
     * @param mContext
     * @param dirName   所在assets文件夹下面文件夹名称
     * @param fileName  文件名称
     * @param clazz      反射出来的类型
     * @return
     */
    public static BaseBean getDatas(Context mContext,String dirName,String fileName, Class<? extends BaseBean> clazz)
    {
        String rawdata= ExtendFileUtil.getAssetContentByName(dirName, fileName,mContext);
        if (!"".equals(rawdata))
        {
            try {
                BaseBean data= JSON.parseObject(rawdata, clazz);
                return data;
            }catch (Exception ex)
            {
                NiceLogUtil.D("getDatas   convertExcepton---->" + ex.toString());
                return new BaseBean();
            }
        }
        else
        {
            return new BaseBean();
        }
    }
}
