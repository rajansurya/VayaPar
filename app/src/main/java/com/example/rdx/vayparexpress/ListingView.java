package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import data.model.BussinessDetailListing;
import data.model.BussinessDetailListingData;

/**
 * Created by RDX on 12/26/2017.
 */
public class ListingView extends Activity implements ListingAdapter.clickListner, View.OnClickListener, SignUpAPICall.CallBackToClass {
    private RecyclerView recycler_view;
    private ArrayList<BussinessDetailListingData> listingdata;
    //    private Toolbar toolbar;
    private TextView nocontent, sortby, filter, seachresult, backto;
    private ListingAdapter listingAdapter;
    private BussinessDetailListing myParcelableObject;
    private String DATA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listing_view);
        Gson gson = new Gson();
        String data = "";
        if (MainActivity.DataHolder.hasData()) {
            data = MainActivity.DataHolder.getData();
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey("DATA")) {
            DATA = bundle.getString("DATA");
        }
        backto = (TextView) findViewById(R.id.backto);
        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ListingView.this.finish();
            }
        });
        seachresult = (TextView) findViewById(R.id.seachresult);
        seachresult.setText("Search result found for " + DATA);
        myParcelableObject = gson.fromJson(data, BussinessDetailListing.class);
        listingdata = myParcelableObject.getObject();
        sortby = (TextView) findViewById(R.id.sortby);

        sortby.setOnClickListener(this);
        filter = (TextView) findViewById(R.id.filter);
        filter.setOnClickListener(this);
        nocontent = (TextView) findViewById(R.id.nocontent);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Business Listing");
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // back button pressed
//                ListingView.this.finish();
//            }
//        });
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recycler_view.getContext(),
                DividerItemDecoration.VERTICAL);
//        recycler_view.addItemDecoration(dividerItemDecoration,R.drawable.round_background);

        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        listingAdapter = new ListingAdapter(listingdata, ListingView.this, this);
        recycler_view.setAdapter(listingAdapter);
        if (listingdata != null && listingdata.size() > 0) {

        } else {
            nocontent.setVisibility(View.VISIBLE);
            recycler_view.setVisibility(View.GONE);
        }

    }

    public static Rect locateView(View v) {
        int[] loc_int = new int[2];
        if (v == null) return null;
        try {
            v.getLocationOnScreen(loc_int);
        } catch (NullPointerException npe) {
            //Happens when the view doesn't exist on screen anymore.
            return null;
        }
        Rect location = new Rect();
        location.left = loc_int[0];
        location.top = loc_int[1];
        location.right = location.left + v.getWidth();
        location.bottom = location.top + v.getHeight();
        return location;
    }

    private void displayPopupWindow(View anchorView) {
        final PopupWindow popup = new PopupWindow(ListingView.this);
        View layout = getLayoutInflater().inflate(R.layout.popup_content, null);
        popup.setContentView(layout);
        TextView atoz = (TextView) layout.findViewById(R.id.atoz);
        atoz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(listingdata, new Comparator<BussinessDetailListingData>() {
                    @Override
                    public int compare(BussinessDetailListingData s1, BussinessDetailListingData s2) {
                        return (s1.getCompanyName().compareToIgnoreCase(s2.getCompanyName()));
                    }
                });
                recycler_view.setAdapter(new ListingAdapter(listingdata, ListingView.this, ListingView.this));
                popup.dismiss();
            }
        });
        TextView ztoa = (TextView) layout.findViewById(R.id.ztoa);
        ztoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(listingdata, new Comparator<BussinessDetailListingData>() {
                    @Override
                    public int compare(BussinessDetailListingData s1, BussinessDetailListingData s2) {
                        int b = s1.getCompanyName().compareTo(s2.getCompanyName());
                        if (b > 0) {
                            return -1;
                        } else if (b == 0) {
                            return 0;

                        } else if (b < 0) {
                            return 1;
                        }
                        return s1.getCompanyName().compareTo(s2.getCompanyName());
                    }
                });
                recycler_view.setAdapter(new ListingAdapter(listingdata, ListingView.this, ListingView.this));
                popup.dismiss();
            }
        });

        // Set content width and height
        popup.setHeight(150);
        popup.setWidth(250);
        // Closes the popup window when touch outside of it - when looses focus
        popup.setOutsideTouchable(true);
        popup.setFocusable(true);
        popup.setBackgroundDrawable(new BitmapDrawable());
        // Show anchored to button
        popup.showAtLocation(anchorView, Gravity.TOP | Gravity.LEFT, locateView(anchorView).left + 100,
                locateView(anchorView).bottom);

        popup.showAsDropDown(anchorView);
    }

    @Override
    public void onclickListner(View view) {
        int itemPosition = recycler_view.getChildLayoutPosition(view);
        BussinessDetailListingData data = listingdata.get(itemPosition);
        Intent intent = new Intent(ListingView.this, BusinessDetail.class);
        intent.putExtra("DETAILDATA", data);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.sortby:
                displayPopupWindow(view);
                break;
            case R.id.filter:
                startActivityForResult(new Intent(ListingView.this, FilterActivity.class), 1001);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1001 && resultCode == RESULT_OK) {
            String district = data.getStringExtra("district");
            String city = data.getStringExtra("city");
            System.out.println("district+district" + district + city);
            try {
                String URL = BuildConfig.SERVER_URL + "public/api/businesslist";
                JSONObject jdata = new JSONObject();
                jdata.put("state", MainActivity.selectedStateID);
                jdata.put("district", TextUtils.isEmpty(district) == true ? "" : district);
                jdata.put("city", TextUtils.isEmpty(city) == true ? "" : city);
                jdata.put("text", MainActivity.searchlocationtext);
                new SignUpAPICall(URL, 0, "SEARCH_BUSSINESS", ListingView.this, this).execute(jdata);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void returnDataToClass(String tag, String data) {
        switch (tag) {
            case "SEARCH_BUSSINESS":
                try {
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    myParcelableObject = gson.fromJson(ob.toString(), BussinessDetailListing.class);
                    listingAdapter.updateView(myParcelableObject.getObject());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}