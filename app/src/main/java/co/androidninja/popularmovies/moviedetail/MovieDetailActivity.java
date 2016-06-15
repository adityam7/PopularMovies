package co.androidninja.popularmovies.moviedetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.androidninja.popularmovies.R;
import co.androidninja.popularmovies.data.Movie;
import co.androidninja.popularmovies.util.ActivityUtils;


/**
 * For now this part of the App is not using MVP as there isn't much
 * to do, and a couple of simple functional test will cover this part of the app.
 * Once this app starts supporting tablets and is loading the movie trailer
 * MVP will be required.
 *
 * For now I tried playing around with DataBinding for the {@link MovieDetailFragment}
 */
public class MovieDetailActivity extends AppCompatActivity {

    private static final String MOVIE = "movie";
    private Movie mMovie;

    @BindView(R.id.backdrop)
    protected ImageView ivBackDrop;

    public static Intent getStartIntent(Context context, @NonNull Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Movie movie = getIntent().getParcelableExtra(MOVIE);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            if(!movie.getTitle().matches(movie.getOriginal_title())) {
                getSupportActionBar().setTitle(movie.getTitle());
            } else {
                getSupportActionBar().setTitle("");
            }
        }

        Glide.with(this).load(movie.getBackdrop_path()).into(ivBackDrop);

        MovieDetailFragment movieDetailFragment = (MovieDetailFragment) getSupportFragmentManager()
                .findFragmentById(R.id.contentFrame);
        if(movieDetailFragment == null) {
            movieDetailFragment = MovieDetailFragment.newInstance(movie);
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), movieDetailFragment,
                    R.id.contentFrame);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
