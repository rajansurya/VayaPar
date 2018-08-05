package com.example.rdx.vayparexpress;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by RDX on 1/17/2018.
 */

public class CustomerAdapter extends ArrayAdapter<String> {
    private final String MY_DEBUG_TAG = "CustomerAdapter";
    private ArrayList<String> items;
    private ArrayList<String> itemsAll;
    private ArrayList<String> suggestions;
    private int viewResourceId;
    String text;

    public CustomerAdapter(Context context, int viewResourceId, ArrayList<String> items, String text) {
        super(context, viewResourceId, items);
        this.items = items;
        this.itemsAll = (ArrayList<String>) items.clone();
        this.suggestions = new ArrayList<String>();
        this.viewResourceId = viewResourceId;
        this.text = text;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(viewResourceId, null);
        }
        String customer = items.get(position);
        if (customer != null) {
            TextView customerNameLabel = (TextView) v.findViewById(R.id.text1);
            if (customerNameLabel != null) {
//              Log.i(MY_DEBUG_TAG, "getView Customer Name:"+customer.getName());
                if (!TextUtils.isEmpty(text) && !TextUtils.isEmpty(customer)) {
//                    System.out.println("text" + text);
                    int startingIndex = customer.toLowerCase().indexOf(text.toLowerCase());
                    int endingIndex = startingIndex + text.length();

                    SpannableStringBuilder str = new SpannableStringBuilder(customer);
//                    System.out.println("text" + text);
//                    System.out.println("text" + startingIndex);
//                    System.out.println("text" + endingIndex);
//                    System.out.println("text" + customer);
                    if (startingIndex > -1 && endingIndex > -1)
                        str.setSpan(new ForegroundColorSpan(Color.RED), startingIndex, endingIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    customerNameLabel.setText(str);
                } else {
                    customerNameLabel.setText(customer);
                }

            }
        }
        return v;
    }

    @Override
    public Filter getFilter() {
        return nameFilter;
    }


    Filter nameFilter = new Filter() {
        @Override
        public String convertResultToString(Object resultValue) {
            String str = ((String) (resultValue));
            return str;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            if (constraint != null) {
                suggestions.clear();
                for (String customer : itemsAll) {
                    if (customer.toLowerCase().startsWith(constraint.toString().toLowerCase())) {
                        suggestions.add(customer);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<String> filteredList = (ArrayList<String>) results.values;
            if (results != null && results.count > 0) {
                clear();
                for (String c : filteredList) {
                    add(c);
                }
                notifyDataSetChanged();
            }
        }
    };

}
