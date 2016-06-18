package co.androidninja.popularmovies.movieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 14/06/16.
 */
public abstract class BaseHolder extends RecyclerView.ViewHolder {
    public BaseHolder(View itemView) {
        super(itemView);
    }

    public abstract void bind(Movie movie, MoviesAdapter.MovieListListener listener);
}
