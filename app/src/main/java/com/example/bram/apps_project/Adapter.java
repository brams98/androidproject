package com.example.bram.apps_project;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;
import java.util.zip.Inflater;



public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<item> items;
    ImageLoader imageLoader = Appcontroller.getmInstance().getImgload();

    public Adapter(MainActivity mainActivity, List<item> array) {

    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView=inflater.inflate(R.layout.custom_list,null);
        }
        if (imageLoader == null){
            imageLoader=Appcontroller.getmInstance().getImgload();
            NetworkImageView imageView = convertView.findViewById(R.id.imgview);
            TextView name= convertView.findViewById(R.id.artistname);
            TextView track = convertView.findViewById(R.id.Trackname);

            item  Item = items.get(position);
            imageView.setImageUrl(Item.getImage(),imageLoader);
            name.setText(Item.getName());
            track.setText(Item.getTrack());

        }
        return null;
    }
}
