package com.example.moviesapp.Models;

import java.util.List;

public class TopRatedTvDetailsModel {


    public String backdrop_path;
    public String first_air_date;
    public String homepage;
    public int id;
    public boolean in_production;
    public String last_air_date;
    public LastEpisodeToAirBean last_episode_to_air;
    public String name;
    public Object next_episode_to_air;
    public int number_of_episodes;
    public int number_of_seasons;
    public String original_language;
    public String original_name;
    public String overview;
    public double popularity;
    public String poster_path;
    public String status;
    public String type;
    public double vote_average;
    public int vote_count;
    public List<CreatedByBean> created_by;
    public List<Integer> episode_run_time;
    public List<GenresBean> genres;
    public List<String> languages;
    public List<NetworksBean> networks;
    public List<String> origin_country;
    public List<ProductionCompaniesBean> production_companies;
    public List<SeasonsBean> seasons;

    public TopRatedTvDetailsModel(String backdrop_path, String first_air_date, String homepage, int id, boolean in_production, String last_air_date, LastEpisodeToAirBean last_episode_to_air, String name, Object next_episode_to_air, int number_of_episodes, int number_of_seasons, String original_language, String original_name, String overview, double popularity, String poster_path, String status, String type, double vote_average, int vote_count, List<CreatedByBean> created_by, List<Integer> episode_run_time, List<GenresBean> genres, List<String> languages, List<NetworksBean> networks, List<String> origin_country, List<ProductionCompaniesBean> production_companies, List<SeasonsBean> seasons) {
        this.backdrop_path = backdrop_path;
        this.first_air_date = first_air_date;
        this.homepage = homepage;
        this.id = id;
        this.in_production = in_production;
        this.last_air_date = last_air_date;
        this.last_episode_to_air = last_episode_to_air;
        this.name = name;
        this.next_episode_to_air = next_episode_to_air;
        this.number_of_episodes = number_of_episodes;
        this.number_of_seasons = number_of_seasons;
        this.original_language = original_language;
        this.original_name = original_name;
        this.overview = overview;
        this.popularity = popularity;
        this.poster_path = poster_path;
        this.status = status;
        this.type = type;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.created_by = created_by;
        this.episode_run_time = episode_run_time;
        this.genres = genres;
        this.languages = languages;
        this.networks = networks;
        this.origin_country = origin_country;
        this.production_companies = production_companies;
        this.seasons = seasons;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isIn_production() {
        return in_production;
    }

    public void setIn_production(boolean in_production) {
        this.in_production = in_production;
    }

    public String getLast_air_date() {
        return last_air_date;
    }

    public void setLast_air_date(String last_air_date) {
        this.last_air_date = last_air_date;
    }

    public LastEpisodeToAirBean getLast_episode_to_air() {
        return last_episode_to_air;
    }

    public void setLast_episode_to_air(LastEpisodeToAirBean last_episode_to_air) {
        this.last_episode_to_air = last_episode_to_air;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getNext_episode_to_air() {
        return next_episode_to_air;
    }

    public void setNext_episode_to_air(Object next_episode_to_air) {
        this.next_episode_to_air = next_episode_to_air;
    }

    public int getNumber_of_episodes() {
        return number_of_episodes;
    }

    public void setNumber_of_episodes(int number_of_episodes) {
        this.number_of_episodes = number_of_episodes;
    }

    public int getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(int number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<CreatedByBean> getCreated_by() {
        return created_by;
    }

    public void setCreated_by(List<CreatedByBean> created_by) {
        this.created_by = created_by;
    }

    public List<Integer> getEpisode_run_time() {
        return episode_run_time;
    }

    public void setEpisode_run_time(List<Integer> episode_run_time) {
        this.episode_run_time = episode_run_time;
    }

    public List<GenresBean> getGenres() {
        return genres;
    }

    public void setGenres(List<GenresBean> genres) {
        this.genres = genres;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<NetworksBean> getNetworks() {
        return networks;
    }

    public void setNetworks(List<NetworksBean> networks) {
        this.networks = networks;
    }

    public List<String> getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(List<String> origin_country) {
        this.origin_country = origin_country;
    }

    public List<ProductionCompaniesBean> getProduction_companies() {
        return production_companies;
    }

    public void setProduction_companies(List<ProductionCompaniesBean> production_companies) {
        this.production_companies = production_companies;
    }

    public List<SeasonsBean> getSeasons() {
        return seasons;
    }

    public void setSeasons(List<SeasonsBean> seasons) {
        this.seasons = seasons;
    }

    public static class LastEpisodeToAirBean {

        public String air_date;
        public int episode_number;
        public int id;
        public String name;
        public String overview;
        public String production_code;
        public int season_number;
        public int show_id;
        public String still_path;
        public int vote_average;
        public int vote_count;

        public LastEpisodeToAirBean(String air_date, int episode_number, int id, String name, String overview, String production_code, int season_number, int show_id, String still_path, int vote_average, int vote_count) {
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

        public int getVote_average() {
            return vote_average;
        }

        public void setVote_average(int vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }
    }

    public static class CreatedByBean {

        public int id;
        public String credit_id;
        public String name;
        public int gender;
        public String profile_path;

        public CreatedByBean(int id, String credit_id, String name, int gender, String profile_path) {
            this.id = id;
            this.credit_id = credit_id;
            this.name = name;
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

    public static class GenresBean {

        public int id;
        public String name;

        public GenresBean(int id, String name) {
            this.id = id;
            this.name = name;
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
    }

    public static class NetworksBean {

        public String name;
        public int id;
        public String logo_path;
        public String origin_country;

        public NetworksBean(String name, int id, String logo_path, String origin_country) {
            this.name = name;
            this.id = id;
            this.logo_path = logo_path;
            this.origin_country = origin_country;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }
    }

    public static class ProductionCompaniesBean {

        public int id;
        public String logo_path;
        public String name;
        public String origin_country;

        public ProductionCompaniesBean(int id, String logo_path, String name, String origin_country) {
            this.id = id;
            this.logo_path = logo_path;
            this.name = name;
            this.origin_country = origin_country;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getLogo_path() {
            return logo_path;
        }

        public void setLogo_path(String logo_path) {
            this.logo_path = logo_path;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getOrigin_country() {
            return origin_country;
        }

        public void setOrigin_country(String origin_country) {
            this.origin_country = origin_country;
        }
    }

    public static class SeasonsBean {

        public String air_date;
        public int episode_count;
        public int id;
        public String name;
        public String overview;
        public String poster_path;
        public int season_number;

        public SeasonsBean(String air_date, int episode_count, int id, String name, String overview, String poster_path, int season_number) {
            this.air_date = air_date;
            this.episode_count = episode_count;
            this.id = id;
            this.name = name;
            this.overview = overview;
            this.poster_path = poster_path;
            this.season_number = season_number;
        }

        public String getAir_date() {
            return air_date;
        }

        public void setAir_date(String air_date) {
            this.air_date = air_date;
        }

        public int getEpisode_count() {
            return episode_count;
        }

        public void setEpisode_count(int episode_count) {
            this.episode_count = episode_count;
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
    }
}
