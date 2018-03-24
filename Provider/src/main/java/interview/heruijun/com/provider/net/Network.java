package interview.heruijun.com.provider.net;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by heruijun on 2018/3/1.
 */
@SuppressWarnings("WeakerAccess")
public class Network {
    private final static Network instance;

    private OkHttpClient client;
    private Retrofit retrofit;

    static {
        instance = new Network();
    }

    private Network() {
    }

    public static OkHttpClient getClient() {
        if (instance.client == null) {
            synchronized (instance) {
                if (instance.client == null) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                    httpClient.addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Interceptor.Chain chain) throws IOException {
                            Request original = chain.request();
                            Request.Builder builder = original.newBuilder();
                            // 此处可以保存业务层token
                            builder.addHeader("Content-Type", "application/json");
                            Request request = builder.build();
                            return chain.proceed(request);
                        }
                    });
                    instance.client = httpClient.build();
                }
            }
        }
        return instance.client;
    }


    public static Retrofit getRetrofit() {
        if (instance.retrofit == null) {
            synchronized (instance) {
                if (instance.retrofit == null) {
                    OkHttpClient client = getClient();
                    String apiUrl = Common.Constance.API_URL;
                    Retrofit.Builder builder = new Retrofit.Builder()
                            .baseUrl(apiUrl)
                            .client(client)
                            .addConverterFactory(GsonConverterFactory.create(Factory.getGson()))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
                    instance.retrofit = builder.build();
                }
            }
        }
        return instance.retrofit;
    }

    public static <T> T with(Class<T> service) {
        return getRetrofit().create(service);
    }

    public static RemoteService remote() {
        return with(RemoteService.class);
    }

    public static final class NetworkTransformer<T> implements ObservableTransformer<T, T> {

        @Override
        public ObservableSource<T> apply(Observable<T> upstream) {
            // 订阅时在io线程，回来接受时在主线程
            return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        }
    }
}
