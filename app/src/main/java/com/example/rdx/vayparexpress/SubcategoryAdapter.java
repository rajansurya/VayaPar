package com.example.rdx.vayparexpress;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import data.model.Sub_Category;

/**
 * Created by RDX on 1/7/2018.
 */


/**
 * Created by RDX on 1/7/2018.
 */

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.ViewHolder> {

    private Context mContext;
    private LayoutInflater layoutInflater;
    private ArrayList<Sub_Category> gsonObj;
    public clickListner clickListner;
//    private int drawble = 54;

    interface clickListner {
        public void onclickListner(int position);
    }

    // Constructor
    public SubcategoryAdapter(Context c, ArrayList<Sub_Category> gsonObj, clickListner clickListner) {
        mContext = c;
        this.gsonObj = gsonObj;
        this.clickListner = clickListner;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.sub_category_item_view, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
//        if (position == 0)
//            drawble = 54;
        Sub_Category item = gsonObj.get(position);
        holder.text.setText(item.getCategory());
        int resID = mContext.getResources().getIdentifier("@drawable/" + "d_" + (position+54), "drawable", mContext.getPackageName());
        System.out.println("resID" + resID);
//        drawble++;
//        if (!(resID == 0))
            holder.image.setBackgroundResource(resID);
//        else
//            holder.image.setBackgroundResource(0);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onclickListner(position);
            }
        });
        holder.itemView.setTag(item);
    }

    @Override
    public int getItemCount() {
        return gsonObj.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.subcategoryicon);
            text = (TextView) itemView.findViewById(R.id.subcatname);
        }
    }



}
