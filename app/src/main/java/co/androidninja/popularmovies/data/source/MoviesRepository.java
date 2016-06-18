package co.androidninja.popularmovies.data.source;

import android.support.annotation.NonNull;

import co.androidninja.popularmovies.util.NetworkUtil;

/**
 * This class will make more sense when I'm using a db to store these results locally
 * for now it will just load the movies from the network
 */
public class MoviesRepository implements MoviesDataSource {

    static final String NETWORK_NOT_CONNECTED = "Network not connected, please check your connection and try again";

    private NetworkUtil mNetworkUtil;
    MoviesDataSource mRemoteSource;

    public MoviesRepository(@NonNull NetworkUtil networkUtil, @NonNull MoviesDataSource remoteSource) {
        mRemoteSource = remoteSource;
        mNetworkUtil = networkUtil;
    }

    @Override
    public void loadPopularMovies(long page, LoadMoviesCallback callback) {
        if(mNetworkUtil.isNetworkAvailable()) {
            mRemoteSource.loadPopularMovies(page, callback);
        } else {
            callback.onError(NETWORK_NOT_CONNECTED, true);
        }
    }

    @Override
    public void loadTopRatedMovies(long page, LoadMoviesCallback callback) {
        if(mNetworkUtil.isNetworkAvailable()) {
            mRemoteSource.loadTopRatedMovies(page, callback);
        } else {
            callback.onError(NETWORK_NOT_CONNECTED, true);
        }
    }

    /**
     * This method will be more useful when we have a db to clear
     */
    @Override
    public void refreshMovies() {

    }
}
