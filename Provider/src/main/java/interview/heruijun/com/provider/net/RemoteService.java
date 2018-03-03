package interview.heruijun.com.provider.net;

import java.util.List;

import interview.heruijun.com.provider.model.api.RspModel;
import interview.heruijun.com.provider.model.api.gank.Gank;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by heruijun on 2018/3/1.
 */

public interface RemoteService {

    @GET("Android/30/{pageNo}")
    Call<RspModel<List<Gank>>> searchGank(@Path(value = "pageNo", encoded = true) int pageNo);

}
