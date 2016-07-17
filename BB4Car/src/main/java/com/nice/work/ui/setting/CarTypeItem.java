package com.nice.work.ui.setting;

import com.core.bean.BaseBean;

/**
 * Created by sufun_job on 2016/1/21.
 *
 * 汽车小分类
 */
public class CarTypeItem extends BaseBean{

    /**
     * 汽车名称
     */
    private String car_name="";
    /**
     * 汽车的logo地址
     */
    private String car_logo_url="";

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    /**
     * 所在的分组情况
     */
    private String group_name="A";

    public String getCar_name() {
        return car_name;
    }

    public void setCar_name(String car_name) {
        this.car_name = car_name;
    }

    public String getCar_logo_url() {
        return car_logo_url;
    }

    public void setCar_logo_url(String car_logo_url) {
        this.car_logo_url = car_logo_url;
    }

    public String getCar_id() {
        return car_id;
    }

    public void setCar_id(String car_id) {
        this.car_id = car_id;
    }

    /**
     * 汽车的编号id
     */
    private String car_id="";
}
