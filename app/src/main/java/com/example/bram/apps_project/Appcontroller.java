package com.example.bram.apps_project;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;



public class Appcontroller extends Application{
    public  static final String TAG =Appcontroller.class.getSimpleName();
    private RequestQueue mrequestque;
    private ImageLoader imgload;
    private static Appcontroller mInstance;



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
    }
    public static synchronized Appcontroller getmInstance() {
        return mInstance;
    }
    public RequestQueue getMrequestque() {
        if (mrequestque == null) {
            mrequestque= Volley.newRequestQueue(getApplicationContext());
        }
        return mrequestque;
    }

    public ImageLoader getImgload() {
        getMrequestque();
        if (imgload == null){
            imgload = new ImageLoader(this.mrequestque,new Cache());
        }
        return imgload;
    }
    public <T> void addtoRequestqueue(Request<T> request,String tag ){
            request.setTag((TextUtils.isEmpty(tag)? TAG:tag));
            getMrequestque().add(request);
    }

    public <T> void cancelpendingreq(Object tag) {

    }


    public void addtoRequestqueue(JsonObjectRequest req) {
    }
}
