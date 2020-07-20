package com.example.moviesapp.Database;

import android.provider.BaseColumns;

public class FavoriteContract {

    public FavoriteContract() {
    }

    public static final class FavoriteEntry implements BaseColumns{
        public static final String TABLE_NAME = "favorite";
        public static final String COLUMN_MOVIE_ID = "movieid";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_POSTER_PATH = "posterpath";
    }
}
