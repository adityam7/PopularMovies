package co.androidninja.popularmovies.movieslist;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;
import co.androidninja.popularmovies.moviedetail.MovieDetailActivity;

public class MoviesFragment extends Fragment implements MoviesContract.View, MoviesAdapter.MovieListListener {

    private static final String SPINNER_STATE = "spinner_state";

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
    private MoviesAdapter mAdapter;
    private Spinner mSpinner;
    private int mNumberOfMovies;
    private boolean mLoading = false;
    private int mNumberOfColoumns;
    private int mSpinnerPosition;

    public MoviesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(savedInstanceState != null) {
            mSpinnerPosition = savedInstanceState.getInt(SPINNER_STATE, 0);
        } else {
            mSpinnerPosition = 0;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        ButterKnife.bind(this, view);
        mNumberOfMovies = 0;
        mNumberOfColoumns = getResources().getInteger(R.integer.columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), mNumberOfColoumns);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position < mNumberOfMovies ? 1 : mNumberOfColoumns;
            }
        });
        mRecyclerView.setLayoutManager(gridLayoutManager);
        return view;
    }
    
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.movie_filter_menu, menu);

        MenuItem item = menu.findItem(R.id.drop_down);
        mSpinner = (Spinner) MenuItemCompat.getActionView(item);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.drop_down_items, R.layout.drop_down_item);

        adapter.setDropDownViewResource(R.layout.drop_down_item);

        mSpinner.setAdapter(adapter);
        if(mSpinnerPosition != 0) {
            mSpinner.setSelection(mSpinnerPosition);
        }
        mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mLoading = true;
                if(position == 0) {
                    mPresenter.setFilter(MoviesContract.Presenter.POPULAR);
                } else {
                    mPresenter.setFilter(MoviesContract.Presenter.TOP_RATED);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SPINNER_STATE, mSpinner.getSelectedItemPosition());
    }

    @Override
    public void showLoading(boolean loading) {
        if (loading) {
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
        mNumberOfMovies = movies.size();
        mAdapter = new MoviesAdapter(movies, this);
        mRecyclerView.setAdapter(mAdapter);
        mLoading = false;
    }

    @Override
    public void addMovies(List<Movie> movies) {
        mNumberOfMovies += movies.size();
        mAdapter.addMovies(movies);
        mLoading = false;
    }

    @Override
    public void showListError(String errorMessage, boolean showRetry) {
        Snackbar.make(mRecyclerView, errorMessage, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mPresenter.loadMovies(false, false);
                    }
                }).show();
    }

    @Override
    public void showListComplete() {
        mAdapter.setListComplete();
    }

    @Override
    public void goToMovie(Movie movie, View view) {
        Intent intent = MovieDetailActivity.getStartIntent(getContext(), movie);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), view, getString(R.string.movie_poster));
        startActivity(intent, optionsCompat.toBundle());
    }

    @Override
    public boolean isActive() {
        return isAdded();
    }

    @Override
    public void setPresenter(MoviesContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void MovieClicked(Movie movie, View view) {
        mPresenter.movieSelected(movie, view);
    }

    @Override
    public void LoadMoreMovies() {
        if(!mLoading) {
            mPresenter.loadMovies(false, false);
            mLoading = true;
        }
    }
}
