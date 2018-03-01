package interview.heruijun.com.provider.presenter.search;

import com.heruijun.baselibrary.presenter.BasePresenter;

import java.util.List;

import interview.heruijun.com.provider.data.DataSource;
import interview.heruijun.com.provider.data.helper.GankHelper;
import interview.heruijun.com.provider.model.api.gank.Gank;
import retrofit2.Call;

/**
 * Created by heruijun on 2018/3/1.
 */

public class SearchGankPresenter extends BasePresenter<SearchContract.GankView>
        implements SearchContract.Presenter, DataSource.Callback<List<Gank>> {

    private Call searchCall;

    public SearchGankPresenter(SearchContract.GankView view) {
        super(view);
    }

    @Override
    public void search(int pageNo) {
        SearchContract.GankView gankView = getView();
        if (searchCall != null && searchCall.isExecuted()) {
            searchCall.cancel();
        }
        GankHelper.searchGank(pageNo, this);
    }

    @Override
    public void onDataLoaded(List<Gank> ganks) {
        SearchContract.GankView gankView = getView();
        if (gankView != null) {
            gankView.onSearchDone(ganks);
        }
    }

    @Override
    public void onDataNotAvailable(int strRes) {
        SearchContract.GankView view = getView();
        if (view != null) {
            view.showError(strRes);
        }
    }
}
