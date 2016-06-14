package co.androidninja.popularmovies.movieslist;

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
public class MovieHolder extends BaseHolder {

    @BindView(R.id.movie_poster)
    protected ImageView ivMoviePoster;

    public MovieHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void bind(final Movie movie, final MoviesAdapter.MovieListListener listener) {
        Glide.with(ivMoviePoster.getContext())
                .load(movie.getPoster_path())
                .placeholder(R.drawable.ic_movies)
                .error(R.drawable.ic_movies)
                .crossFade()
                .into(ivMoviePoster);
        ivMoviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.MovieClicked(movie);
            }
        });
    }
}
