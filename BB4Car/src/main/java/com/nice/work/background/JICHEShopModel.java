package com.nice.work.background;

import com.core.bean.BaseBean;

import java.util.List;

/**
 * Created by nice on 16/4/19.
 */
public class JICHEShopModel extends BaseBean {

    /**
     * lon : 110
     * shop_id : 067a894600b440b8abd64fbd746b7cb4
     * shops_pic : {"id":"f3a2235314454433866bddce541265a2","thumbnailProductImagePath":"http://7xspo7.com2.z0.glb.qiniucdn.com/upload/image/201604/f3a2235314454433866bddce541265a2_thumbnail.jpg","smallProductImagePath":"http://7xspo7.com2.z0.glb.qiniucdn.com/upload/image/201604/f3a2235314454433866bddce541265a2_small.jpg","bigProductImagePath":"http://7xspo7.com2.z0.glb.qiniucdn.com/upload/image/201604/f3a2235314454433866bddce541265a2_big.jpg","sourceProductImagePath":"http://7xspo7.com2.z0.glb.qiniucdn.com/upload/image/201604/f3a2235314454433866bddce541265a2.png"}
     * address : 百子湾1号院2楼无界空间
     * tel : 13911150991
     * name : 北京纳能旗舰店
     * lat : 39.9
     */

    private double lon;
    private String shop_id;

    public List<String> getShopsImageList_str() {
        return shopsImageList_str;
    }

    public void setShopsImageList_str(List<String> shopsImageList_str) {
        this.shopsImageList_str = shopsImageList_str;
    }

    private List<String> shopsImageList_str;
    private String address;
    private String tel;
    private String name;
    private double lat;

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

}
