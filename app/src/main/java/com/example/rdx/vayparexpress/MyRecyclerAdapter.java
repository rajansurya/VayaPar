package com.example.rdx.vayparexpress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

import java.util.ArrayList;
import java.util.Random;

import data.model.Categories;
import data.model.Category;

/**
 * Created by RDX on 1/7/2018.
 */

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> {

    private Context mContext;
    //    private LayoutInflater layoutInflater;
    private ArrayList<Category> gsonObj;
    public clickListner clickListner;
    private int width;
    private int[] colorarray;

    interface clickListner {
        public void onclickListner(int position);
    }

    // Constructor
    public MyRecyclerAdapter(Context c, Categories gsonObj, clickListner clickListner, int width,int[] colorarray) {
        mContext = c;
        this.gsonObj = gsonObj.getObject();
        this.clickListner = clickListner;
        this.width = width;
        this.colorarray=colorarray;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_view, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        Category item = gsonObj.get(position);
        holder.text.setText(item.getTitle());
//        int resID = mContext.getResources().getIdentifier("@drawable/" + "d_" + gsonObj.get(position).getId(), "drawable", mContext.getPackageName());
//        holder.image.setBackgroundResource(resID);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) (width), width-(width/4));
        holder.itemView.setLayoutParams(lp);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onclickListner(position);
            }
        });
        if (!TextUtils.isEmpty(item.getIcon())) {
            Glide
                    .with(mContext)
                    .load(item.getIcon())
                    .asBitmap()
                    .into(new SimpleTarget<Bitmap>(250, 250) {
                        @Override
                        public void onResourceReady(Bitmap resource, GlideAnimation glideAnimation) {
                            Drawable drawable = new BitmapDrawable(mContext.getResources(), resource);
                            holder.backgroundimg.setBackgroundDrawable(drawable);
                        }
                    });
        } else {
            holder.backgroundimg.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.noimage));
        }

        holder.cat_color.setBackgroundColor(colorarray[position]);

        holder.itemView.setTag(item);
    }



    @Override
    public int getItemCount() {
        return gsonObj.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView text;
        public FrameLayout backgroundimg;
        public RelativeLayout cat_color;

        public ViewHolder(View itemView) {
            super(itemView);
            cat_color = (RelativeLayout) itemView.findViewById(R.id.cat_color);
            backgroundimg = (FrameLayout) itemView.findViewById(R.id.backgroundimg);
            image = (ImageView) itemView.findViewById(R.id.cat_img);
            text = (TextView) itemView.findViewById(R.id.catname);
        }
    }
  /*  public Integer[] mThumbIds = {
            R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
            R.drawable.flour
    };*/

}
