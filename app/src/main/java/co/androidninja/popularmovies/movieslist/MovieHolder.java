package co.androidninja.popularmovies.movieslist;

import android.view.View;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;
import co.androidninja.popularmovies.util.ImageHelper;

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
        ImageHelper.loadPosterImage(ivMoviePoster.getContext(),
                ivMoviePoster, R.drawable.ic_movies,
                R.drawable.ic_movies, movie.getPoster_path());
        ivMoviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.MovieClicked(movie, v);
            }
        });
    }
}
