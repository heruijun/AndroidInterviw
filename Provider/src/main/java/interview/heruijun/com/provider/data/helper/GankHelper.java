package interview.heruijun.com.provider.data.helper;

import java.util.List;

import interview.heruijun.com.provider.R;
import interview.heruijun.com.provider.data.DataSource;
import interview.heruijun.com.provider.model.api.RspModel;
import interview.heruijun.com.provider.model.api.gank.Gank;
import interview.heruijun.com.provider.net.Factory;
import interview.heruijun.com.provider.net.Network;
import interview.heruijun.com.provider.net.RemoteService;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by heruijun on 2018/3/1.
 */

public class GankHelper {

    public static void searchGank(int pageNo, final DataSource.Callback<List<Gank>> callback) {
        RemoteService service = Network.remote();
        Observable<RspModel<List<Gank>>> observable = service.searchGank(pageNo);
        observable.compose(new Network.NetworkTransformer<RspModel<List<Gank>>>())
                .subscribe(new Observer<RspModel<List<Gank>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RspModel<List<Gank>> rspModel) {
                        if (rspModel != null && !rspModel.isError()) {
                            callback.onDataLoaded(rspModel.getResults());
                        } else {
                            Factory.decodeRspCode(rspModel, callback);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable(R.string.data_network_error);
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
