package com.example.rdx.vayparexpress;//package com.example.rdx.vayparexpress;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import data.model.Categories;
//import data.model.Sub_CategoryList;
//
///**
// * Created by stpl on 7-12-2017.
// */
//public class CategoriesAdapter extends RecyclerView.ViewHolder {
//    private Context mContext;
//    private LayoutInflater layoutInflater;
//    Sub_CategoryList gsonObj;
//    public clickListner clickListner;
//
//
//    interface clickListner {
//        public void onclickListner(int position);
//    }
//    // Constructor
//    public CategoriesAdapter(Context c, Sub_CategoryList gsonObj, clickListner clickListner) {
//        mContext = c;
//        this.gsonObj = gsonObj;
//        this.clickListner=clickListner;
//    }
//
//    public int getCount() {
//        return gsonObj.getObject().size();
//    }
//
//    public Object getItem(int position) {
//        return null;
//    }
//
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    // create a new ImageView for each item referenced by the Adapter
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        if (convertView == null) {
//            layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//            convertView = layoutInflater.inflate(R.layout.categories_view, parent, false);
//            ImageView catimg = (ImageView) convertView.findViewById(R.id.cat_img);
//            catimg.setBackgroundResource(mThumbIds[position]);
////            setImageDrawable(mContext.getDrawable(mThumbIds[position]));
//            TextView catname = (TextView) convertView.findViewById(R.id.catname);
//            catname.setText(gsonObj.getObject().get(position).getCategory());
//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    clickListner.onclickListner(position);
//                }
//            });
//        }
////        else
////        {
////            imageView = (ImageView) convertView;
////        }
//
//        return convertView;
//    }
//
//    // Keep all Images in array
//    public Integer[] mThumbIds = {
//            R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour,R.drawable.banner3, R.drawable.pluses,
//            R.drawable.flour
//    };
//}