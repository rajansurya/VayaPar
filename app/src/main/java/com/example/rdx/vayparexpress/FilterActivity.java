package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import data.model.CityData;
import data.model.CityList;
import data.model.DistrictData;
import data.model.DistrictList;

/**
 * Created by stpl on 9-1-2018.
 */

public class FilterActivity extends Activity implements SignUpAPICall.CallBackToClass, View.OnClickListener {
    private FlowLayout flowLayout, flowcity;
    private boolean isshowall;
    private TextView clickedDistrictview, clickedCityView;
    private Toolbar toolbar;
    private TextView custom_toolbar_title, clearall;
    private HashMap<String, String> districtlist = new HashMap<String, String>();
    private DistrictList districtList;
    private CityList cityList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.filter_view);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Filter");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                FilterActivity.this.finish();
            }
        });
        custom_toolbar_title = (TextView) findViewById(R.id.custom_toolbar_title);
        custom_toolbar_title.setOnClickListener(this);
        clearall = (TextView) findViewById(R.id.clearall);
        clearall.setOnClickListener(this);
        flowLayout = (FlowLayout) findViewById(R.id.flowdistrict);
        flowcity = (FlowLayout) findViewById(R.id.flowcity);
//                                String URLstate = stateList.getObject().get(position).getSubcatlist();
//        new SignUpAPICall(MainActivity.URLstate, 1, "DISTRICTLIST", FilterActivity.this, FilterActivity.this).execute();


    }

    private void fillAutoSpacingLayout(ArrayList<DistrictData> dummyTexts) {

//        ArrayList<DistrictData> dummyTexts = MainActivity.districtList.getObject();//getResources().getStringArray(R.array.planets_array);
        if (!isshowall && dummyTexts.size() > 7) {
            for (int i = 0; i < 7; i++) {
                TextView textView = buildLabel(dummyTexts.get(i).getState(), dummyTexts.get(i).getSubcatlist(), dummyTexts.get(i).getId());
                if (i == 6)
                    textView = buildLabelPlus("... +");
                flowLayout.addView(textView);
            }
        } else {
            for (DistrictData data : dummyTexts) {
                TextView textView = buildLabel(data.getState(), data.getSubcatlist(), data.getId());
                flowLayout.addView(textView);
            }
            if (dummyTexts.size() > 7)
                flowLayout.addView(buildLabelPlus("... -"));
        }
    }

    private TextView buildLabelPlus(String sign) {
        final TextView textView = new TextView(this);
        textView.setText(sign);
        textView.setTextColor(getResources().getColor(R.color.white));
        textView.setTextSize(17);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
        textView.setPadding((int) dpToPx(8), (int) dpToPx(5), (int) dpToPx(8), (int) dpToPx(5));
        textView.setBackgroundResource(R.drawable.flow_rec);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textView.getText().toString().equals("... +")) {
                    isshowall = true;
                    flowLayout.removeAllViews();
                    fillAutoSpacingLayout(districtList.getObject());
                } else if (textView.getText().toString().equals("... -")) {
                    isshowall = false;
                    flowLayout.removeAllViews();
                    fillAutoSpacingLayout(districtList.getObject());
                }
            }
        });
        return textView;
    }

    private TextView buildLabel(String text, final String citylist, String tag) {
        final TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTag(tag);
        textView.setTextSize(13);//TypedValue.COMPLEX_UNIT_SP,
        textView.setPadding((int) dpToPx(8), (int) dpToPx(5), (int) dpToPx(8), (int) dpToPx(5));
        textView.setBackgroundResource(R.drawable.label_bg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickedDistrictview != null)
                    clickedDistrictview.setBackgroundResource(R.drawable.label_bg);
                textView.setBackgroundResource(R.drawable.flow_rec);
                clickedDistrictview = textView;
                String URLstate = citylist;
                new SignUpAPICall(URLstate, 1, "CITYLIST", FilterActivity.this, FilterActivity.this).execute();
            }
        });
        return textView;
    }

    private float dpToPx(float dp) {
        return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    private TextView buildLabelCity(String text, String tag) {
        final TextView textView = new TextView(this);
        textView.setText(text);
        textView.setTag(tag);
        textView.setTextSize(13);//TypedValue.COMPLEX_UNIT_SP,
        textView.setPadding((int) dpToPx(8), (int) dpToPx(5), (int) dpToPx(8), (int) dpToPx(5));
        textView.setBackgroundResource(R.drawable.label_bg);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickedCityView != null)
                    clickedCityView.setBackgroundResource(R.drawable.label_bg);
                textView.setBackgroundResource(R.drawable.flow_rec);
                clickedCityView = textView;
            }
        });
        return textView;
    }

    @Override
    public void returnDataToClass(String tag, String data) {


        switch (tag) {
            case "DISTRICTLIST":
                try {
                    System.out.println("DIST   " + data);
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    districtList = gson.fromJson(ob.toString(), DistrictList.class);
                    fillAutoSpacingLayout(districtList.getObject());
//                    for (int i = 0; i < districtList.getObject().size(); i++) {
//                        districtlist.put(districtList.getObject().get(i).getId(), districtList.getObject().get(i).getState());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.district_view, new ArrayList<String>(districtlist.values()));
//                    district.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "CITYLIST":
                System.out.println("DDDDDD" + data);
                try {
                    flowcity.removeAllViews();
                    System.out.println("DIST   " + data);
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    cityList = gson.fromJson(ob.toString(), CityList.class);
                    for (CityData text : cityList.getObject()) {
                        TextView textView = buildLabelCity(text.getCity(), text.getId());
                        flowcity.addView(textView);
                    }


                } catch (Exception e) {
                    flowcity.removeAllViews();
                    e.printStackTrace();
                }
                break;
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.custom_toolbar_title:
                Intent returnIntent = new Intent();
                if (clickedDistrictview != null)
                    returnIntent.putExtra("district", clickedDistrictview.getTag().toString());
                if (clickedCityView != null)
                    returnIntent.putExtra("city", clickedCityView.getTag().toString());
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
                break;
            case R.id.clearall:
                isshowall = false;
                clickedCityView = null;
                clickedDistrictview = null;
                flowLayout.removeAllViews();
                flowcity.removeAllViews();
                fillAutoSpacingLayout(districtList.getObject());
                break;
        }
    }
}
