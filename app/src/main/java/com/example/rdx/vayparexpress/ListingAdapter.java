package com.example.rdx.vayparexpress;

/**
 * Created by RDX on 12/26/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.bumptech.glide.Glide;

import java.util.List;

import data.model.BussinessDetailListingData;

public class ListingAdapter extends RecyclerView.Adapter<ListingAdapter.MyViewHolder> implements View.OnClickListener {
    private Context context;
    private List<BussinessDetailListingData> moviesList;
    public clickListner clickListner;

    @Override
    public void onClick(View view) {
        clickListner.onclickListner(view);
    }

    interface clickListner {
        public void onclickListner(View view);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView companyName, address, dealswith;//, email, mobileno;
        ImageView logo;

        public MyViewHolder(View view) {
            super(view);
            logo = (ImageView) view.findViewById(R.id.logo);
            companyName = (TextView) view.findViewById(R.id.companyName);
            address = (TextView) view.findViewById(R.id.address);
            dealswith = (TextView) view.findViewById(R.id.dealswith);
//            mobileno = (TextView) view.findViewById(R.id.mobileno);
        }
    }


    public ListingAdapter(List<BussinessDetailListingData> moviesList, Context context, clickListner clickListner) {
        this.clickListner = clickListner;
        this.context = context;
        this.moviesList = moviesList;
    }

    public void updateView(List<BussinessDetailListingData> moviesList) {
        this.moviesList.clear();
        this.moviesList.addAll(moviesList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_row, parent, false);
        itemView.setOnClickListener(this);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        BussinessDetailListingData movie = moviesList.get(position);
        holder.companyName.setText(movie.getCompanyName());
        holder.address.setText(movie.getLocation());//+ " " + movie.getDistrict() + " " + movie.getState()
        holder.dealswith.setText("Deals in - "+movie.getDealwith());
//        holder.mobileno.setText(movie.getMobile());
        String []splitedata=movie.getCompanyName().trim().split(" ");
        String shortname="";
        if (splitedata.length>=2){
            shortname=splitedata[0].substring(0,1).toUpperCase();
            String secondst=splitedata[1];
            if (!TextUtils.isEmpty(secondst))
            shortname=shortname+secondst.substring(0,1).toUpperCase();
        }else if (splitedata.length==1){
            shortname=splitedata[0].substring(0,1).toUpperCase();
        }


        TextDrawable drawable = TextDrawable.builder()
                .buildRoundRect(shortname, context.getColor(R.color.header),14);
        holder.logo.setImageDrawable(drawable);
//        Glide.with(context).load(movie.getLogo()).placeholder(R.drawable.noimage).into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}