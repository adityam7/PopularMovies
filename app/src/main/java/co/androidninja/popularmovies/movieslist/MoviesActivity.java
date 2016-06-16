package co.androidninja.popularmovies.movieslist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import co.androidninja.popularmovies.Injection;
import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.source.MoviesDataSource;
import co.androidninja.popularmovies.util.ActivityUtils;

public class MoviesActivity extends AppCompatActivity {

    private MoviesContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MoviesFragment moviesFragment = (MoviesFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(moviesFragment == null) {
            moviesFragment = new MoviesFragment();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), moviesFragment,
                    R.id.contentFrame);
        }

        MoviesDataSource repository = Injection.injectMoviesDataSource(getApplicationContext());
        mPresenter = new MoviesPresenter(moviesFragment, repository);
        moviesFragment.setPresenter(mPresenter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.cleanUp();
        mPresenter = null;
    }
}
