package co.androidninja.popularmovies.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by Aditya Mehta on 15/06/16.
 */
public class ImageHelper {

    public static void loadImage(Context context, ImageView imageView, int resId, int resIdError, String url) {
        Glide.clear(imageView);
        imageView.setImageDrawable(null);
        Glide.with(context).load(url)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(resId)
                .error(resIdError)
                .into(imageView);
    }

}
