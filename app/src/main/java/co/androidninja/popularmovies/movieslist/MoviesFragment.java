package co.androidninja.popularmovies.movieslist;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;

public class MoviesFragment extends Fragment implements MoviesContract.View {

    @BindView(R.id.progressBar)
    protected ProgressBar mProgressBar;

    @BindView(R.id.recyclerview)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.error_layout)
    protected RelativeLayout mErrorLayout;

    @BindView(R.id.errorText)
    protected TextView mErrorText;

    @BindView(R.id.retry_button)
    protected Button btnRetry;

    private static final String TAG = MoviesFragment.class.getSimpleName();
    private MoviesContract.Presenter mPresenter;

    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.start();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showLoading(boolean loading) {
        if(loading) {
            mProgressBar.setVisibility(View.VISIBLE);
            mErrorLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.GONE);
        } else {
            mProgressBar.setVisibility(View.GONE);
            mErrorLayout.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showError(String errorMessage, boolean showRetry) {
        mProgressBar.setVisibility(View.GONE);
        mErrorLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.GONE);
        mErrorText.setText(errorMessage);
        btnRetry.setVisibility(showRetry ? View.VISIBLE : View.GONE);
    }

    @Override
    public void showMovies(List<Movie> movies) {
        Toast.makeText(getContext(), "Movies: "+movies.size(), Toast.LENGTH_SHORT).show();
        MoviesAdapter adapter = new MoviesAdapter(movies);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void addMovies(List<Movie> movies) {

    }

    @Override
    public void showListError(String errorMessage, boolean showRetry) {
        Snackbar.make(mRecyclerView, errorMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.loadMovies(false, false);
                    }
                });
    }

    @Override
    public void goToMovie(Movie movie) {

    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(MoviesContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
