package com.hemant.pixabay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.Onclicklistner {

    public static final String EXTRA_URL="imageURl";
    public static final String EXTRA_CREATOR ="creatorName";
    public static final String EXTRA_LIKES = "likecount";

    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ArrayList<ExampleItem> exampleItems;
    private RequestQueue requestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView =(RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        exampleItems = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(this);
        parseJson();
    }

    private void parseJson() {

       //  String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        String url ="https://pixabay.com/api/?key=14805449-305fd88f1079e7312d12fce58&q=food&image_type=photo&pretty=true";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("hits");

                    for (int i=0;i<jsonArray.length();i++){
                        JSONObject hit = jsonArray.getJSONObject(i);
                        String creatorname = hit.getString("user");
                        String imageurl = hit.getString("webformatURL");
                        int likecount = hit.getInt("likes");
                        exampleItems.add(new ExampleItem(imageurl,creatorname,likecount));
                    }
                    exampleAdapter = new ExampleAdapter(MainActivity.this,exampleItems);
                    recyclerView.setAdapter(exampleAdapter);
                  exampleAdapter.setOnItemClickListner(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        requestQueue.add(request);

    }

    @Override
    public void onclicklist(int position) {

        Intent detail = new Intent(this,DetailActivity.class);
        ExampleItem clickitem = exampleItems.get(position);
        detail.putExtra(EXTRA_URL,clickitem.getmImageurl());
        detail.putExtra(EXTRA_CREATOR,clickitem.getmCreator());
        detail.putExtra(EXTRA_LIKES,clickitem.getmLikes());
        startActivity(detail);
    }
}
