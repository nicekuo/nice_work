package com.naneng.jiche.test;

import com.lidroid.xutils.util.LogUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by ${nice} on ${2016年04月29日14:09:09}.
 */
public class DataJsonCreator {

    public static void initJson() {
        JSONArray data = new JSONArray();
        try {
            for (int i = 1980; i < 2017; i++) {
                JSONObject item = new JSONObject();
                data.put(item);
                item.put("id", String.valueOf(i - 1980 + 1));
                item.put("name",String.valueOf(i));
                JSONArray child = new JSONArray();
                item.put("child",child);
                for (int k = 1;k<13;k++){
                    JSONObject childItem = new JSONObject();
                    childItem.put("id", String.valueOf((i - 1980 + 1)*10+k));
                    childItem.put("name",String.valueOf(k));
                    child.put(childItem);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        LogUtils.d(data.toString());
    }
}
