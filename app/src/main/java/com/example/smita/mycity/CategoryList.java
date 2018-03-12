package com.example.smita.mycity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryList extends AppCompatActivity {


    String photoURL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1200&key=AIzaSyA78PaOND-Yoshet42MzYFN7yGt6YWE-x4&photoreference=";

    private List<Content> contentList = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);




        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new CategoryAdapter(CategoryList.this, contentList);
        recyclerView.setAdapter(adapter);

        Bundle bundle = getIntent().getExtras();
        String apiURL = (String) bundle.get("url");
        String title = (String) bundle.get("title");
        setTitle(title);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, apiURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject object = new JSONObject(response);

                            if (object.getString("status").equals("OK")){
                                String rating, contentName, address, photourl,status;

                                JSONArray results = object.getJSONArray("results");
                                for (int i = 0; i < results.length(); i++){
                                    JSONObject hotelObject = results.getJSONObject(i);

                                    contentName = hotelObject.getString("name");
                                    address = hotelObject.getString("vicinity");
                                    String[] shortAddress = address.split(",");

                                    if (hotelObject.has("opening_hours")){
                                        JSONObject statusDetailObj = hotelObject.getJSONObject("opening_hours");
                                        status = statusDetailObj.getString("open_now");
                                        if (status.equals("true")){
                                            status = "Open";
                                        }else {
                                            status = "Closed";
                                        }
                                    }else {
                                        status = "null";
                                    }

                                    if (hotelObject.has("rating")){
                                        rating = hotelObject.getString("rating");
                                    }else {
                                        rating = "0";
                                    }

                                    if (hotelObject.has("photos")){
                                        JSONArray photoObjectArray = hotelObject.getJSONArray("photos");
                                        JSONObject photoObject = photoObjectArray.getJSONObject(0);
                                        photourl = photoURL + photoObject.getString("photo_reference");
                                    }else {
                                        photourl = "null";
                                    }
                                    contentList.add(new Content(contentName,editaddress(shortAddress),rating,photourl,status));

                                    adapter.notifyDataSetChanged();

                                }
                            }else {
                                Toast.makeText(CategoryList.this, "In else condition", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            Toast.makeText(CategoryList.this, e.toString(), Toast.LENGTH_SHORT).show();
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(CategoryList.this, "onError", Toast.LENGTH_SHORT).show();

            }
        });

        requestQueue.add(stringRequest);





    }
    String editaddress(String[] original){
        String edited;
        int len = original.length;
        if (len > 2) {
            if (!original[len - 2].equals(original[len - 1])) {
                edited = original[len - 2] + "," + original[len - 1];
            } else if(!original[len - 2].equals("")) {
                edited = original[len - 3] + "," + original[len - 1];
            }else {
                edited = original[len - 1];
            }
        } else {
            edited = original[len - 1];
        }
        return edited;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.back_in,R.anim.back_out);
    }
}
