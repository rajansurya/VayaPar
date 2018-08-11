package com.example.rdx.vayparexpress;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import data.model.BussinessDetailListingData;

/**
 * Created by RDX on 11-08-2018.
 */
public class MyViewPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    ArrayList<BussinessDetailListingData> object;
    Context context;
    public MyViewPagerAdapter(ArrayList<BussinessDetailListingData> object,Context context) {
        this.object = object;
        this.context=context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = layoutInflater.inflate(R.layout.ads_view, container, false);
        ImageView ads = (ImageView) view.findViewById(R.id.ads);
        ads.setImageResource(R.drawable.noimage);
        ads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BussinessDetailListingData data = object.get(position);
                Intent intent = new Intent(context, BusinessDetail.class);
                intent.putExtra("DETAILDATA", data);
                context.startActivity(intent);
            }
        });
        Glide.with(context).load(object.get(position).getBanner()).placeholder(R.drawable.noimage).into(ads);
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        return object.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }
}

