package interview.heruijun.com.provider.net;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import interview.heruijun.com.provider.data.DataSource;
import interview.heruijun.com.provider.model.api.RspModel;

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

    /**
     * 用于错误状态码处理
     *
     * @param model
     * @param failedCallback
     */
    public static void decodeRspCode(RspModel model, DataSource.FailedCallback failedCallback) {
//        if (model == null)
//            return;
//        switch (model.getCode()) {
//            case RspModel.SUCCEED:
//                return;
//            case RspModel.ERROR_SERVICE:
//                decodeRspCode(R.string.data_rsp_error_service, failedCallback);
//                break;
//            default:
//                decodeRspCode(R.string.data_rsp_error_unknown, failedCallback);
//                break;
//        }
    }

    public static void runOnAsync(Runnable runnable) {
        instance.defaultExecutor.execute(runnable);
    }

    public static Gson getGson() {
        return instance.defaultGson;
    }
}
