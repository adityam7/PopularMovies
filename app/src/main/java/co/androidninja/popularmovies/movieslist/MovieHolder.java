package co.androidninja.popularmovies.movieslist;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 14/06/16.
 */
public class MovieHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.movie_poster)
    protected ImageView ivMoviePoster;

    public MovieHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Movie movie) {
        Glide.with(ivMoviePoster.getContext())
                .load(movie.getPoster_path())
                .placeholder(R.drawable.ic_movies)
                .error(R.drawable.ic_movies)
                .crossFade()
                .into(ivMoviePoster);
    }
}
