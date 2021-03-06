package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AutoCompleteTextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import data.model.BussinessDetailListing;
import data.model.BussinessDetailListingData;
import data.model.DealsWithList;

/**
 * Created by Rajan Gupta on 02-07-2018.
 */

public class SearchYourBusiness extends Activity implements CallBackToLocation, View.OnClickListener, SignUpAPICall.CallBackToClass {
    private AutoCompleteTextView searchlocation, dealsWith;
    private boolean isSearching_Location, isSearching_dealswith;
    private ViewPager viewPager;//ads2
    private MyViewPagerAdapter myViewPagerAdapter;
    //    public static String searchlocationtext;
//    private ImageView search_location;
    private int currentPage = 0, currentPage1 = 0;
    private Timer timer, timer2;
    private final long DELAY_MS = 600;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 3000;
    private int dotSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);
//        search_location = (ImageView) findViewById(R.id.search_location);
//        search_location.setOnClickListener(this);
        dealsWith = (AutoCompleteTextView) findViewById(R.id.dealsWith);
        searchlocation = (AutoCompleteTextView) findViewById(R.id.searchlocation);
        searchlocation.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)&& charSequence.length() > 2) {//&& charSequence.length() > 1
                    if (!isSearching_Location) {
                        try {
                            isSearching_Location = true;
                            String URL = BuildConfig.SERVER_URL + "public/api/autocomplete";
                            JSONObject data = new JSONObject();
                            data.put("word", charSequence);
                            new SignUpAPICallLocation(URL, 0, "SEARCH_WORD", SearchYourBusiness.this, SearchYourBusiness.this).execute(data);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        dealsWith.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!TextUtils.isEmpty(charSequence)&& charSequence.length() > 2) {
                    if (!isSearching_dealswith) {
                        try {
                            isSearching_dealswith = true;
                            String URL = BuildConfig.SERVER_URL + "public/api/autocompletesubcategory";
                            JSONObject data = new JSONObject();
                            data.put("word", charSequence);
                            new SignUpAPICallLocation(URL, 0, "SEARCH_DEALSWITH", SearchYourBusiness.this, SearchYourBusiness.this).execute(data);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        viewPager=(ViewPager)findViewById(R.id.view_pager);
        myViewPagerAdapter = new MyViewPagerAdapter(new ArrayList<BussinessDetailListingData>(),SearchYourBusiness.this);
        viewPager.setAdapter(myViewPagerAdapter);
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == dotSize) {
                    currentPage = 0;
                }
                viewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled

            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);

        String ads = BuildConfig.SERVER_URL + "public/api/adslist/1";
        new SignUpAPICall(ads, 1, "ads", SearchYourBusiness.this, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new JSONObject());

    }

    @Override
    public void returnDataToLocationSearch(String tag, String data) {
        switch (tag) {
            case "SEARCH_WORD":
                try {
                    System.out.println("isSearching_Location" + isSearching_Location);
                    isSearching_Location = false;
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    BussinessDetailListing gsonObj = gson.fromJson(ob.toString(), BussinessDetailListing.class);
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < gsonObj.getObject().size(); i++) {
                        list.add(gsonObj.getObject().get(i).getCompanyName());
                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.location_suggetion_view, R.id.text1, list);
                    CustomerAdapter autoadpL = new CustomerAdapter(SearchYourBusiness.this, R.layout.location_suggetion_view, list, searchlocation.getText().toString());

                    searchlocation.setAdapter(autoadpL);
                    autoadpL.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "SEARCH_DEALSWITH":
                try {
                    isSearching_dealswith = false;
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    DealsWithList gsonObj = gson.fromJson(ob.toString(), DealsWithList.class);
                    ArrayList<String> list = new ArrayList<>();
                    for (int i = 0; i < gsonObj.getObject().size(); i++) {
                        list.add(gsonObj.getObject().get(i).getDealwith());
                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.location_suggetion_view, R.id.text1, list);
                    CustomerAdapter autoadp = new CustomerAdapter(SearchYourBusiness.this, R.layout.location_suggetion_view, list, dealsWith.getText().toString());
                    dealsWith.setAdapter(autoadp);
                    autoadp.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;


        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_location:
                break;

        }
    }

    public void callAPICategory(View view) {
        try {
//            searchlocationtext = searchlocation.getText().toString();
            String URL = BuildConfig.SERVER_URL + "public/api/businesslist";
            JSONObject data = new JSONObject();
//            String dd = state.getSelectedItem().toString();
            data.put("state", MainActivity.selectedStateID);//getKeyByValue(statelisthas, dd)
            data.put("district", MainActivity.districtID);
            data.put("city", MainActivity.cityID);
            data.put("catid", dealsWith.getText().toString());
            System.out.println("data " + data);
            new SignUpAPICall(URL, 0, "SEARCH_BUSSINESS", SearchYourBusiness.this, SearchYourBusiness.this).execute(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callAPIBusiness(View view) {
        try {
//            searchlocationtext = searchlocation.getText().toString();
            String URL = BuildConfig.SERVER_URL + "public/api/businesslist";
            JSONObject data = new JSONObject();
//            String dd = state.getSelectedItem().toString();
            data.put("state", MainActivity.selectedStateID);//getKeyByValue(statelisthas, dd)
            data.put("district", MainActivity.districtID);
            data.put("city", MainActivity.cityID);
            data.put("text", searchlocation.getText().toString());
            System.out.println("data " + data);
            new SignUpAPICall(URL, 0, "SEARCH_BUSSINESS", SearchYourBusiness.this, SearchYourBusiness.this).execute(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getKeyByValue(HashMap<String, String> val, String value) {
        for (Map.Entry<String, String> entry : val.entrySet()) {
            if (entry.getValue().equals(value)) {
                return entry.getKey();
            }
        }
        return "";
    }

    @Override
    public void returnDataToClass(String tag, String data) {
        switch (tag) {
            case "SEARCH_BUSSINESS":
                try {
                    JSONObject ob = new JSONObject(data);
//                    Gson gson = new Gson();
//                    BussinessDetailListing gsonObj = gson.fromJson(ob.toString(), BussinessDetailListing.class);
//                    BussinessDetailListingData gsonOb=gsonObj.getObject().get(0);
//                    ArrayList<BussinessDetailListingData> dd=new ArrayList<>();
//                    dd.add(gsonOb);
//                    gsonObj.setObject(dd);
                    MainActivity.DataHolder.setData(ob.toString());
                    Intent intent = new Intent(SearchYourBusiness.this, ListingView.class);
                    String dd = TextUtils.isEmpty(searchlocation.getText().toString()) ? dealsWith.getText().toString() : searchlocation.getText().toString();
                    intent.putExtra("DATA", dd);
                    startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "ads":
                try {
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    BussinessDetailListing adsList = gson.fromJson(ob.toString(), BussinessDetailListing.class);
                    dotSize = adsList.getObject().size();
                    myViewPagerAdapter = new MyViewPagerAdapter(adsList.getObject(),SearchYourBusiness.this);
                    viewPager.setAdapter(myViewPagerAdapter);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private class SignUpAPICallLocation extends AsyncTask<JSONObject, Void, String> {
        private String TAG;
        private CallBackToLocation fragment;
        private String URL;
        private Context context;
        int isPostOrGet;

        public SignUpAPICallLocation(String url, int isPostOrGet, String tag, CallBackToLocation fragment, Context context) {
            this.TAG = tag;
            this.fragment = fragment;
            this.URL = url;
            this.context = context;
            this.isPostOrGet = isPostOrGet;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(JSONObject... params) {
            return isPostOrGet == 0 ? HttpConnection.performPostCall(URL, params[0], context) : HttpConnection.performGetCall(URL, context);
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            fragment.returnDataToLocationSearch(TAG, s);
        }


    }
}
