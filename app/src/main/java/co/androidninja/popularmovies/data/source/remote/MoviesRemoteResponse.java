package co.androidninja.popularmovies.data.source.remote;

import java.util.List;

import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 12/06/16.
 */
public class MoviesRemoteResponse {

    private long page;
    private List<Movie> results;
    private long total_results;
    private long total_pages;

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public List<Movie> getResults() {
        return results;
    }

    public void setResults(List<Movie> results) {
        this.results = results;
    }

    public long getTotal_results() {
        return total_results;
    }

    public void setTotal_results(long total_results) {
        this.total_results = total_results;
    }

    public long getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(long total_pages) {
        this.total_pages = total_pages;
    }
}
