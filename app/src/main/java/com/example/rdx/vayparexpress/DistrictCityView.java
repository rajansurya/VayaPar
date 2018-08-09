package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import data.model.Citylist_Data;
import data.model.Districtlist_Data;

/**
 * Created by RDX on 07-07-2018.
 */

public class DistrictCityView extends Activity {
    LinearLayout main_viewdist;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.distcity_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DistrictCityView.this.finish();
            }
        });

        String namestate = getIntent().getExtras().getString("namestate");
        toolbar.setTitle(namestate);
        main_viewdist = (LinearLayout) findViewById(R.id.main_viewdist);
        ArrayList<Districtlist_Data> districtlist_data = (ArrayList<Districtlist_Data>) getIntent().getSerializableExtra("allDistrict");
        int horizontal = 0;
        LinearLayout horizontallinear = null;
        for (final Districtlist_Data districts : districtlist_data) {
            if (districts.getCitylist() != null && districts.getCitylist().size() == 0) {
                continue;
            }
            if (horizontal == 0) {
                horizontallinear = null;
                horizontallinear = new LinearLayout(DistrictCityView.this);
                LinearLayout.LayoutParams layoutParams1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                horizontallinear.setLayoutParams(layoutParams1);
                horizontallinear.setWeightSum(3f);
                horizontallinear.setOrientation(LinearLayout.HORIZONTAL);

            }


            LinearLayout linearLayout = new LinearLayout(DistrictCityView.this);
            LinearLayout.LayoutParams childparam = (LinearLayout.LayoutParams) horizontallinear.getLayoutParams();
            childparam.weight = 1;
            linearLayout.setLayoutParams(childparam);
            linearLayout.setOrientation(LinearLayout.VERTICAL);

            TextView textView = new TextView(DistrictCityView.this);
            textView.setTypeface(null, Typeface.BOLD);
            textView.setText(districts.getDistrict().toUpperCase());
            textView.setTextSize(15f);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            params.setMargins(2, 2, 2, 17);
            textView.setLayoutParams(params);
            textView.setGravity(Gravity.CENTER);
            textView.setBackground(getResources().getDrawable(R.drawable.testline));
            linearLayout.addView(textView);

            for (final Citylist_Data ob : districts.getCitylist()) {
                TextView textView1 = new TextView(DistrictCityView.this);
                textView1.setText(ob.getCity());
                textView1.setPadding(3, 5, 3, 5);
                textView1.setCompoundDrawablesWithIntrinsicBounds(getResources().getDrawable(R.drawable.blackdot), null, null, null);
                textView1.setCompoundDrawablePadding(3);
                linearLayout.addView(textView1);
                textView1.setTextSize(14f);
                textView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // System.out.println("VVVV  "+ob.getCityid()+" VVVVV "+districts.getDistrictid());
                        Intent intent = new Intent();
                        intent.putExtra("cityid",ob.getCityid());
                        intent.putExtra("districtid",districts.getDistrictid());
                        setResult(RESULT_OK,intent );
                        finish();
                    }
                });
            }
            horizontallinear.addView(linearLayout);
//            System.out.println("GGGGG");
            if (horizontal == 2)
                main_viewdist.addView(horizontallinear);
            horizontal++;
            if (horizontal == 3)
                horizontal = 0;

        }
    }
}
