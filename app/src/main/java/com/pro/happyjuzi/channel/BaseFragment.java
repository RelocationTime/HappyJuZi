package com.pro.happyjuzi.channel;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pro.happyjuzi.R;
import com.pro.happyjuzi.adapter.ChannelAdapter;
import com.pro.happyjuzi.bean.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment implements
        IRecommendView, SwipeRefreshLayout.OnRefreshListener{


    public RecyclerView recycle_view;
    public RecomendPresenter presenter;
    public ChannelAdapter adapter;
    public ArrayList<BaseBean> arrayList;
    public static final int CAT_RECOMMEND = 0;
    public static final int CAT_GOSSIP = 1;
    public static final int CAT_VIDEO = 2;
    public static final int CAT_FASHION = 3;
    public static final int CAT_LIFE = 4;
    public SwipeRefreshLayout swipe_refresh;
    public View load_more;
    public int lastVisibleItem = 0;
    public LinearLayoutManager layout;

    public BaseFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_recommend, container, false);
        recycle_view = ((RecyclerView) view.findViewById(R.id.recycle_view));
        presenter = new RecomendPresenter(this,getContext());
        swipe_refresh = ((SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh));
        swipe_refresh.setOnRefreshListener(this);
        recycle_view.setHasFixedSize(true);
        swipe_refresh.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark,R.color.colorAccent);
        load_more = view.findViewById(R.id.footer_loadmore);
        layout = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recycle_view.setLayoutManager(layout);
        arrayList = new ArrayList<>();
        selectTypeRecommen();
        adapter = new ChannelAdapter(getContext(), arrayList);
        recycle_view.setAdapter(adapter);
        recycle_view.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && adapter.getItemCount() == (lastVisibleItem + 1)) {
                    load_more.setVisibility(View.VISIBLE);
                    loadMore();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem =  layout.findLastVisibleItemPosition();
            }
        });
        return view;
    }

    public abstract void selectTypeRecommen();
    @Override
    public void loadMore(ArrayList<BaseBean> list) {
        load_more.setVisibility(View.GONE);
        adapter.addAll(list);

    }

    public abstract void loadMore();
    @Override
    public void loadSucess() {
        swipe_refresh.setRefreshing(false);
        Toast.makeText(getContext(), "橘子君加载完成 ... ", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        swipe_refresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipe_refresh.setRefreshing(false);
    }

    @Override
    public void showRecommendList(List<BaseBean> list) {
        adapter.addAll(list);
        swipe_refresh.setRefreshing(false);
        load_more.setVisibility(View.GONE);
        loadSucess();
    }


    @Override
    public void showNoNet() {
        Toast.makeText(getContext(), "橘子君找不到网络", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        selectTypeRecommen();
    }
}
