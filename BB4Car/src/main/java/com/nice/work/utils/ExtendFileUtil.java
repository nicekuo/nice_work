package com.nice.work.utils;

import android.content.Context;

import com.core.util.NiceLogUtil;
import com.core.util.file.FileUtil;

import java.io.InputStream;

/**
 * Created by sufun_job on 2016/2/24.
 */
public class ExtendFileUtil extends FileUtil {

    /**
     * @param dirName 在assets下的一文件名    如datas/tt.txt
     * @param name    文件名
     * @param context
     * @return
     * @description 取得assets里面的某个文件，并且读取里面的内容
     */
    public static String getAssetContentByName(String dirName, String name, Context context) {

        try {

            String fileFullPath = "";
            if ("".equals(dirName)) {
                fileFullPath =name;
            } else {
                fileFullPath = dirName+"/"+name;
            }

            InputStream in=context.getAssets().open(fileFullPath);
            int size=in.available();
            byte[]  buffer=new byte[size];
            in.read(buffer);
            in.close();
            return new String(buffer);
        }
        catch (Exception ex)
        {
            NiceLogUtil.D("   not exits " + dirName + "/" + name);
        }

        return "";
    }
}
