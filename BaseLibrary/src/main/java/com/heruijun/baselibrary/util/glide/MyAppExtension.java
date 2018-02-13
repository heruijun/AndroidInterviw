package com.heruijun.baselibrary.util.glide;

import com.bumptech.glide.annotation.GlideExtension;
import com.bumptech.glide.annotation.GlideOption;
import com.bumptech.glide.request.RequestOptions;

/**
 * Created by heruijun on 2018/2/10.
 */

@GlideExtension
public class MyAppExtension {
    // Size of mini thumb in pixels.
    // private static final int MINI_THUMB_SIZE = 100;

    // private static final RequestOptions DECODE_TYPE_GIF = decodeTypeOf(GifDrawable.class).lock();

    private MyAppExtension() {
    } // utility class

    @GlideOption
    public static void miniThumb(RequestOptions options, int size) {
        options
                .fitCenter()
                .override(size);
    }

//    @GlideType(GifDrawable.class)
//    public static void asGif(RequestBuilder<GifDrawable> requestBuilder) {
//        requestBuilder
//                .transition(new DrawableTransitionOptions())
//                .apply(DECODE_TYPE_GIF);
//    }
}