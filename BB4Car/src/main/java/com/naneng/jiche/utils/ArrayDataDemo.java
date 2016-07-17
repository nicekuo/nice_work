package com.naneng.jiche.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author bao.longxiang
 * 测试用假数据
 */
public class ArrayDataDemo {

    private static final Map<String, List<String>> DATAs = new LinkedHashMap<>();

    private static void init() {
        if (!DATAs.isEmpty()) {
            return;
        }
        List<String> citys = new ArrayList<>();
        citys.add("吉林");
        citys.add("四平");
        citys.add("白城");
        citys.add("通话");
        citys.add("长春");
        citys.add("长白山");
        DATAs.put("吉林省", citys);
        citys = new ArrayList<>();
        citys.add("海尔滨");
        citys.add("齐齐哈尔");
        citys.add("鸡西市");
        citys.add("佳木斯市");
        citys.add("鹤岗市");
        citys.add("大庆市");
        DATAs.put("黑龙江", citys);
        citys = new ArrayList<>();
        citys.add("沈阳");
        citys.add("大连");
        citys.add("抚区");
        citys.add("本溪市");
        citys.add("丹东市");
        citys.add("东港市");
        DATAs.put("辽宁省", citys);
        citys = new ArrayList<>();
        citys.add("青岛");
        citys.add("济南");
        citys.add("日照");
        citys.add("临沂");
        citys.add("烟台");
        citys.add("胶州");
        DATAs.put("山东省", citys);
        citys = new ArrayList<>();
        citys.add("成都市");
        citys.add("绵阳市");
        citys.add("德阳市");
        citys.add("攀枝花市");
        citys.add("遂宁市");
        citys.add("南充市");
        DATAs.put("四川省", citys);
        citys = new ArrayList<>();
        citys.add("兰州");
        citys.add("天水");
        citys.add("白银");
        citys.add("平凉");
        citys.add("庆阳");
        citys.add("陇南");
        citys.add("定西");
        DATAs.put("甘肃省", citys);

    }


    public static Map<String, List<String>> getAll() {
        init();
        return new HashMap<>(DATAs);
    }

}
