package co.androidninja.popularmovies.movieslist;

import com.google.common.collect.Lists;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import co.androidninja.popularmovies.data.Movie;
import co.androidninja.popularmovies.data.source.MoviesDataSource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Aditya Mehta on 16/06/16.
 */
public class MoviesPresenterTest {

    @Mock
    private MoviesContract.View mView;

    @Mock
    private MoviesDataSource mDataSource;

    @Captor
    private ArgumentCaptor<MoviesDataSource.LoadMoviesCallback> mLoadMoviesCallback;

    private MoviesPresenter mPresenter;

    @Before
    public void setUpMoviesPresenter() {
        MockitoAnnotations.initMocks(this);
        mPresenter = new MoviesPresenter(mView, mDataSource);
    }

    @Test
    public void loadInitialMoviesIntoView() {
        when(mView.isActive()).thenReturn(true);

        mPresenter.currentFilter = MoviesContract.Presenter.POPULAR;
        mPresenter.start();

        verify(mView).showLoading(true);

        verify(mDataSource).loadPopularMovies(eq(1l), mLoadMoviesCallback.capture());

        List<Movie> movies = Lists.newArrayList(new Movie());
        mLoadMoviesCallback.getValue().onMoviesLoaded(movies, 2);

        verify(mView).isActive();
        verify(mView).showLoading(false);
        verify(mView).showMovies(movies);
    }

    @Test
    public void loadRefreshedMoviesIntoView() {
        when(mView.isActive()).thenReturn(true);

        mPresenter.currentFilter = MoviesContract.Presenter.POPULAR;
        mPresenter.loadMovies(true, false);

        verify(mDataSource).refreshMovies();

        verify(mDataSource).loadPopularMovies(eq(1l), mLoadMoviesCallback.capture());

        List<Movie> movies = Lists.newArrayList(new Movie());
        mLoadMoviesCallback.getValue().onMoviesLoaded(movies, 2);

        verify(mView).isActive();
        verify(mView).addMovies(movies);
    }

    @Test
    public void loadFinalMoviesIntoView() {
        when(mView.isActive()).thenReturn(true);

        mPresenter.currentFilter = MoviesContract.Presenter.POPULAR;
        mPresenter.loadMovies(false, false);

        verify(mDataSource).loadPopularMovies(eq(1l), mLoadMoviesCallback.capture());

        List<Movie> movies = Lists.newArrayList(new Movie());
        mLoadMoviesCallback.getValue().onMoviesLoaded(movies, -1);

        verify(mView).isActive();
        verify(mView).addMovies(movies);
        verify(mView).showListComplete();
    }

    @Test
    public void loadInitialMoviesFail() {
        when(mView.isActive()).thenReturn(true);
        String errorMessage = "Test error message";
        boolean showRetry = true;

        mPresenter.currentFilter = MoviesContract.Presenter.POPULAR;
        mPresenter.start();

        verify(mView).showLoading(true);

        verify(mDataSource).loadPopularMovies(eq(1l), mLoadMoviesCallback.capture());

        mLoadMoviesCallback.getValue().onError(errorMessage, showRetry);

        verify(mView).isActive();
        verify(mView).showLoading(false);
        verify(mView).showError(errorMessage, showRetry);
    }

    @Test
    public void loadMoviesFail() {
        when(mView.isActive()).thenReturn(true);
        String errorMessage = "Test error message";
        boolean showRetry = true;

        mPresenter.currentFilter = MoviesContract.Presenter.POPULAR;
        mPresenter.loadMovies(false, false);

        verify(mDataSource).loadPopularMovies(eq(1l), mLoadMoviesCallback.capture());
        mLoadMoviesCallback.getValue().onError(errorMessage, showRetry);

        verify(mView).isActive();
        verify(mView).showListError(errorMessage, showRetry);
    }

    @Test
    public void checkSetFilter() {
        mPresenter.setFilter(MoviesContract.Presenter.TOP_RATED);
        verify(mDataSource).refreshMovies();
        assertEquals(mPresenter.currentFilter, MoviesContract.Presenter.TOP_RATED);
    }
}
