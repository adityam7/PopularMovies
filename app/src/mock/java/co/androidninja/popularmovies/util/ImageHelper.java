package co.androidninja.popularmovies.util;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Aditya Mehta on 16/06/16.
 */
public class ImageHelper {

    public static void loadPosterImage(Context context, ImageView imageView, @DrawableRes int resIdPlaceHolder, @DrawableRes int resIdError, String path) {
        Glide.clear(imageView);
        imageView.setImageDrawable(null);
        Glide.with(context)
                .load(Uri.parse("file:///android_asset"+path))
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(resIdPlaceHolder)
                .error(resIdError)
                .into(imageView);
    }

    public static void loadBackDropImage(Context context, ImageView imageView, String path) {
        Glide.clear(imageView);
        imageView.setImageDrawable(null);
        Glide.with(context)
                .load(Uri.parse("file:///android_asset"+path))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(imageView);
    }

}
