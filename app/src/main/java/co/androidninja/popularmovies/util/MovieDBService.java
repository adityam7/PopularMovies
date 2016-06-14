package co.androidninja.popularmovies.util;

import co.androidninja.popularmovies.data.source.remote.MoviesRemoteResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Aditya Mehta on 12/06/16.
 */
public interface MovieDBService {

    @GET("popular")
    Call<MoviesRemoteResponse> getPopularMovies(@Query("api_key") String api_key,
                                                @Query("page") long page);

    @GET("top_rated")
    Call<MoviesRemoteResponse> getTopRatedMovies(@Query("api_key") String api_key,
                                                 @Query("page") long page);

}
