package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.json.JSONObject;

import java.util.ArrayList;

import data.model.Sub_Category;
import data.model.Sub_CategoryList;

/**
 * Created by RDX on 1/7/2018.
 */

public class SubcategoryList extends Activity implements SubcategoryAdapter.clickListner, SignUpAPICall.CallBackToClass {
    private RecyclerView recycler_view;
    private ArrayList<Sub_Category> listingdata;
    private Toolbar toolbar;
    private TextView nocontent;
    private ImageView logo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.subcategory_view);
        Intent i = getIntent();
        Sub_CategoryList myParcelableObject = (Sub_CategoryList) i.getParcelableExtra("DATA");
        listingdata = myParcelableObject.getObject();
        nocontent = (TextView) findViewById(R.id.nocontent);
        logo = (ImageView) findViewById(R.id.logo);
        Glide.with(SubcategoryList.this).load(i.getStringExtra("Icon")).placeholder(R.drawable.noimage).into(logo);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(i.getStringExtra("title"));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                SubcategoryList.this.finish();
            }
        });
        recycler_view = (RecyclerView) findViewById(R.id.subcategory);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_view.setLayoutManager(mLayoutManager);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setAdapter(new SubcategoryAdapter(SubcategoryList.this, listingdata, SubcategoryList.this));
    }

    @Override
    public void onclickListner(int position) {
        try {
            String URL = BuildConfig.SERVER_URL +"public/api/businesslistbyid";
            JSONObject data = new JSONObject();
            System.out.println("ID" + listingdata.get(position).getId());
            data.put("subcatid", listingdata.get(position).getId());
            data.put("state", MainActivity.selectedStateID);
            new SignUpAPICall(URL, 0, "SEARCH_BUSSINESS", SubcategoryList.this, this).execute(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void returnDataToClass(String tag, String data) {
        switch (tag) {
            case "SEARCH_BUSSINESS":
                try {
                    JSONObject ob = new JSONObject(data);
//                    Gson gson = new Gson();
//                    BussinessDetailListing gsonObj = gson.fromJson(ob.toString(), BussinessDetailListing.class);
                    MainActivity.DataHolder.setData(ob.toString());
                    Intent intent = new Intent(SubcategoryList.this, ListingView.class);
//                    intent.putExtra("DATA", ob.toString());
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
