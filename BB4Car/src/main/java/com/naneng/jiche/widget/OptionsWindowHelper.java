package com.naneng.jiche.widget;

import android.app.Activity;

import com.naneng.jiche.widget.pickerview.CharacterPickerView;
import com.naneng.jiche.widget.pickerview.CharacterPickerWindow;

import java.util.ArrayList;
import java.util.List;

/**
 * 地址选择器
 * @Author sufun
 *
 *
 * 用法:
 *      //初始化
        final CharacterPickerWindow window = OptionsWindowHelper.builder(MainActivity.this, new OptionsWindowHelper.OnOptionsSelectListener() {
        @Override
            public void onOptionsSelect(String province, String city) {
                Log.e("main", province + "," + city);
                }
            });
            // 弹出
            window.showAtLocation(v, Gravity.BOTTOM, 0, 0);
        }
 */
public class OptionsWindowHelper {

    private static List<String> options1Items = null;
    private static List<List<String>> options2Items = null;

    public interface OnOptionsSelectListener {
        void onOptionsSelect(String data1, String data2);
    }

    private OptionsWindowHelper() {
    }

    public static CharacterPickerWindow builder(Activity activity,List<String> data1,List<List<String>> data2, final OnOptionsSelectListener listener) {
        //选项选择器
        CharacterPickerWindow mOptions = new CharacterPickerWindow(activity);
        //初始化选项数据
        setPickerData(mOptions.getPickerView(),data1,data2);
        //设置默认选中的二级项目
        mOptions.setSelectOptions(0, 0);
        //监听确定选择按钮
        mOptions.setOnoptionsSelectListener(new CharacterPickerWindow.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                if (listener != null) {
                    String province = options1Items.get(options1);
                    String city = options2Items.get(options1).get(option2);
                    listener.onOptionsSelect(province, city);
                }
            }
        });
        return mOptions;
    }

    /**
     * 初始化选项数据
     */
    public static void setPickerData(CharacterPickerView view,List<String> data1,List<List<String>> data2) {
        if (options1Items == null) {
            options1Items = new ArrayList();
            options2Items = new ArrayList<>();
        }
        options1Items.clear();
        options2Items.clear();
        options2Items.addAll(data2);
        options1Items.addAll(data1);
        //二级联动效果
        view.setPicker(options1Items, options2Items);
    }
}
