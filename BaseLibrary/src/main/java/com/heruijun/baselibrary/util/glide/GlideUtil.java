package com.heruijun.baselibrary.util.glide;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.math.BigDecimal;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * Created by heruijun on 2018/2/1.
 */

public class GlideUtil {

    public static void loadImage(Context context, String imagUri, ImageView imageView) {
        GlideApp.with(context).load(imagUri)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//                .skipMemoryCache(true)
                //.miniThumb(1000)
                //.centerCrop()
                .transition(withCrossFade())
                .into(imageView);
    }

    public static void clearGlideCache(final Context context) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Glide.get(context.getApplicationContext()).clearDiskCache();
                return null;
            }
        };
        clearCacheMemory(context);
        cleanCatchDisk(context);
    }

    public static boolean cleanCatchDisk(Context context) {
        return deleteFolderFile(context.getApplicationContext().getCacheDir() + "/" + GlideCatchConfig.GLIDE_CARCH_DIR, true);
    }

    // 获取Glide磁盘缓存大小
    public static String getCacheSize(Context context) {
        String pathname = context.getApplicationContext().getCacheDir() + "/" + GlideCatchConfig.GLIDE_CARCH_DIR;
        // Log.e("pathname", pathname);
        try {
            return getFormatSize(getFolderSize(new File(pathname)));
        } catch (Exception e) {
            e.printStackTrace();
            return "获取失败";
        }
    }

    // 清除Glide内存缓存
    public static boolean clearCacheMemory(Context context) {
        try {
            if (Looper.myLooper() == Looper.getMainLooper()) { //只能在主线程执行
                Glide.get(context).clearMemory();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // 获取指定文件夹内所有文件大小的和
    private static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (File aFileList : fileList) {
                if (aFileList.isDirectory()) {
                    size = size + getFolderSize(aFileList);
                } else {
                    size = size + aFileList.length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    // 格式化单位
    private static String getFormatSize(double size) {
        if (size == 0) {
            return "0MB";
        }
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }
        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
        }
        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
        }
        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
    }

    // 按目录删除文件夹文件方法
    private static boolean deleteFolderFile(String filePath, boolean deleteThisPath) {
        try {
            File file = new File(filePath);
            if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (File file1 : files) {
                    deleteFolderFile(file1.getAbsolutePath(), true);
                }
            }
            if (deleteThisPath) {
                if (!file.isDirectory()) {
                    file.delete();
                } else {
                    if (file.listFiles().length == 0) {
                        file.delete();
                    }
                }
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
