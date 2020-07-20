package com.example.moviesapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.moviesapp.Models.PopularMoviesModel;

import java.util.ArrayList;
import java.util.List;

public class FavoriteDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "favorite.db";
    private static final int DATABASE_VERSION = 1;
    public static final String LOG_TAG = "FAVORITE";

    SQLiteOpenHelper dbHandler;
    SQLiteDatabase db;

    public FavoriteDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void open() {
        Log.i(LOG_TAG, "Database Opened");
        db = dbHandler.getWritableDatabase();

    }

    public void close() {
        Log.i(LOG_TAG, "Database Closed");
        dbHandler.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_TABLE = "CREATE TABLE " + FavoriteContract.FavoriteEntry.TABLE_NAME + " ("
                + FavoriteContract.FavoriteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID + " INTEGER, "
                + FavoriteContract.FavoriteEntry.COLUMN_TITLE + " TEXT, "
                + FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH + " TEXT" + ");";

        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + FavoriteContract.FavoriteEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);
    }


    public void addFavorite(PopularMoviesModel.ResultsBean model) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID, model.getId());
        contentValues.put(FavoriteContract.FavoriteEntry.COLUMN_TITLE , model.getTitle());
        contentValues.put(FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH, model.getPoster_path());

        db.insert(FavoriteContract.FavoriteEntry.TABLE_NAME, null, contentValues);
        db.close();
    }

    public void deleteFavorite(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(FavoriteContract.FavoriteEntry.TABLE_NAME, FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID + "=" + id, null);
        db.close();
    }

    public List<PopularMoviesModel.ResultsBean> getAllFavorite() {

        String[] columns = {FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID,
                FavoriteContract.FavoriteEntry.COLUMN_TITLE,
                FavoriteContract.FavoriteEntry.COLUMN_POSTER_PATH};

        List<PopularMoviesModel.ResultsBean> favoriteList = new ArrayList<>();
        String queryString = "SELECT * FROM " + FavoriteContract.FavoriteEntry.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(FavoriteContract.FavoriteEntry.TABLE_NAME,columns,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do {
                PopularMoviesModel.ResultsBean movie = new PopularMoviesModel.ResultsBean();
                movie.setId(cursor.getInt(0));
                movie.setTitle(cursor.getString(1));
                movie.setPoster_path(cursor.getString(2));

                favoriteList.add(movie);
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return favoriteList;

    }

    public void deleteMovies(ArrayList<PopularMoviesModel.ResultsBean> moviesList) {
        SQLiteDatabase db = getWritableDatabase();

        for (int i = 0; i < moviesList.size(); i++) {
            db.delete(FavoriteContract.FavoriteEntry.TABLE_NAME, FavoriteContract.FavoriteEntry.COLUMN_MOVIE_ID + " = ?", new String[]{String.valueOf(moviesList.get(i).getId())});
        }
        db.close();
    }
}


