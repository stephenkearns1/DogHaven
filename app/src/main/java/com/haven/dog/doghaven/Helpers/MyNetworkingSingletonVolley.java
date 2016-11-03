package com.haven.dog.doghaven.Helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Created by kearn on 02/11/2016.
 */

public class MyNetworkingSingletonVolley {

    private static MyNetworkingSingletonVolley singletonInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private static Context mContext;


    private MyNetworkingSingletonVolley(Context c){
        mContext = c;
        mRequestQueue = getRequestQueue();


        mImageLoader = new ImageLoader(mRequestQueue,
                             new ImageLoader.ImageCache(){
                    private final LruCache<String, Bitmap>
                                cache = new LruCache<String, Bitmap>(40);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                 cache.put(url, bitmap);
            }
        });
    }


    public static synchronized MyNetworkingSingletonVolley getInstance(Context context){
        if(singletonInstance == null){
            singletonInstance = new MyNetworkingSingletonVolley(context);
        }

        return singletonInstance;
    }


    public RequestQueue getRequestQueue(){
        /*
          if a requestQueue has not been created for the application
          create one making sure it is created for the application
          not the activity, to avoid leaking

         */

        if(mRequestQueue == null){

            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addReuestToQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    public ImageLoader getImageLoader(){
        return mImageLoader;
    }
}
