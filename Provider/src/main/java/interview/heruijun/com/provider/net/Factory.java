package interview.heruijun.com.provider.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by heruijun on 2018/3/1.
 */
public class Factory {
    private final static Factory instance;
    private final Executor defaultExecutor;
    private final Gson defaultGson;

    static {
        instance = new Factory();
    }

    private Factory() {
        defaultExecutor = Executors.newFixedThreadPool(4);
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
        defaultGson = gsonBuilder.create();
    }

    public static void runOnAsync(Runnable runnable) {
        instance.defaultExecutor.execute(runnable);
    }

    public static Gson getGson() {
        return instance.defaultGson;
    }
}
