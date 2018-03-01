package interview.heruijun.com.provider.data.helper;

import java.util.List;

import interview.heruijun.com.provider.R;
import interview.heruijun.com.provider.data.DataSource;
import interview.heruijun.com.provider.model.api.RspModel;
import interview.heruijun.com.provider.model.api.gank.Gank;
import interview.heruijun.com.provider.net.Factory;
import interview.heruijun.com.provider.net.Network;
import interview.heruijun.com.provider.net.RemoteService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by heruijun on 2018/3/1.
 */

public class GankHelper {

    public static Call<RspModel<List<Gank>>> searchGank(int pageNo, final DataSource.Callback<List<Gank>> callback) {
        RemoteService service = Network.remote();
        Call<RspModel<List<Gank>>> call = service.searchGank(pageNo);
        call.enqueue(new Callback<RspModel<List<Gank>>>() {
            @Override
            public void onResponse(Call<RspModel<List<Gank>>> call, Response<RspModel<List<Gank>>> response) {
                RspModel<List<Gank>> rspModel = response.body();
                if (rspModel != null && !rspModel.isError()) {
                    callback.onDataLoaded(rspModel.getResults());
                } else {
                    Factory.decodeRspCode(rspModel, callback);
                }
            }

            @Override
            public void onFailure(Call<RspModel<List<Gank>>> call, Throwable t) {
                callback.onDataNotAvailable(R.string.data_network_error);
            }
        });
        return call;
    }
}
