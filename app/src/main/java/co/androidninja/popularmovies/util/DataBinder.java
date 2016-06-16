package co.androidninja.popularmovies.util;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import co.androidninja.popularmovies.R;

/**
 * Created by Aditya Mehta on 16/06/16.
 */
public final class DataBinder {
    private DataBinder() {
        // NO_OP
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        ImageHelper.loadPosterImage(context, imageView, R.drawable.ic_movies, R.drawable.ic_movies, url);
    }
}
