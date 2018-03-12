package com.example.smita.mycity;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private List<Content> contentList;
    private static Context mcontext;


    public CategoryAdapter(Context context,List<Content> contentList) {
        this.contentList = contentList;
        this.mcontext = context;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView contentNameTextView, contentAddressTextView, contentRatingTextView, contentStatus;
        RatingBar ratingRateBar;
        ImageView icon;
        RelativeLayout contentClickLayout;

        public ViewHolder(final View v) {
            super(v);
            contentNameTextView = (TextView) v.findViewById(R.id.contentName);
            contentAddressTextView = (TextView) v.findViewById(R.id.contentAddr);
            contentRatingTextView = (TextView) v.findViewById(R.id.ratingText);
            ratingRateBar = (RatingBar) v.findViewById(R.id.ratingStars);
            icon = (ImageView) v.findViewById(R.id.contentImage);
            contentStatus = (TextView) v.findViewById(R.id.contentStatus);
            contentClickLayout = (RelativeLayout) v.findViewById(R.id.clickRelativeLay);
        }
    }



    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryAdapter.ViewHolder holder, int position) {

        final Content content = contentList.get(position);
        holder.contentNameTextView.setText(content.getContentName());
        holder.contentAddressTextView.setText(content.getContentAddress());
        float rate = Float.parseFloat(content.getContentRating());
        if (rate != 0){
            rate = rate * 1.0f;
            String textRate = String.valueOf(rate);
            content.setContentRating(textRate);
            holder.contentRatingTextView.setText(content.getContentRating());
        }else {
            holder.contentRatingTextView.setText("NA");
        }
        holder.ratingRateBar.setRating(rate);

        String imageAddress = content.getContentImgUrl();
        if (imageAddress.equals("null")){
            Glide.with(mcontext).load(R.drawable.nothing).into(holder.icon);
        }else {
            Glide.with(mcontext).load(imageAddress).into(holder.icon);
        }

        String statusNow = content.getContentStatus();
        if (!statusNow.equals("null")){
            if (statusNow.equals("Open")){
                holder.contentStatus.setText(statusNow);
                holder.contentStatus.setTextColor(mcontext.getResources().getColor(R.color.green));
            }else {
                holder.contentStatus.setText(statusNow);
                holder.contentStatus.setTextColor(mcontext.getResources().getColor(R.color.red));
            }
        }

        holder.contentClickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mcontext,DetailsActivity.class);
                mcontext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return contentList.size();
    }
}
