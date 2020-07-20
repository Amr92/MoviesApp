package com.example.moviesapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviesapp.Adapters.PopularMoviesAdapter;
import com.example.moviesapp.Adapters.PopularTVAdapter;
import com.example.moviesapp.Adapters.TopRatedTVAdapter;
import com.example.moviesapp.Classes.PaginationScrollListener;
import com.example.moviesapp.Models.PopularTVSeries;
import com.example.moviesapp.Models.TopRatedTVSeries;
import com.example.moviesapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TVSeriesFragment extends Fragment {

    private RecyclerView popularTvRecyclerView;
    private RecyclerView topRatedTVRec;
    private PopularTVAdapter adapter;
    private TopRatedTVAdapter tRatedAdapter;
    private LinearLayoutManager layoutManager;
    private LinearLayoutManager linearLayoutManager;
    private AsyncHttpClient client;
    public static final String API_KEY = "56461395297741c1c0452f322385501c";
    private List<PopularTVSeries.ResultsBean> popularTvShowsResults = new ArrayList<>();
    private List<TopRatedTVSeries.ResultsBean> topRatedTvShowsResults = new ArrayList<>();
    private static int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 10;
    private int CURRENT_PAGE = PAGE_START;
    private ProgressBar loadingBar;
    private ProgressBar bar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_t_v_series, container, false);

        popularTvRecyclerView = view.findViewById(R.id.popular_tv_rv);
        layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        popularTvRecyclerView.setLayoutManager(layoutManager);

        topRatedTVRec = view.findViewById(R.id.top_rated_tv_rv);
        linearLayoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        topRatedTVRec.setLayoutManager(linearLayoutManager);

        loadingBar = view.findViewById(R.id.popular_tv_progress_bar);
        bar = view.findViewById(R.id.top_rated_tv_progress_bar);

        getPopularTvShows();
        paginationPopularTv();

        getTopRatedTvShows();
        paginationTopRatedTv();
        return view;
    }

    private void getPopularTvShows() {
        String SERVER_URL = "https://api.themoviedb.org/3/tv/popular?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularTVSeries>(){}.getType();
                PopularTVSeries model = new Gson().fromJson(responseString,dataType);
                popularTvShowsResults.addAll(model.getResults());
                loadingBar.setVisibility(View.GONE);
                adapter = new PopularTVAdapter(popularTvShowsResults,getActivity());
                popularTvRecyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
                if (CURRENT_PAGE <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }
        });
    }

    private void paginationPopularTv(){
        popularTvRecyclerView.addOnScrollListener(new PaginationScrollListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                //Increment page index to load the next one
                CURRENT_PAGE ++;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                    }
                },1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private void loadMore() {

        String SERVER_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&page=" + CURRENT_PAGE;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularTVSeries>(){}.getType();
                PopularTVSeries model = new Gson().fromJson(responseString,dataType);
                popularTvShowsResults.addAll(model.getResults());
                adapter.removeLoadingFooter();
                isLoading = false;
                adapter = new PopularTVAdapter(popularTvShowsResults,getActivity());
                popularTvRecyclerView.setAdapter(adapter);

                if (CURRENT_PAGE != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }
        });

    }

    private void getTopRatedTvShows() {
        String SERVER_URL = "https://api.themoviedb.org/3/tv/top_rated?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TopRatedTVSeries>(){}.getType();
                TopRatedTVSeries model = new Gson().fromJson(responseString,dataType);
                topRatedTvShowsResults.addAll(model.getResults());
                bar.setVisibility(View.GONE);
                tRatedAdapter = new TopRatedTVAdapter(topRatedTvShowsResults,getActivity());
                topRatedTVRec.setAdapter(tRatedAdapter);
                tRatedAdapter.notifyDataSetChanged();
                if (CURRENT_PAGE <= TOTAL_PAGES) tRatedAdapter.addLoadingFooter();
                else isLastPage = true;
            }
        });
    }

    private void paginationTopRatedTv(){
        topRatedTVRec.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                //Increment page index to load the next one
                CURRENT_PAGE ++;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMoreItems();
                    }
                },1000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });
    }

    private void loadMoreItems() {

        String SERVER_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY + "&page=" + CURRENT_PAGE;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TopRatedTVSeries>(){}.getType();
                TopRatedTVSeries model = new Gson().fromJson(responseString,dataType);
                topRatedTvShowsResults.addAll(model.getResults());
                tRatedAdapter.removeLoadingFooter();
                isLoading = false;
                tRatedAdapter = new TopRatedTVAdapter(topRatedTvShowsResults,getActivity());
                topRatedTVRec.setAdapter(tRatedAdapter);

                if (CURRENT_PAGE != TOTAL_PAGES) tRatedAdapter.addLoadingFooter();
                else isLastPage = true;
            }
        });

    }
}
