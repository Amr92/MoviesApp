package com.example.moviesapp.Models;

import java.util.List;

public class PersonsSearchModel {

    public int page;
    public int total_results;
    public int total_pages;
    public List<ResultsBean> results;

    public PersonsSearchModel(int page, int total_results, int total_pages, List<ResultsBean> results) {
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
        public String known_for_department;
        public int gender;
        public int id;
        public String profile_path;
        public boolean adult;
        public String name;
        public List<KnownForBean> known_for;

        public ResultsBean(double popularity, String known_for_department, int gender, int id, String profile_path, boolean adult, String name, List<KnownForBean> known_for) {
            this.popularity = popularity;
            this.known_for_department = known_for_department;
            this.gender = gender;
            this.id = id;
            this.profile_path = profile_path;
            this.adult = adult;
            this.name = name;
            this.known_for = known_for;
        }

        public ResultsBean() {

        }

        public double getPopularity() {
            return popularity;
        }

        public String getKnown_for_department() {
            return known_for_department;
        }

        public int getGender() {
            return gender;
        }

        public int getId() {
            return id;
        }

        public String getProfile_path() {
            return profile_path;
        }

        public boolean isAdult() {
            return adult;
        }

        public String getName() {
            return name;
        }

        public List<KnownForBean> getKnown_for() {
            return known_for;
        }

        public static class KnownForBean {

            public String release_date;
            public int id;
            public int vote_count;
            public boolean video;
            public String media_type;
            public double vote_average;
            public String title;
            public String original_title;
            public String original_language;
            public boolean adult;
            public String backdrop_path;
            public String overview;
            public String poster_path;
            public List<Integer> genre_ids;

            public KnownForBean(String release_date, int id, int vote_count, boolean video, String media_type, double vote_average, String title, String original_title, String original_language, boolean adult, String backdrop_path, String overview, String poster_path, List<Integer> genre_ids) {
                this.release_date = release_date;
                this.id = id;
                this.vote_count = vote_count;
                this.video = video;
                this.media_type = media_type;
                this.vote_average = vote_average;
                this.title = title;
                this.original_title = original_title;
                this.original_language = original_language;
                this.adult = adult;
                this.backdrop_path = backdrop_path;
                this.overview = overview;
                this.poster_path = poster_path;
                this.genre_ids = genre_ids;
            }

            public String getRelease_date() {
                return release_date;
            }

            public int getId() {
                return id;
            }

            public int getVote_count() {
                return vote_count;
            }

            public boolean isVideo() {
                return video;
            }

            public String getMedia_type() {
                return media_type;
            }

            public double getVote_average() {
                return vote_average;
            }

            public String getTitle() {
                return title;
            }

            public String getOriginal_title() {
                return original_title;
            }

            public String getOriginal_language() {
                return original_language;
            }

            public boolean isAdult() {
                return adult;
            }

            public String getBackdrop_path() {
                return backdrop_path;
            }

            public String getOverview() {
                return overview;
            }

            public String getPoster_path() {
                return poster_path;
            }

            public List<Integer> getGenre_ids() {
                return genre_ids;
            }
        }
    }
}
