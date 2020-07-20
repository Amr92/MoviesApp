package com.example.moviesapp.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Adapters.MoreCelebsAdapter;
import com.example.moviesapp.Adapters.PopularMoviesAdapter;
import com.example.moviesapp.Classes.PaginationScrollListener;
import com.example.moviesapp.Models.PersonsSearchModel;
import com.example.moviesapp.Models.PopularCelebsModel;
import com.example.moviesapp.Models.PopularMoviesModel;
import com.example.moviesapp.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class MoreCelebs extends AppCompatActivity {

    @BindView(R.id.back_arrow_celebs)
    AppCompatImageView backArrowCelebs;
    private RecyclerView moreCelebsRv;
    private MoreCelebsAdapter adapter;
    private LinearLayoutManager layoutManager;
    private final String API_KEY = "56461395297741c1c0452f322385501c";
    private AsyncHttpClient client;
    private List<PersonsSearchModel.ResultsBean> moreCelebsList = new ArrayList<>();
    private static int PAGE_START = 1;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 10;
    private int CURRENT_PAGE = PAGE_START;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_celebs);
        ButterKnife.bind(this);

        moreCelebsRv = findViewById(R.id.more_celebs_rv);
        layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        moreCelebsRv.setLayoutManager(layoutManager);

        adapter = new MoreCelebsAdapter(this);

        paginationPopularCelebs();

        backArrowCelebs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private void getCelebsData() {

        String SERVER_URL = "https://api.themoviedb.org/3/person/popular?api_key=" + API_KEY + "&page=" + PAGE_START;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PersonsSearchModel>(){}.getType();
                PersonsSearchModel model = new Gson().fromJson(responseString,dataType);
                moreCelebsList.addAll(model.getResults());
                adapter.addAll(moreCelebsList);
                moreCelebsRv.setAdapter(adapter);
                adapter.notifyDataSetChanged();

            }
        });
    }

    private void paginationPopularCelebs(){
        moreCelebsRv.addOnScrollListener(new PaginationScrollListener(layoutManager) {
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

        getCelebsData();
    }

    private void loadMore() {

        String SERVER_URL = "https://api.themoviedb.org/3/person/popular?api_key=" + API_KEY + "&page=" + CURRENT_PAGE;
        client = new AsyncHttpClient();
        client.get(SERVER_URL, null, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                Type dataType = new TypeToken<PersonsSearchModel>(){}.getType();
                PersonsSearchModel model = new Gson().fromJson(responseString,dataType);
                moreCelebsList.addAll(model.getResults());
                adapter.removeLoadingFooter();
                isLoading = false;
                adapter.addAll(moreCelebsList);
                moreCelebsRv.setAdapter(adapter);

                if (CURRENT_PAGE != TOTAL_PAGES) adapter.addLoadingFooter();
                else isLastPage = true;
            }
        });

    }

}
