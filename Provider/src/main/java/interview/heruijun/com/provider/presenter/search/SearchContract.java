package interview.heruijun.com.provider.presenter.search;

import com.heruijun.baselibrary.presenter.BaseContract;

import java.util.List;

import interview.heruijun.com.provider.model.api.gank.Gank;

/**
 * Created by heruijun on 2018/3/1.
 */

public class SearchContract {

    public interface Presenter extends BaseContract.Presenter {
        void search(int pageNo);
    }

    public interface GankView extends BaseContract.View<SearchContract.Presenter> {
        void onSearchDone(List<Gank> ganks);
    }

}
