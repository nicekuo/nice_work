package com.core.map;

import android.app.Activity;
import android.content.Intent;

import com.core.R;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nice on 16/4/22.
 */
public class MapUtil {

    public static final String kBaiduMap = "com.baidu.BaiduMap";
    public static final String kGaodeMap = "com.autonavi.minimap";


    public static List<MapModel> scranAvaliableMap() {
        List<MapModel> mapModels = new ArrayList<>();
        if (isInstallPackage(kBaiduMap)) {
            MapModel mapModel = new MapModel();
            mapModel.setMap_name("百度地图");
            mapModel.setMap_image_resource(R.drawable.baidu_image);
            mapModel.setKey(kBaiduMap);
            mapModels.add(mapModel);
        }

        if (isInstallPackage(kGaodeMap)) {
            MapModel mapModel = new MapModel();
            mapModel.setMap_name("高德地图");
            mapModel.setMap_image_resource(R.drawable.gaode_image);
            mapModel.setKey(kGaodeMap);
            mapModels.add(mapModel);
        }

        return mapModels;
    }


    public static boolean isInstallPackage(String packageName) {
        return new File("/data/data/" + packageName).exists();
    }

    private static double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }

    private static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }

    public static void gaode(MapDataModel mapDataModel, Activity activity) {
        double[] gd_lat_lon = bdToGaoDe(mapDataModel.getsLat(), mapDataModel.getsLon());
        double[] gd_lat_lonTwo = bdToGaoDe(mapDataModel.getdLat(), mapDataModel.getdLon());
        Intent intent = new Intent("android.intent.action.VIEW", android.net.Uri.parse("androidamap://route?sourceApplication=softname&slat=" + gd_lat_lonTwo[1] + "&slon=" + gd_lat_lonTwo[0] + "&sname=" + mapDataModel.getsAddress() + "&dlat=" + gd_lat_lon[1] + "&dlon=" + gd_lat_lon[0] + "&dname=" + mapDataModel.getdAddress() + "&dev=0&m=0&t=1"));
        intent.setPackage("com.autonavi.minimap");
        activity.startActivity(intent);
    }

    public static void baiduMap(MapDataModel mapDataModel, Activity activity) {
        Intent intent = null;
        try {
            intent = Intent.getIntent("intent://map/direction?origin=latlng:" + mapDataModel.getdLat() + "," + mapDataModel.getdLon() + "|name:" + mapDataModel.getsAddress() + "&destination=latlng:" + mapDataModel.getsLat() + "," + mapDataModel.getsLon() + "|name:" + mapDataModel.getdAddress() + "&mode=driving&src=yourCompanyName|yourAppName#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
            activity.startActivity(intent);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
