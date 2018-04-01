package interview.heruijun.com.androidinterview.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.heruijun.baselibrary.fragment.PresenterFragment;
import com.heruijun.baselibrary.recycler.DividerItemDecoration;
import com.heruijun.baselibrary.recycler.RecyclerAdapter;
import com.heruijun.baselibrary.widget.EmptyView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import interview.heruijun.com.androidinterview.R;
import interview.heruijun.com.provider.model.api.gank.Gank;
import interview.heruijun.com.provider.presenter.search.SearchContract;
import interview.heruijun.com.provider.presenter.search.SearchGankPresenter;

/**
 * Created by heruijun on 2018/2/18.
 */

public class DataListExampleFragment extends PresenterFragment<SearchContract.Presenter>
        implements SearchContract.GankView {

    @BindView(R.id.empty)
    EmptyView mEmptyView;

    @BindView(R.id.refreshLayout)
    RefreshLayout mRefreshLayout;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerView;

    private RecyclerAdapter<Gank> mAdapter;
    private int pageNo = 1;

    public static DataListExampleFragment newInstance(String text) {
        DataListExampleFragment fileManagerFragment = new DataListExampleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fileManagerFragment.setArguments(bundle);
        return fileManagerFragment;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.fragment_filemanager;
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mEmptyView.bind(mRecyclerView);
        setPlaceHolderView(mEmptyView);

        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                pageNo = 1;
                search(pageNo);
                refreshlayout.finishRefresh();
            }
        });
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                pageNo++;
                search(pageNo);
                refreshlayout.finishLoadMore();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter = new RecyclerAdapter<Gank>() {
            @Override
            protected ViewHolder<Gank> onCreateViewHolder(View root, int viewType) {
                return new DataListExampleFragment.ViewHolder(root);
            }

            @Override
            protected int getItemViewType(int position, Gank gank) {
                if (gank.getImages() == null) {
                    return R.layout.list_gank_item;
                } else {
                    return R.layout.list_gank_item_images;
                }
            }
        });
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
    }

    @Override
    protected void initData() {
        super.initData();
        search(pageNo);
    }

    public void search(int pageNo) {
        mPresenter.search(pageNo);
    }

    @Override
    public void onSearchDone(List<Gank> ganks) {
        if (pageNo == 1) {
            mAdapter.replace(ganks);
        } else {
            mAdapter.add(ganks);
        }
        mEmptyView.triggerOkOrEmpty(mAdapter.getItems().size() > 0);
    }

    @Override
    protected SearchContract.Presenter initPresenter() {
        return new SearchGankPresenter(this);
    }

    class ViewHolder extends RecyclerAdapter.ViewHolder<Gank> {

        @BindView(R.id.desc)
        TextView mTitle;

        @BindView(R.id.type)
        TextView mType;

        @BindView(R.id.author)
        TextView mAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        protected void onBind(Gank gank) {
            mTitle.setText(gank.getDesc());
            mType.setText("类型：" + gank.getType());
            if (!TextUtils.isEmpty(gank.getWho())) {
                mAuthor.setVisibility(View.VISIBLE);
            } else {
                mAuthor.setVisibility(View.GONE);
            }
            mAuthor.setText(gank.getWho());
        }
    }
}
