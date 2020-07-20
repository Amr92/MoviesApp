package com.example.moviesapp.Models;

import java.util.List;

public class TopRatedTVSeries {

    public int page;
    public int total_results;
    public int total_pages;
    public List<ResultsBean> results;

    public TopRatedTVSeries(int page, int total_results, int total_pages, List<ResultsBean> results) {
        this.page = page;
        this.total_results = total_results;
        this.total_pages = total_pages;
        this.results = results;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {

        public String original_name;
        public String name;
        public double popularity;
        public int vote_count;
        public String first_air_date;
        public String backdrop_path;
        public String original_language;
        public int id;
        public double vote_average;
        public String overview;
        public String poster_path;
        public List<Integer> genre_ids;
        public List<String> origin_country;

        public ResultsBean() {
        }

        public ResultsBean(String original_name, String name, double popularity, int vote_count, String first_air_date, String backdrop_path, String original_language, int id, double vote_average, String overview, String poster_path, List<Integer> genre_ids, List<String> origin_country) {
            this.original_name = original_name;
            this.name = name;
            this.popularity = popularity;
            this.vote_count = vote_count;
            this.first_air_date = first_air_date;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.id = id;
            this.vote_average = vote_average;
            this.overview = overview;
            this.poster_path = poster_path;
            this.genre_ids = genre_ids;
            this.origin_country = origin_country;
        }

        public String getOriginal_name() {
            return original_name;
        }

        public void setOriginal_name(String original_name) {
            this.original_name = original_name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPopularity() {
            return popularity;
        }

        public void setPopularity(double popularity) {
            this.popularity = popularity;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public String getFirst_air_date() {
            return first_air_date;
        }

        public void setFirst_air_date(String first_air_date) {
            this.first_air_date = first_air_date;
        }

        public String getBackdrop_path() {
            return backdrop_path;
        }

        public void setBackdrop_path(String backdrop_path) {
            this.backdrop_path = backdrop_path;
        }

        public String getOriginal_language() {
            return original_language;
        }

        public void setOriginal_language(String original_language) {
            this.original_language = original_language;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getPoster_path() {
            return poster_path;
        }

        public void setPoster_path(String poster_path) {
            this.poster_path = poster_path;
        }

        public List<Integer> getGenre_ids() {
            return genre_ids;
        }

        public void setGenre_ids(List<Integer> genre_ids) {
            this.genre_ids = genre_ids;
        }

        public List<String> getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(List<String> origin_country) {
            this.origin_country = origin_country;
        }
    }
}
