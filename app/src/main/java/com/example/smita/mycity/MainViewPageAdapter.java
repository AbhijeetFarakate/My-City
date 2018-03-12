package com.example.smita.mycity;


import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;


public class MainViewPageAdapter extends PagerAdapter{
    Context vcontext;
    LayoutInflater vinflater;


    public MainViewPageAdapter(Context vcontext) {
        this.vcontext = vcontext;
    }

    @Override
    public int getCount() {
        return MainActivity.mainViewimg.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        vinflater = (LayoutInflater) vcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = vinflater.inflate(R.layout.main_viewpager,null);
        ImageView main_viewImg = (ImageView)view.findViewById(R.id.main_viewImg);
        main_viewImg.setImageResource(MainActivity.mainViewimg[position]);


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view,0);



        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view =(View) object;
        viewPager.removeView(view);
    }
}
