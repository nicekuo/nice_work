package com.core.util.file;

import android.content.Context;
import android.os.Build;
import android.os.Environment;

import com.core.CoreApplication;
import com.core.util.constants.CoreConstant;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author miaoxin.ye
 * @createdate 2013-12-15 下午6:18:21
 * @Description: 文件工具类
 */
public class FileUtil {

    /**
     * @param filePath
     * @return
     * @Description: 检查目录是否存在, 不存在则创建
     */
    public static boolean checkDir(String filePath) {
        File f = new File(filePath);
        if (!f.exists()) {
            return f.mkdirs();
        }
        return true;
    }

    /**
     * @return
     * @Description: 判断SD卡是否存在
     */
    public static boolean isExistSD() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡路径
     */
    public static String getSDCardPath() {
        if (isExistSD()) {
            return Environment.getExternalStorageDirectory().toString() + "/";
        }
        return null;
    }

    /**
     * @param context
     * @return
     * @Description: 获取当前应用SD卡缓存目录
     */
    public static String getSDCacheDir(Context context) {
        //api大于8的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            //目录为/mnt/sdcard/Android/data/com.mt.mtpp/cache
            return context.getExternalCacheDir().getPath();
        }
        String cacheDir = "/Android/data/" + context.getPackageName() + "/cache";
        return Environment.getExternalStorageDirectory().getPath() + cacheDir;
    }

    /**
     * @param type
     * @return
     * @Description: 获取缓存文件目录
     */
    public static String getCacheFileDir(int type) {
        if (CoreConstant.CACHE_DIR_SD == type) {
            //缓存在SD卡中
            return CoreApplication.FILE_DIR;
        } else {
            //缓存在SYSTEM文件中
            return CoreApplication.CACHE_DIR_SYSTEM;
        }
    }

    /**
     * @param sourceFile
     * @param targetFile
     * @throws IOException
     * @Description: 复制文件
     */
    public static void copyFile(String sourceFile, String targetFile) throws IOException {
        BufferedInputStream inBuff = null;
        BufferedOutputStream outBuff = null;
        try {
            inBuff = new BufferedInputStream(new FileInputStream(sourceFile));
            outBuff = new BufferedOutputStream(new FileOutputStream(targetFile));
            // 缓冲数组
            byte[] b = new byte[1024 * 5];
            int len;
            while ((len = inBuff.read(b)) != -1) {
                outBuff.write(b, 0, len);
            }
            // 刷新此缓冲的输出流
            outBuff.flush();
        } finally {
            // 关闭流
            if (inBuff != null)
                inBuff.close();
            if (outBuff != null)
                outBuff.close();
        }
    }

    /**
     * @param path 文件夹绝对路径
     * @return
     * @author caibing.zhang
     * @createdate 2013-11-14 下午8:46:55
     * @Description: 删除指定文件夹下所有文件
     */
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                boolean b = temp.delete();
                if (b) {
                    System.out.println("删除成功");
                } else {
                    System.out.println("删除失败：" + temp);
                }
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

    /**
     * @param folderPath 文件夹绝对路径
     * @author caibing.zhang
     * @createdate 2013-11-14 下午8:46:10
     * @Description: 删除文件, 包括文件夹
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param filename
     * @return
     * @author caibing.zhang
     * @createdate 2013-11-22 下午2:28:45
     * @Description: Java文件操作 获取文件扩展名
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * @param path
     * @return
     * @author miaoxin.ye
     * @createdate 2014-1-23 上午11:04:52
     * @Description: 删除文件
     */
    public static boolean deleteFile(String path) {
        File f = new File(path);
        if (f.exists()) {
            return f.delete();
        }
        return false;
    }

    /**
     * @param file
     * @return
     * @author caibing.zhang
     * @createdate 2015年3月7日 下午5:44:18
     * @Description: 获得某个文件的大小
     */
    public static double getDirSize(File file) {
        //判断文件是否存在     
        if (file.exists()) {
            //如果是目录则递归计算其内容的总大小    
            if (file.isDirectory()) {
                File[] children = file.listFiles();
                double size = 0;
                for (File f : children)
                    size += getDirSize(f);
                return size;
            } else {//如果是文件则直接返回其大小,以“兆”为单位   
                double size = (double) file.length() / 1024 / 1024;
                return size;
            }
        } else {
            System.out.println("文件或者文件夹不存在，请检查路径是否正确！");
            return 0.0;
        }
    }

}
