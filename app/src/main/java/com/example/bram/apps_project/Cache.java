package com.example.bram.apps_project;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import com.android.volley.toolbox.ImageLoader;



public class Cache extends LruCache<String,Bitmap> implements ImageLoader.ImageCache {
    /**
     * @param maxSize for caches that do not override {@link #sizeOf}, this is
     *                the maximum number of entries in the cache. For all other caches,
     *                this is the maximum sum of the sizes of the entries in this cache.
     */
    public Cache(int maxSize) {
        super(maxSize);
    }

    public Cache() {
        this(getDefaultCachesize());
    }

    public static int getDefaultCachesize(){
        final int maxmemory = (int) (Runtime.getRuntime().maxMemory()/1024);
        final int cachesize = maxmemory/8;
        return cachesize;
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes()*value.getHeight()/1024;
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url,bitmap);
    }
}
