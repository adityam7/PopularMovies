package co.androidninja.popularmovies.data.source.remote;

import co.androidninja.popularmovies.data.source.MoviesDataSource;
import co.androidninja.popularmovies.util.MovieDBService;
import co.androidninja.popularmovies.util.RetrofitSingleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Aditya Mehta on 11/06/16.
 */
public class MoviesRemoteDataSource implements MoviesDataSource {

    private MovieDBService mService;
    private static String API_KEY = "9af5d91c76a48f655f6ac15085707ef8"; //"{Your API Key goes here}";

    public MoviesRemoteDataSource() {
        Retrofit retrofit = RetrofitSingleton.getInstance();
        mService = retrofit.create(MovieDBService.class);
    }

    @Override
    public void loadPopularMovies(long page, final LoadMoviesCallback callback) {
        Call<MoviesRemoteResponse> call = mService.getPopularMovies(API_KEY, page);
        executeCall(call, callback);
    }

    @Override
    public void loadTopRatedMovies(long page, final LoadMoviesCallback callback) {
        Call<MoviesRemoteResponse> call = mService.getTopRatedMovies(API_KEY, page);
        executeCall(call, callback);
    }

    private void executeCall(Call<MoviesRemoteResponse> call, final LoadMoviesCallback callback) {
        call.enqueue(new Callback<MoviesRemoteResponse>() {
            @Override
            public void onResponse(Call<MoviesRemoteResponse> call, Response<MoviesRemoteResponse> response) {
                if(response.isSuccessful()) {
                    MoviesRemoteResponse remoteResponse = response.body();
                    long page = remoteResponse.getPage();
                    long total_pages = remoteResponse.getTotal_pages();
                    if(page == total_pages) {
                        page = -1;
                    } else {
                        page++;
                    }
                    callback.onMoviesLoaded(remoteResponse.getResults(), page);
                } else {
                    int statusCode = response.code();
                    String message = "Oops, something went wrong";
                    boolean tryAgain = true;
                    if(statusCode >= 400 && statusCode < 500) {
                        message = "We are currently facing some server issues right now, please try again later in 15 minutes";
                        tryAgain = true;
                    } else if (statusCode >= 500) {
                        message = "We are currently facing some server issues right now, close the app and try again in 15 minutes";
                        tryAgain = false;
                    }
                    callback.onError(message, tryAgain);
                }
            }

            @Override
            public void onFailure(Call<MoviesRemoteResponse> call, Throwable t) {
                callback.onError(t.getMessage(), true);
            }
        });
    }

    @Override
    public void refreshMovies() {

    }
}
