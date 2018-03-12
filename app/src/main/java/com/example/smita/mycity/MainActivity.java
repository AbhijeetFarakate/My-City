package com.example.smita.mycity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    protected String apiURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=18.498072,73.8328396&radius=1000&key=AIzaSyA78PaOND-Yoshet42MzYFN7yGt6YWE-x4&types=";

    protected static Integer[] mainViewimg = {R.drawable.travel,R.drawable.food,R.drawable.shopping};
    protected static final int [] iconImg = {R.drawable.clothing,R.drawable.diamond,R.drawable.graduate,R.drawable.medicine,R.drawable.pills,
                                    R.drawable.restaurant,R.drawable.shoppingcart,R.drawable.furniture,R.drawable.mobilestore,R.drawable.saloon,
                                    R.drawable.atm,R.drawable.gas_station,R.drawable.government,R.drawable.lawyer,R.drawable.bus,R.drawable.gym,
                                    R.drawable.store,R.drawable.real_estate};
    protected static final String[] menulist = {"Clothing","Jewellery Shop","School","Hospital","Medical Store","Restaurant","Shopping",
                                            "Furniture","Mobile Store","Saloon","ATM", "Gas Station","Government Office","Lawyer",
                                            "Transport","Gym","General Store","Real Estate"};

    private final String type[] = {"clothing_store","jewelry_store","school","hospital","pharmacy","restaurant","shopping_mall","furniture_store",
                            "electronics_store","hair_care","atm","gas_station","local_government_office","lawyer",
                            "bus_station","gym","convenience_store","real_estate_agency"};


    private List<Content> contentList = new ArrayList<>();

    ViewPager mainViewPager;
    GridView mainGridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*if (Build.VERSION.SDK_INT >= 21){

            TransitionInflater inflater = TransitionInflater.from(this);
            Transition transition = inflater.inflateTransition(R.transition.transition_a);
            getWindow().setExitTransition(transition);
            Slide slide = new Slide();
            slide.setDuration(5000);
            getWindow().setReenterTransition(slide);

        }*/

        setContentView(R.layout.activity_main);

        //Main Grid
        mainGridView = (GridView) findViewById(R.id.mainGridview);
        mainGridView.setAdapter(new CustomGridAdapter(this,menulist,iconImg));

        mainGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MainActivity.this,CategoryList.class);
                String completeUrl = apiURL+type[position];
                intent.putExtra("url",completeUrl);
                intent.putExtra("title",menulist[position]);
                /*ActivityOptionsCompat compat = ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,null);*/
                /*startActivity(intent,compat.toBundle());*/
                startActivity(intent);
                overridePendingTransition(R.anim.effect,R.anim.effecttwo);

            }
        });

        //Main image Slide
        mainViewPager = (ViewPager) findViewById(R.id.mainViewPager);
        mainViewPager.setAdapter(new MainViewPageAdapter(this));





    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        /*overridePendingTransition(R.anim.effect,R.anim.effecttwo);*/

    }
}
