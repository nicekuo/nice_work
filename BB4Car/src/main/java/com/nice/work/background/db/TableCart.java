package com.nice.work.background.db;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sreay on 14-9-10.
 */
public class TableCart {

    public static String TableName = "nnjc_cart";
    public static String kColId = "clo_id";
    public static String kProductId = "product_id";
    public static String kCartDealInfo = "product_info";
    public static String kOtherInfo = "other_info";//以后备用字段
    private static final String[] COLUMNS_INFO = new String[]{kCartDealInfo};

    public static void create(SQLiteDatabase db) {
        db.execSQL("create table if not exists " + TableName +
                "(" + kColId + " integer primary key autoincrement," + kProductId + " text not null," + kCartDealInfo +
                " text not null," + kOtherInfo + " text);");
    }
//
//    public static void drop(SQLiteDatabase db) {
//        db.execSQL("drop table " + TableName);
//    }
//
//    public static void saveInfo(ShoppingCarModel skuModel) {
//
//        SQLiteDatabase db = JICHEApplication.getInstance().getDB();
//        if (db == null) {
//            return;
//        }
//
//        Intent intent = new Intent(ConfigValue.kProductCartCountBroadcast);
//        LocalBroadcastManager.getInstance(JICHEApplication.getInstance()).sendBroadcast(intent);
//
//        if (addHasCreateModelCount(skuModel)) {
//            return;
//        }
//        ContentValues values = new ContentValues();
//        values.put(kCartDealInfo, JSonUtil.getIntence().gsonToStr(skuModel));
//        values.put(kProductId, skuModel.getGoods_id());
//        db.insert(TableName, kColId, values);
//
//    }
//
//    private static boolean addHasCreateModelCount(ShoppingCarModel skuModel) {
//        SQLiteDatabase db = JICHEApplication.getInstance().getDB();
//        if (db == null) {
//            return true;
//        }
//        ShoppingCarModel model = null;
//        String selection = kProductId + "='" + skuModel.getGoods_id() + "'";
//        Cursor cursor = db.query(TableName, COLUMNS_INFO, selection, null, null, null, null);
//        if (cursor.moveToNext()) {
//            model = JSonUtil.getIntence().fromJsonWithNoException(cursor.getString(0), ShoppingCarModel.class);
//        }
//        if (model == null) {
//            cursor.close();
//            return false;
//        } else {
//            skuModel.setNum(skuModel.getNum() + model.getNum());
//            updateInfoByID(skuModel);
//            cursor.close();
//            return true;
//        }
//    }
//
//    public static void updateInfoByID(ShoppingCarModel skuModel) {
//        SQLiteDatabase db = JICHEApplication.getInstance().getDB();
//        if (db == null) {
//            return;
//        }
//        ContentValues values = new ContentValues();
//        values.put(kCartDealInfo, JSonUtil.getIntence().gsonToStr(skuModel));
//        db.update(TableName, values, kProductId + "=?", new String[]{skuModel.getGoods_id() + ""});
//        Intent intent = new Intent(ConfigValue.kProductCartCountBroadcast);
//        LocalBroadcastManager.getInstance(JICHEApplication.getInstance()).sendBroadcast(intent);
//    }
//
//    public static void dropInfoByID(String sku_id) {
//        SQLiteDatabase db = JICHEApplication.getInstance().getDB();
//        if (db == null) {
//            return;
//        }
//        db.delete(TableName, kProductId + "=?", new String[]{sku_id + ""});
//        Intent intent = new Intent(ConfigValue.kProductCartCountBroadcast);
//        LocalBroadcastManager.getInstance(JICHEApplication.getInstance()).sendBroadcast(intent);
//    }
//
//    public static ArrayList<ShoppingCarModel> getSkuModel() {
//        ArrayList<ShoppingCarModel> models = new ArrayList<ShoppingCarModel>();
//        SQLiteDatabase db = JICHEApplication.getInstance().getDB();
//        if (db == null) {
//            return null;
//        }
//        String[] cols = new String[]{kCartDealInfo};
//        if (!db.isOpen()) {
//            return null;
//        }
//        Cursor cursor = db.query(TableName, cols, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            int infoIndex = cursor.getColumnIndex(kCartDealInfo);
//            String info = cursor.getString(infoIndex);
//            ShoppingCarModel skuModel = JSonUtil.getIntence().fromJsonWithNoException(info, ShoppingCarModel.class);
//            models.add(0, skuModel);
//        }
//        cursor.close();
//        return models;
//    }
}
