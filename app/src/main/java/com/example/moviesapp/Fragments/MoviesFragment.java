package com.example.moviesapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.moviesapp.Adapters.PopularMoviesAdapter;
import com.example.moviesapp.Adapters.TopRatedMoviesAdapter;
import com.example.moviesapp.Adapters.SlidePagerAdapter;
import com.example.moviesapp.Classes.PaginationScrollListener;
import com.example.moviesapp.Models.NowPlayingMoviesModel;
import com.example.moviesapp.Models.PopularMoviesModel;
import com.example.moviesapp.Models.TopRatedMoviesModel;
import com.example.moviesapp.R;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cz.msebera.android.httpclient.Header;

public class MoviesFragment extends Fragment {

    private ViewPager sliderPager;
    private RecyclerView recPopularMovies;
    private RecyclerView recTopRatedMovies;
    private PopularMoviesAdapter adapter;
    private TopRatedMoviesAdapter topRatedMoviesAdapter;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager linearLayoutManager2;
    private TabLayout indicator;
    private AsyncHttpClient client;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private List<NowPlayingMoviesModel.ResultsBean> nowPlayingMoviesResults = new ArrayList<>();
    private List<PopularMoviesModel.ResultsBean> popularMoviesResults = new ArrayList<>();
    private List<TopRatedMoviesModel.ResultsBean> topRatedMoviesResults = new ArrayList<>();
    private ProgressBar loadingBar;
    private ProgressBar bar;
    private static int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 10;
    private int CURRENT_PAGE = PAGE_START;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);

        sliderPager = view.findViewById(R.id.slider_pager);
        indicator = view.findViewById(R.id.indicator);
        recPopularMovies = view.findViewById(R.id.popular_movies_rv);
        linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recPopularMovies.setLayoutManager(linearLayoutManager);

        recTopRatedMovies = view.findViewById(R.id.top_rated_movies_rv);
        linearLayoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recTopRatedMovies.setLayoutManager(linearLayoutManager2);

        loadingBar = view.findViewById(R.id.popular_progress_bar);
        bar = view.findViewById(R.id.top_rated_progress_bar);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new MoviesFragment.sliderTimer(), 3000, 4000);

        getNowPlayingMoviesData();
        paginationPopularMovies();
        getPopularMoviesData();
        paginationTopRatedMovies();
        getTopRatedMoviesData();

        return view;
    }

    private void getNowPlayingMoviesData() {

        String SERVER_URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + API_KEY;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<NowPlayingMoviesModel>() {
                }.getType();
                NowPlayingMoviesModel model = new Gson().fromJson(responseString, dataType);
                nowPlayingMoviesResults.addAll(model.getResults());
                SlidePagerAdapter pagerAdapter = new SlidePagerAdapter(getActivity(), nowPlayingMoviesResults);
                sliderPager.setAdapter(pagerAdapter);
                indicator.setupWithViewPager(sliderPager, true);
            }
        });
    }

    class sliderTimer extends TimerTask {

        @Override
        public void run() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderPager.getCurrentItem() < nowPlayingMoviesResults.size() - 1) {
                        sliderPager.setCurrentItem(sliderPager.getCurrentItem() + 1);
                    } else {
                        sliderPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    private void getPopularMoviesData() {

        String SERVER_URL = "https://api.themoviedb.org/3/movie/popular?api_key=" + API_KEY + "&page=" + PAGE_START;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PopularMoviesModel>() {
                }.getType();
                PopularMoviesModel model = new Gson().fromJson(responseString, dataType);
                popularMoviesResults.addAll(model.getResults());

                loadingBar.setVisibility(View.GONE);
                adapter = new PopularMoviesAdapter(popularMoviesResults, getActivity());
                recPopularMovies.setAdapter(adapter);
                if (CURRENT_PAGE <= TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }
        });
    }

    private void paginationPopularMovies() {
        recPopularMovies.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                //Increment page index to load the next one
                CURRENT_PAGE++;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadMore();
                    }
                }, 1000);
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

                Type dataType = new TypeToken<PopularMoviesModel>() {
                }.getType();
                PopularMoviesModel model = new Gson().fromJson(responseString, dataType);
                popularMoviesResults.addAll(model.getResults());
                adapter.removeLoadingFooter();
                isLoading = false;
                adapter = new PopularMoviesAdapter(popularMoviesResults, getActivity());
                recPopularMovies.setAdapter(adapter);

                if (CURRENT_PAGE != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }
        });

    }


    private void getTopRatedMoviesData() {

        String SERVER_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY + "&page=" + PAGE_START;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TopRatedMoviesModel>() {
                }.getType();
                TopRatedMoviesModel model = new Gson().fromJson(responseString, dataType);
                topRatedMoviesResults.addAll(model.getResults());
                bar.setVisibility(View.GONE);

                topRatedMoviesAdapter = new TopRatedMoviesAdapter(topRatedMoviesResults, getActivity());
                recTopRatedMovies.setAdapter(topRatedMoviesAdapter);

                if (CURRENT_PAGE <= TOTAL_PAGES) topRatedMoviesAdapter.addLoadingFooter();
                else isLastPage = true;

            }
        });
    }

    private void paginationTopRatedMovies() {

        recTopRatedMovies.addOnScrollListener(new PaginationScrollListener(linearLayoutManager2) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                //Increment page index to load the next one
                CURRENT_PAGE++;

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNext();
                    }
                }, 1000);
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

    private void loadNext() {

        String SERVER_URL = "https://api.themoviedb.org/3/movie/top_rated?api_key=" + API_KEY + "&page=" + CURRENT_PAGE;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<TopRatedMoviesModel>() {
                }.getType();
                TopRatedMoviesModel model = new Gson().fromJson(responseString, dataType);
                topRatedMoviesResults.addAll(model.getResults());
                topRatedMoviesAdapter.removeLoadingFooter();
                isLoading = false;
                topRatedMoviesAdapter = new TopRatedMoviesAdapter(topRatedMoviesResults, getActivity());
                recTopRatedMovies.setAdapter(topRatedMoviesAdapter);

                if (CURRENT_PAGE != TOTAL_PAGES) topRatedMoviesAdapter.addLoadingFooter();
                else isLastPage = true;
            }
        });
    }

}