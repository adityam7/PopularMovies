package co.androidninja.popularmovies.data.source;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import co.androidninja.popularmovies.util.NetworkUtil;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Aditya Mehta on 16/06/16.
 */
public class MoviesRepositoryTest {

    @Mock
    private NetworkUtil mNetworkUtil;

    @Mock
    private MoviesDataSource mDataSource;

    @Mock
    private MoviesDataSource.LoadMoviesCallback mLoadMoviesCallback;

    private MoviesRepository mRepository;

    @Before
    public void setUpMoviesRepository() {
        MockitoAnnotations.initMocks(this);
        mRepository = new MoviesRepository(mNetworkUtil, mDataSource);
    }

    @Test
    public void loadPopularMovies() {
        when(mNetworkUtil.isNetworkAvailable()).thenReturn(true);

        mRepository.loadPopularMovies(1, mLoadMoviesCallback);

        verify(mNetworkUtil).isNetworkAvailable();
        verify(mDataSource).loadPopularMovies(1, mLoadMoviesCallback);
    }

    @Test
    public void loadPopularMoviesNetworkFail() {
        when(mNetworkUtil.isNetworkAvailable()).thenReturn(false);

        mRepository.loadPopularMovies(1, mLoadMoviesCallback);

        verify(mNetworkUtil).isNetworkAvailable();
        verify(mLoadMoviesCallback).onError(mRepository.NETWORK_NOT_CONNECTED, true);
    }

    @Test
    public void loadTopRatedMovies() {
        when(mNetworkUtil.isNetworkAvailable()).thenReturn(true);

        mRepository.loadPopularMovies(1, mLoadMoviesCallback);

        verify(mNetworkUtil).isNetworkAvailable();
        verify(mDataSource).loadPopularMovies(1, mLoadMoviesCallback);
    }

    @Test
    public void loadTopRatedMoviesNetworkFail() {
        when(mNetworkUtil.isNetworkAvailable()).thenReturn(false);

        mRepository.loadPopularMovies(1, mLoadMoviesCallback);

        verify(mNetworkUtil).isNetworkAvailable();
        verify(mLoadMoviesCallback).onError(mRepository.NETWORK_NOT_CONNECTED, true);
    }
}
