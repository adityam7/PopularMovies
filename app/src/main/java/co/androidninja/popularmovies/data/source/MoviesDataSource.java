package co.androidninja.popularmovies.data.source;

import java.util.List;

import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 11/06/16.
 */
public interface MoviesDataSource {
    interface LoadMoviesCallback {
        void onMoviesLoaded(List<Movie> movies, long nextPage);

        void onError(String message, boolean showRetry);
    }

    void loadPopularMovies(long page, LoadMoviesCallback callback);

    void loadTopRatedMovies(long page,  LoadMoviesCallback callback);

    void refreshMovies();
}
