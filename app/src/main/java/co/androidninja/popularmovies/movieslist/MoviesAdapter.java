package co.androidninja.popularmovies.movieslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 14/06/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MovieHolder> {

    private List<Movie> mMovies;

    public MoviesAdapter(List<Movie> movies) {
        mMovies = movies;
    }

    @Override
    public MovieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieHolder holder, int position) {
        holder.bind(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }
}
