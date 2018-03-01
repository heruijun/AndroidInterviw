package interview.heruijun.com.provider.net;

import java.io.IOException;

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
                            .addConverterFactory(GsonConverterFactory.create(Factory.getGson()));
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
}
