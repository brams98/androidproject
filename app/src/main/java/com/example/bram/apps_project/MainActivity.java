package com.example.bram.apps_project;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String BOOK_BASE_URL =  "https://itunes.apple.com/search?term=shawn+mendes";
    private static final String QUERY_PARAM = "term";
    private static final String MAX_RESULTS = "maxResults";
    private List<item> array= new ArrayList<item>();
    private ProgressDialog dialog;
    ListView listview;
    public Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listview = (ListView) findViewById(R.id.list);
        adapter = new Adapter(this,array);
        listview.setAdapter(adapter);

        JsonObjectRequest req = new JsonObjectRequest(BOOK_BASE_URL.toString(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray data = response.getJSONArray("results");
                    for (int i=0;i<data.length();i++){
                            JSONObject pull =data.getJSONObject(i);
                        item result =new item();
                        result.setImage(pull.getString("artworkUrl30"));
                        result.setName(pull.getString("artistName"));
                        result.setTrack(pull.getString("trackName"));
                        array.add(result);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Appcontroller.getmInstance().addtoRequestqueue(req);
    }
}
