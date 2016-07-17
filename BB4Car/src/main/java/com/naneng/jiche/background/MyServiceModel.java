package com.naneng.jiche.background;

import com.core.bean.BaseBean;

/**
 * Created by nice on 16/4/19.
 */
public class MyServiceModel extends BaseBean {

    private String servicestype_id;
    private String desc;
    private double price;
    private String shop_id;
    private String type_name;

    public String getServicestype_id() {
        return servicestype_id;
    }

    public void setServicestype_id(String servicestype_id) {
        this.servicestype_id = servicestype_id;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
