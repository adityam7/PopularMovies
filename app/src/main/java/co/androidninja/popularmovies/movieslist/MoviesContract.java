package co.androidninja.popularmovies.movieslist;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

import co.androidninja.popularmovies.BasePresenter;
import co.androidninja.popularmovies.BaseView;
import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 11/06/16.
 */
public interface MoviesContract {

    interface View extends BaseView<Presenter> {
        void showLoading(boolean loading);
        void showError(String errorMessage, boolean showRetry);
        void showMovies(List<Movie> movies);
        void addMovies(List<Movie> movies);
        void showListError(String errorMessage, boolean showRetry);
        void goToMovie(Movie movie);
        boolean isActive();
    }

    interface Presenter extends BasePresenter {
        int POPULAR = 1;
        int TOP_RATED = 2;

        @IntDef({POPULAR, TOP_RATED})
        @Retention(RetentionPolicy.SOURCE)
        @interface Filter {
        }

        void loadMovies(boolean forceUpdate, boolean loadInitial);
        void setFilter(@Filter int filterType);
        void movieSelected(Movie movie);
        void cleanUp();
    }

}
