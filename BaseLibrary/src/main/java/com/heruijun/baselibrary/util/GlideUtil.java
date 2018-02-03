package com.heruijun.baselibrary.util;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by heruijun on 2018/2/1.
 */

public class GlideUtil {

    public static void loadImage(Context context, String imagUri, ImageView imageView) {
        GlideApp.with(context).load(imagUri)
//                .diskCacheStrategy(DiskCacheStrategy.NONE)
//                .skipMemoryCache(true)
                .into(imageView);
    }

    public static void clearCache(final Context context) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {
                Glide.get(context.getApplicationContext()).clearDiskCache();
                return null;
            }
        };
        Glide.get(context).clearMemory();
    }
}
