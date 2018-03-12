package com.example.smita.mycity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class CustomGridAdapter extends BaseAdapter {
    private Context context;
    private final String[] menulist;
    private final int[] iconlist;

    public CustomGridAdapter(Context context, String[] menulist, int[] iconlist) {
        this.context = context;
        this.menulist = menulist;
        this.iconlist = iconlist;
    }

    @Override
    public int getCount() {
        return menulist.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        ViewHolder holder = null;

        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            /*grid = layoutInflater.inflate(R.layout.main_grid,null);*/
            convertView = layoutInflater.inflate(R.layout.main_grid,null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.menu);
            holder.image = (ImageView) convertView.findViewById(R.id.icon);
            
            convertView.setTag(holder);
        }else {
            /*grid = (View) convertView;*/
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText(menulist[position]);
        holder.image.setImageResource(iconlist[position]);
        return convertView;
    }

    static class ViewHolder {
        TextView text;
        ImageView image;
    }
}
