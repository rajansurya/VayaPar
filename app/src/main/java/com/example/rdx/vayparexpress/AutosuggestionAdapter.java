package com.example.rdx.vayparexpress;

import android.content.Context;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RDX on 1/15/2018.
 */

public class AutosuggestionAdapter extends BaseAdapter {
    private ArrayList<String> statelist;
    private LayoutInflater layoutInflater;
    private Context context;
    int endpostion;

    AutosuggestionAdapter(ArrayList<String> statelist, Context context, int endpostion) {
        this.statelist = statelist;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.endpostion = endpostion;
    }

    @Override
    public int getCount() {
        return statelist.size();
    }

    @Override
    public Object getItem(int i) {
        return statelist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.spinner_text_view, null);
//        LinearLayout mainview = (LinearLayout) view.findViewById(R.id.mainview);
//        if (i % 2 == 0) {
//            mainview.setBackgroundColor(context.getResources().getColor(R.color.statebck));
//        } else {
//            mainview.setBackgroundColor(context.getResources().getColor(R.color.white));
//        }
        TextView tv = (TextView) view.findViewById(R.id.text1);
        Spannable spanText = Spannable.Factory.getInstance().newSpannable(statelist.get(i));
        spanText.setSpan(new BackgroundColorSpan(0xFFFFFF00), 0, endpostion, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        tv.setText(statelist.get(i));
        return view;
    }

    int selectedItem = -1;

    /*@Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        View v = null;
        v = super.getDropDownView(position, null, parent);
        // If this is the selected item position
        if (position%2==0) {
            v.setBackgroundColor(context.getResources().getColor(R.color.statebck));
        } else {
            // for other views
            v.setBackgroundColor(context.getResources().getColor(R.color.white));

        }
        selectedItem = position;
        return v;
    }*/
}
