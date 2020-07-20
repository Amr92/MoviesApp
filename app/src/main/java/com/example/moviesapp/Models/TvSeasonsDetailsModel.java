package com.example.moviesapp.Models;

import java.util.List;

public class TvSeasonsDetailsModel {

    private String _id;
    private String air_date;
    private String name;
    private String overview;
    private int id;
    private String poster_path;
    private int season_number;
    private List<EpisodesBean> episodes;

    public TvSeasonsDetailsModel() {
    }

    public TvSeasonsDetailsModel(String _id, String air_date, String name, String overview, int id, String poster_path, int season_number, List<EpisodesBean> episodes) {
        this._id = _id;
        this.air_date = air_date;
        this.name = name;
        this.overview = overview;
        this.id = id;
        this.poster_path = poster_path;
        this.season_number = season_number;
        this.episodes = episodes;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAir_date() {
        return air_date;
    }

    public void setAir_date(String air_date) {
        this.air_date = air_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getSeason_number() {
        return season_number;
    }

    public void setSeason_number(int season_number) {
        this.season_number = season_number;
    }

    public List<EpisodesBean> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<EpisodesBean> episodes) {
        this.episodes = episodes;
    }

    public static class EpisodesBean {

        private String air_date;
        private int episode_number;
        private int id;
        private String name;
        private String overview;
        private String production_code;
        private int season_number;
        private int show_id;
        private String still_path;
        private double vote_average;
        private int vote_count;
        private List<CrewBean> crew;
        private List<GuestStarsBean> guest_stars;

        public EpisodesBean() {
        }

        public EpisodesBean(String air_date, int episode_number, int id, String name, String overview, String production_code, int season_number, int show_id, String still_path, double vote_average, int vote_count, List<CrewBean> crew, List<GuestStarsBean> guest_stars) {
            this.air_date = air_date;
            this.episode_number = episode_number;
            this.id = id;
            this.name = name;
            this.overview = overview;
            this.production_code = production_code;
            this.season_number = season_number;
            this.show_id = show_id;
            this.still_path = still_path;
            this.vote_average = vote_average;
            this.vote_count = vote_count;
            this.crew = crew;
            this.guest_stars = guest_stars;
        }

        public String getAir_date() {
            return air_date;
        }

        public void setAir_date(String air_date) {
            this.air_date = air_date;
        }

        public int getEpisode_number() {
            return episode_number;
        }

        public void setEpisode_number(int episode_number) {
            this.episode_number = episode_number;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOverview() {
            return overview;
        }

        public void setOverview(String overview) {
            this.overview = overview;
        }

        public String getProduction_code() {
            return production_code;
        }

        public void setProduction_code(String production_code) {
            this.production_code = production_code;
        }

        public int getSeason_number() {
            return season_number;
        }

        public void setSeason_number(int season_number) {
            this.season_number = season_number;
        }

        public int getShow_id() {
            return show_id;
        }

        public void setShow_id(int show_id) {
            this.show_id = show_id;
        }

        public String getStill_path() {
            return still_path;
        }

        public void setStill_path(String still_path) {
            this.still_path = still_path;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public List<CrewBean> getCrew() {
            return crew;
        }

        public void setCrew(List<CrewBean> crew) {
            this.crew = crew;
        }

        public List<GuestStarsBean> getGuest_stars() {
            return guest_stars;
        }

        public void setGuest_stars(List<GuestStarsBean> guest_stars) {
            this.guest_stars = guest_stars;
        }

        public static class CrewBean {

            private int id;
            private String credit_id;
            private String name;
            private String department;
            private String job;
            private int gender;
            private String profile_path;

            public CrewBean() {
            }

            public CrewBean(int id, String credit_id, String name, String department, String job, int gender, String profile_path) {
                this.id = id;
                this.credit_id = credit_id;
                this.name = name;
                this.department = department;
                this.job = job;
                this.gender = gender;
                this.profile_path = profile_path;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCredit_id() {
                return credit_id;
            }

            public void setCredit_id(String credit_id) {
                this.credit_id = credit_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getDepartment() {
                return department;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public String getJob() {
                return job;
            }

            public void setJob(String job) {
                this.job = job;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getProfile_path() {
                return profile_path;
            }

            public void setProfile_path(String profile_path) {
                this.profile_path = profile_path;
            }
        }

        public static class GuestStarsBean {

            private int id;
            private String name;
            private String credit_id;
            private String character;
            private int order;
            private int gender;
            private String profile_path;

            public GuestStarsBean() {
            }

            public GuestStarsBean(int id, String name, String credit_id, String character, int order, int gender, String profile_path) {
                this.id = id;
                this.name = name;
                this.credit_id = credit_id;
                this.character = character;
                this.order = order;
                this.gender = gender;
                this.profile_path = profile_path;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getCredit_id() {
                return credit_id;
            }

            public void setCredit_id(String credit_id) {
                this.credit_id = credit_id;
            }

            public String getCharacter() {
                return character;
            }

            public void setCharacter(String character) {
                this.character = character;
            }

            public int getOrder() {
                return order;
            }

            public void setOrder(int order) {
                this.order = order;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getProfile_path() {
                return profile_path;
            }

            public void setProfile_path(String profile_path) {
                this.profile_path = profile_path;
            }
        }
    }
}
