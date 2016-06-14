package co.androidninja.popularmovies.movieslist;

import android.view.View;

import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 14/06/16.
 */
public class LoadingHolder extends BaseHolder {

    public LoadingHolder(View itemView) {
        super(itemView);
    }

    @Override
    public void bind(Movie movie, MoviesAdapter.MovieListListener listener) {
        listener.LoadMoreMovies();
    }
}
