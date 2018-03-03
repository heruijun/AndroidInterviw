package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.github.chrisbanes.photoview.PhotoView;
import com.heruijun.baselibrary.fragment.LazyFragment;
import com.heruijun.baselibrary.util.glide.GlideApp;

import java.io.IOException;
import java.io.InputStream;

import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.androidinterview.activity.InterviewActivity;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by heruijun on 2018/2/18.
 */

public class ImageViewFragment extends LazyFragment {

    private ProgressBar mProgessBar;
    private PhotoView mImage;
    private boolean isPrepared;
    private String currentImg;
    private InterviewActivity interviewActivity;

    static ImageViewFragment newInstance(String imgUrl) {
        ImageViewFragment imageViewFragment = new ImageViewFragment();
        Bundle b = new Bundle();
        b.putString("imgUrl", imgUrl);
        imageViewFragment.setArguments(b);
        return imageViewFragment;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);

        interviewActivity = (InterviewActivity) getActivity();
        mProgessBar = root.findViewById(R.id.progressBar);
        mImage = root.findViewById(R.id.image);
        currentImg = getArguments().getString("imgUrl");

        isPrepared = true;

        lazyLoad();

        if (interviewActivity.getImageSet() != null && interviewActivity.getImageSet().size() > 0) {
            for (String img : interviewActivity.getImageSet()) {
                if (img.equals(currentImg)) {
                    mProgessBar.setVisibility(View.GONE);
                    GlideApp.with(getActivity()).load(currentImg)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            //.skipMemoryCache(true)
                            //.miniThumb(1000)
                            //.centerCrop()
                            //.transition(withCrossFade())
                            .into(mImage);
                }
            }
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_imageview;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void lazyLoad() {
        if (!isPrepared || !isVisible) {
            return;
        }

        boolean hasCached = false;

        if (interviewActivity.getImageSet() != null && interviewActivity.getImageSet().size() > 0) {
            for (String img : interviewActivity.getImageSet()) {
                if (img.equals(currentImg)) {
                    hasCached = true;
                    break;
                }
            }
        }

        if (hasCached) {
            return;
        }

        // 开始缓存
        mProgessBar.setVisibility(View.VISIBLE);
        final ProgressListener progressListener = new ProgressListener() {
            @Override
            public void update(long bytesRead, long contentLength) {
                // int progress = (int) ((100 * bytesRead) / contentLength);
                Log.e("文件长度", bytesRead + " / " + contentLength);
                // Enable if you want to see the progress with logcat
                // Log.v(LOG_TAG, "Progress: " + progress + "%");
                // mProgessBar.setProgress(progress);
                if (bytesRead == contentLength) {
                    Log.e("文件长度", "加载完成");
                    ((InterviewActivity) getActivity()).cacheImg(currentImg);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mProgessBar.setVisibility(View.GONE);
                        }
                    });
                }
            }
        };

        GlideApp.get(getActivity()).getRegistry()
                .replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(new OkHttpClient.Builder()
                        .addNetworkInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Request request = chain.request();

//            logger.info(String.format("Sending request %s on %s%n%s",
//                    request.url(), chain.connection(), request.headers()));

                                Response originalResponse = chain.proceed(request);
                                return originalResponse.newBuilder()
                                        .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                                        .build();
                            }
                        })
                        .build()));

        GlideApp.with(getActivity()).load(currentImg)
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                //.skipMemoryCache(true)
                //.miniThumb(1000)
                //.centerCrop()
                //.transition(withCrossFade())
                .into(mImage);
    }

    private static class ProgressResponseBody extends ResponseBody {

        private final ResponseBody responseBody;
        private final ProgressListener progressListener;
        private BufferedSource bufferedSource;

        public ProgressResponseBody(ResponseBody responseBody, ProgressListener progressListener) {
            this.responseBody = responseBody;
            this.progressListener = progressListener;
        }

        @Override
        public MediaType contentType() {
            return responseBody.contentType();
        }

        @Override
        public long contentLength() {
            return responseBody.contentLength();
        }

        @Override
        public BufferedSource source() {
            if (bufferedSource == null) {
                bufferedSource = Okio.buffer(source(responseBody.source()));
            }
            return bufferedSource;
        }

        private Source source(Source source) {
            return new ForwardingSource(source) {
                long totalBytesRead = 0L;

                @Override
                public long read(Buffer sink, long byteCount) throws IOException {
                    long bytesRead = super.read(sink, byteCount);
                    // read() returns the number of bytes read, or -1 if this source is exhausted.
                    totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                    progressListener.update(totalBytesRead, responseBody.contentLength());
                    return bytesRead;
                }
            };
        }
    }

    interface ProgressListener {
        void update(long bytesRead, long contentLength);
    }
}
