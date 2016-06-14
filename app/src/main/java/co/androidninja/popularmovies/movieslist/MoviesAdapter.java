package co.androidninja.popularmovies.movieslist;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;

/**
 * Created by Aditya Mehta on 14/06/16.
 */
public class MoviesAdapter extends RecyclerView.Adapter<BaseHolder> {

    private static final String TAG = MoviesAdapter.class.getSimpleName();
    private static final int LOADING = 1;
    private static final int MOVIE = 2;

    private List<Movie> mMovies;
    private int mShowLoadingMore;

    public interface MovieListListener {
        void MovieClicked(Movie movie);
        void LoadMoreMovies();
    }

    private MovieListListener mListListener;

    public MoviesAdapter(List<Movie> movies, MovieListListener listener) {
        mMovies = movies;
        mListListener = listener;
        mShowLoadingMore = 1;
    }

    public void addMovies(List<Movie> movies) {
        mMovies.addAll(movies);
        mShowLoadingMore = 1;
        notifyDataSetChanged();
    }

    void setListComplete() {
        mShowLoadingMore = 0;
        notifyDataSetChanged();
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case MOVIE:
                Log.d(TAG, "onCreateViewHolder: MOVIE");
                return getMovieHolder(parent);
            case LOADING:
                return getLoadingHolder(parent);
            default:
                return null;
        }
    }

    private LoadingHolder getLoadingHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
        return new LoadingHolder(view);
    }

    private MovieHolder getMovieHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieHolder(view);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        if(position < mMovies.size()) {
            holder.bind(mMovies.get(position), mListListener);
        } else {
            holder.bind(null, mListListener);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mShowLoadingMore == 1 && position == mMovies.size()) {
            return LOADING;
        } else {
            return MOVIE;
        }
    }

    @Override
    public int getItemCount() {
        return mMovies.size()+mShowLoadingMore;
    }
}
