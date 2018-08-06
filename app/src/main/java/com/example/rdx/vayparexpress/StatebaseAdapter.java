package com.example.rdx.vayparexpress;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by RDX on 1/15/2018.
 */

public class StatebaseAdapter extends BaseAdapter implements SpinnerAdapter {
    private  ArrayList<String> statelist;
    private  LayoutInflater layoutInflater;
    private Context context;

    StatebaseAdapter(ArrayList<String> statelist, Context context) {
        this.statelist = statelist;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
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
        LinearLayout mainview = (LinearLayout) view.findViewById(R.id.mainview);
        /*mainview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(context, view.getLeft()-(view.getWidth()*2),
                        view.getTop()+(view.getHeight()*2));
            }
        });*/
//        if (i % 2 == 0) {
//            mainview.setBackgroundColor(context.getResources().getColor(R.color.statebck));
//        } else {
//            mainview.setBackgroundColor(context.getResources().getColor(R.color.white));
//        }
        TextView tv = (TextView) view.findViewById(R.id.text1);
        tv.setText(statelist.get(i));
        return view;
    }

    int selectedItem = -1;

    @Override
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
    }
    public void showDialog(Context context, int x, int y){
        // x -->  X-Cordinate
        // y -->  Y-Cordinate
        Dialog dialog  = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_location);
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams wmlp = dialog.getWindow().getAttributes();
        wmlp.gravity = Gravity.TOP | Gravity.LEFT;
        wmlp.x = x;
        wmlp.y = y;

        dialog.show();
    }
}
