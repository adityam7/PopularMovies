package co.androidninja.popularmovies;

import android.content.Context;

import co.androidninja.popularmovies.data.source.MockDataSource;
import co.androidninja.popularmovies.data.source.MoviesDataSource;

/**
 * Created by Aditya Mehta on 16/06/16.
 */
public class Injection {
    public static MoviesDataSource injectMoviesDataSource(Context context) {
        return new MockDataSource();
    }
}
