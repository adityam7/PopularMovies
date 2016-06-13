package co.androidninja.popularmovies.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aditya Mehta on 12/06/16.
 */
public class RetrofitSingleton {
    private static Retrofit singleton;
    private static final String BASE_URL = "http://api.themoviedb.org/3/movie/";

    private static Retrofit setUpRetrofit(String baseUrl) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS).build();
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd")
                .create();
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static Retrofit getInstance() {
        if(singleton == null) {
            singleton = setUpRetrofit(BASE_URL);
        }
        return singleton;
    }
}
