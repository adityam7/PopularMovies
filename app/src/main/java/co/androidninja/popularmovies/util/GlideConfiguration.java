package co.androidninja.popularmovies.util;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Created by Aditya Mehta on 15/06/16.
 */
public class GlideConfiguration implements GlideModule {

    private static final int CACHE_SIZE_IN_BYTES = 1024*1024*4 ;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        // Apply options to the builder here.
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 2 * CACHE_SIZE_IN_BYTES));
        builder.setMemoryCache(new LruResourceCache(CACHE_SIZE_IN_BYTES));
        builder.setBitmapPool(new LruBitmapPool(CACHE_SIZE_IN_BYTES));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }

}
