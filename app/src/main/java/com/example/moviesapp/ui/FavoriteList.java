package com.example.moviesapp.ui;

import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.Adapters.PopularMoviesAdapter;
import com.example.moviesapp.Database.FavoriteDBHelper;
import com.example.moviesapp.Models.PopularMoviesModel;
import com.example.moviesapp.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteList extends AppCompatActivity {

    @BindView(R.id.favorite_rec_view)
    RecyclerView favoriteRecView;

    private FavoriteDBHelper favoriteDBHelper;
    private PopularMoviesAdapter adapter;
    private List<PopularMoviesModel.ResultsBean> moviesList = new ArrayList<>();
    private SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        ButterKnife.bind(this);

        favoriteDBHelper = new FavoriteDBHelper(FavoriteList.this);
        mDatabase = favoriteDBHelper.getWritableDatabase();
        moviesList = favoriteDBHelper.getAllFavorite();

        favoriteRecView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapter = new PopularMoviesAdapter(moviesList,this);
        favoriteRecView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    /*private void getFavorite() {

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void...params){
                moviesList.clear();
                moviesList.addAll(favoriteDBHelper.getAllFavorite());
                return null;
            }
            @Override
            protected void onPostExecute(Void aVoid){
                super.onPostExecute(aVoid);
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }*/

}