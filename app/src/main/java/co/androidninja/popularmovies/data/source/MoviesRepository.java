package co.androidninja.popularmovies.data.source;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;

/**
 * This class will make more sense when I'm using a db to store these results locally
 * for now it will just load the movies from the network
 */
public class MoviesRepository implements MoviesDataSource {

    ConnectivityManager mConnectivityManager;
    MoviesDataSource mRemoteSource;

    public MoviesRepository(@NonNull Context context, @NonNull MoviesDataSource remoteSource) {
        mRemoteSource = remoteSource;
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public void loadPopularMovies(long page, LoadMoviesCallback callback) {
        if(isNetworkAvailable()) {
            mRemoteSource.loadPopularMovies(page, callback);
        } else {
            callback.onError("Network not connected, please check your connection and try again", true);
        }
    }

    @Override
    public void loadTopRatedMovies(long page, LoadMoviesCallback callback) {
        if(isNetworkAvailable()) {
            mRemoteSource.loadTopRatedMovies(page, callback);
        } else {
            callback.onError("Network not connected, please check your connection and try again", true);
        }
    }

    /**
     * This method will be more useful when we have a db to clear
     */
    @Override
    public void refreshMovies() {

    }

    private boolean isNetworkAvailable() {
        NetworkInfo nwInfo = mConnectivityManager.getActiveNetworkInfo();
        if (nwInfo != null && nwInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }
}
