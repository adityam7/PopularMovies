package co.androidninja.popularmovies.movieslist;

import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import co.androidninja.popularmovies.data.Movie;
import co.androidninja.popularmovies.data.source.MoviesDataSource;
import co.androidninja.popularmovies.util.EspressoIdlingResource;

/**
 * Created by Aditya Mehta on 11/06/16.
 */
public class MoviesPresenter implements MoviesContract.Presenter {

    @Filter
    int currentFilter = POPULAR;

    private MoviesContract.View mView;
    private MoviesDataSource mMovieRepository;
    private long mNextPage;

    public MoviesPresenter(@NonNull MoviesContract.View view, @NonNull MoviesDataSource dataSource) {
        mView = view;
        mMovieRepository = dataSource;
        mNextPage = 1;
    }

    @Override
    public void start() {
        loadMovies(false, true);
    }

    @Override
    public void loadMovies(boolean forceUpdate, final boolean loadInitial) {
        if (loadInitial) {
            mView.showLoading(true);
            mNextPage = 1;
        }
        if (forceUpdate) {
            mMovieRepository.refreshMovies();
        }

        EspressoIdlingResource.increment();
        MoviesDataSource.LoadMoviesCallback callback = new MoviesDataSource.LoadMoviesCallback() {
            @Override
            public void onMoviesLoaded(List<Movie> movies, long nextPage) {
                if (mView.isActive()) {
                    mNextPage = nextPage;
                    if (loadInitial) {
                        mView.showLoading(false);
                        mView.showMovies(movies);
                    } else {
                        mView.addMovies(movies);
                    }
                    if(mNextPage == -1) {
                        mView.showListComplete();
                    }
                }
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onError(String message, boolean showRetry) {
                if (mView.isActive()) {
                    if (loadInitial) {
                        mView.showLoading(false);
                        mView.showError(message, showRetry);
                    } else {
                        mView.showListError(message, showRetry);
                    }
                }
                EspressoIdlingResource.decrement();
            }
        };

        if(mNextPage != -1) {
            if (currentFilter == POPULAR) {
                mMovieRepository.loadPopularMovies(mNextPage, callback);
            } else {
                mMovieRepository.loadTopRatedMovies(mNextPage, callback);
            }
        }
    }

    @Override
    public void setFilter(@Filter int filter) {
        currentFilter = filter;
        mMovieRepository.refreshMovies();
        start();
    }

    @Override
    public void movieSelected(Movie movie, View view) {
        mView.goToMovie(movie, view);
    }

    @Override
    public void cleanUp() {
        mView = null;
        mMovieRepository = null;
    }
}
