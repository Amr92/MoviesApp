package com.example.moviesapp.Models;

import java.util.List;

public class MoviesSearchModel {

    public int page;
    public int total_results;
    public int total_pages;
    public List<ResultsBean> results;

    public MoviesSearchModel(int page, int total_results, int total_pages, List<ResultsBean> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public static class ResultsBean {

        public double popularity;
        public int vote_count;
        public boolean video;
        public String poster_path;
        public int id;
        public boolean adult;
        public String backdrop_path;
        public String original_language;
        public String original_title;
        public String title;
        public double vote_average;
        public String overview;
        public String release_date;
        public List<Integer> genre_ids;

        public ResultsBean(double popularity, int vote_count, boolean video, String poster_path, int id, boolean adult, String backdrop_path, String original_language, String original_title, String title, double vote_average, String overview, String release_date, List<Integer> genre_ids) {
            this.popularity = popularity;
            this.vote_count = vote_count;
            this.video = video;
            this.poster_path = poster_path;
            this.id = id;
            this.adult = adult;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.original_title = original_title;
            this.title = title;
            this.vote_average = vote_average;
            this.overview = overview;
            this.release_date = release_date;
            this.genre_ids = genre_ids;
        }

        public double getPopularity() {
            return popularity;
        }

        public int getVote_count() {
            return vote_count;
        }

        public boolean isVideo() {
            return video;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public int getId() {
            return id;
        }

        public boolean isAdult() {
            return adult;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public String getOriginal_title() {
            return original_title;
        }

        public String getTitle() {
            return title;
        }

        public double getVote_average() {
            return vote_average;
        }

        public String getOverview() {
            return overview;
        }

        public String getRelease_date() {
            return release_date;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }
    }
}
