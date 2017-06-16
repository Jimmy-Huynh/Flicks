package com.tvnsoftware.flicks.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TamHH on 6/15/2017.
 */

public class NowPlaying {
    @SerializedName("results")
    private List<Movie> movies;
    public int page;
    @SerializedName("total_results")
    public int totalResults;
    public Dates dates;
    @SerializedName("total_pages")
    public int totalPages;

    public NowPlaying() {
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public Dates getDates() {
        return dates;
    }

    public void setDates(Dates dates) {
        this.dates = dates;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
