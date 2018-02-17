package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.github.chrisbanes.photoview.PhotoView;
import com.heruijun.baselibrary.fragment.BaseFragment;
import com.heruijun.baselibrary.util.glide.GlideApp;

import java.io.IOException;
import java.io.InputStream;

import interview.heruijun.com.androidinterview.R;
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

public class ImageViewFragment extends BaseFragment {

    private ProgressBar mProgessBar;
    private PhotoView mImage;

    static ImageViewFragment newInstance(String imgUrl) {
        ImageViewFragment imageViewFragment = new ImageViewFragment();
        Bundle b = new Bundle();
        b.putString("imgUrl", imgUrl);
        imageViewFragment.setArguments(b);
        return imageViewFragment;
    }

    class LoggingInterceptor implements Interceptor {
        @Override
        public Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();

            long t1 = System.nanoTime();
//            logger.info(String.format("Sending request %s on %s%n%s",
//                    request.url(), chain.connection(), request.headers()));

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mProgessBar.setVisibility(View.VISIBLE);
                }
            });

            Response response = chain.proceed(request);

            long t2 = System.nanoTime();
//            logger.info(String.format("Received response for %s in %.1fms%n%s",
//                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            return response;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_imageview, container, false);

        mProgessBar = view.findViewById(R.id.progressBar);
        mImage = view.findViewById(R.id.image);

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
                        .addNetworkInterceptor(new LoggingInterceptor())
                        .addNetworkInterceptor(new Interceptor() {
                            @Override
                            public Response intercept(Chain chain) throws IOException {
                                Response originalResponse = chain.proceed(chain.request());
                                return originalResponse.newBuilder()
                                        .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                                        .build();
                            }
                        })
                        .build()));

        GlideApp.with(getActivity()).load(getArguments().getString("imgUrl"))
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                //.miniThumb(1000)
                //.centerCrop()
                //.transition(withCrossFade())
                .into(mImage);
        // GlideUtil.loadImage(getContext(), TEST_IMG, mImage);

        return view;
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
