package com.core.map;

import com.core.bean.BaseBean;

/**
 * Created by nice on 16/4/22.
 */
public class MapDataModel  extends BaseBean{


    private double sLat;
    private double sLon;
    private double dLat;
    private double dLon;
    private String sAddress;
    private String dAddress;

    public String getsAddress() {
        return sAddress;
    }

    public void setsAddress(String sAddress) {
        this.sAddress = sAddress;
    }

    public String getdAddress() {
        return dAddress;
    }

    public void setdAddress(String dAddress) {
        this.dAddress = dAddress;
    }

    public double getsLat() {
        return sLat;
    }

    public void setsLat(double sLat) {
        this.sLat = sLat;
    }

    public double getsLon() {
        return sLon;
    }

    public void setsLon(double sLon) {
        this.sLon = sLon;
    }

    public double getdLat() {
        return dLat;
    }

    public void setdLat(double dLat) {
        this.dLat = dLat;
    }

    public double getdLon() {
        return dLon;
    }

    public void setdLon(double dLon) {
        this.dLon = dLon;
    }
}
