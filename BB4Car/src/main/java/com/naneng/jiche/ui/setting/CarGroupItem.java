package com.naneng.jiche.ui.setting;

import com.core.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sufun_job on 2016/1/21.
 */
public class CarGroupItem extends BaseBean {
    /**
     * 汽车的组的名称
     */
    private String group_name="";
    /**
     * 汔车所在组的id
     */
    private String group_id="";

    public List<CarTypeItem> getCars() {
        return cars;
    }

    public void setCars(List<CarTypeItem> cars) {
        this.cars = cars;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    /**

     * 分组id下面的所有子类的汽车
     */
    private List<CarTypeItem> cars=new ArrayList<CarTypeItem>();
}
