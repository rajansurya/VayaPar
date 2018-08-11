package com.example.rdx.vayparexpress;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadPoolExecutor;

import data.model.BussinessDetailListing;
import data.model.BussinessDetailListingData;
import data.model.Categories;
import data.model.DistrictList;
import data.model.State;
import data.model.StateList;
import data.model.Sub_CategoryList;


public class MainActivity extends AppCompatActivity implements SignUpAPICall.CallBackToClass, MyRecyclerAdapter.clickListner, AdapterView.OnItemSelectedListener {
    private ViewPager viewPager;//ads2
    private MyViewPagerAdapter myViewPagerAdapter;
    private TextView[] dots;
    //    private int[] layouts;
    private DotsIndicator dots_indicator;
    private int currentPage = 0, currentPage1 = 0;
    private Timer timer, timer2;
    private final long DELAY_MS = 600;//delay in milliseconds before task is to be executed
    private final long PERIOD_MS = 3000;
    private int NUM_PAGES = 4;
    private ArrayAdapter<CharSequence> adapter;
    //    private Spinner district;
    public static Spinner state_dialog;
    public static MySpinner state;
    private RecyclerView gridview;
    private boolean showDrawer;
    private LinearLayout drawerRed, main_view;

    //    private int height, width;
    private String selectedstate;
    private ImageView search_location;
    private ImageView enquiry;
    //    private HashMap<String, String> districtlist = new HashMap<String, String>();
    public static HashMap<String, String> statelisthas = new HashMap<String, String>();
    private AutoCompleteTextView dealsWith;
    private Sub_CategoryList subCategoryList;
    private boolean isSearching_Location, isSearching_dealswith;
    private Categories categories2, categories1, categories;
    private String subcattitle;
    public static DistrictList districtList;
    public static String selectedStateID,districtID,cityID;
    private int dotSize;
    //    public static String URLstate;
    private StateList stateList;
    private StatebaseAdapter adp;
    private String subcategory_Icon;
    static int widthT, heightT;
    public static String searchlocationtext;
    private DrawerLayout drawer_layout;
    private float lastTranslate = 0.0f, slideOffsetT;
    boolean isget;
    int getWidthT;
    ImageView homeicon, emailiocn, call, website, rating, share;//overview
    boolean isrunning = false;
    TextView popular, othercat;

    //NavigationView navigation_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        popular = (TextView) findViewById(R.id.popular);
        othercat = (TextView) findViewById(R.id.othercat);
//        popular.setEnabled(true);

        popular.setSelected(true);
//        navigation_view=(NavigationView)findViewById(R.id.navigation_view);
        homeicon = (ImageView) findViewById(R.id.homeicon);
        //overview = (ImageView) findViewById(R.id.overviewt);
        emailiocn = (ImageView) findViewById(R.id.emailiocn);
        call = (ImageView) findViewById(R.id.call);
        website = (ImageView) findViewById(R.id.website);
        rating = (ImageView) findViewById(R.id.rating);
        share = (ImageView) findViewById(R.id.share);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        heightT = displayMetrics.heightPixels;
        widthT = displayMetrics.widthPixels;

//        LocationDialogFragment dialog = new LocationDialogFragment();
//        dialog.show(getSupportFragmentManager(), "dialog");
        enquiry = (ImageView) findViewById(R.id.enquiry);
        enquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //Do something after 100ms
                        Intent oo = new Intent(getApplicationContext(), Enquiry.class);
                        startActivity(oo);
                    }
                }, 100);

            }
        });
        dealsWith = (AutoCompleteTextView) findViewById(R.id.dealsWith);
        dealsWith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchYourBusiness.class));
            }
        });
//        searchlocation = (AutoCompleteTextView) findViewById(R.id.searchlocation);
        search_location = (ImageView) findViewById(R.id.search_location);
//        search_location.setOnClickListener(this);
        drawerRed = (LinearLayout) findViewById(R.id.drawerRed);
        main_view = (LinearLayout) findViewById(R.id.main_view);
        state = (MySpinner) findViewById(R.id.state);
//        district = (Spinner) findViewById(R.id.districk);
        gridview = (RecyclerView) findViewById(R.id.gridview);

        gridview.setLayoutManager(new GridLayoutManager(MainActivity.this, 2));
        int spanCount = 3; // 3 columns
        int spacing = 1; // 50px
        boolean includeEdge = true;
//        gridview.addItemDecoration(new SpacesItemDecoration( spacing));

        dots_indicator = (DotsIndicator) findViewById(R.id.dots_indicator);
        viewPager = (ViewPager) findViewById(R.id.view_pager);


        drawer_layout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, final float slideOffset) {
                System.out.println("slideOffset" + slideOffset);
//                System.out.println("drawerRed.getWidth()" + drawerRed.getWidth());
                if (!isget) {
                    isget = true;
                    getWidthT = drawerRed.getWidth();
                }
                float moveFactor = (getWidthT * slideOffset);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    main_view.setTranslationX(moveFactor);
                    homeicon.setRotation(slideOffset * 100);
                    drawerRed.setAlpha(slideOffset);
                    //overview.setScaleX((slideOffset));
                    emailiocn.setScaleX((slideOffset));
                    call.setScaleX((slideOffset));
                    website.setScaleX((slideOffset));
                    rating.setScaleX((slideOffset));
                    share.setScaleX((slideOffset));
                } else {
                    TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
                    anim.setDuration(0);
                    anim.setFillAfter(true);
                    main_view.startAnimation(anim);
                    lastTranslate = moveFactor;
                }
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                homeicon.setRotation(90);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                homeicon.setRotation(0);
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

//        ads2 = (ViewPager) findViewById(R.id.ads2);
//        layouts = new int[]{
//                R.layout.welcome_slide1,
//                R.layout.welcome_slide1,
//                R.layout.welcome_slide1,
//                R.layout.welcome_slide1};

        // adding bottom dots


        // making notification bar transparent
//        changeStatusBarColor();

        myViewPagerAdapter = new MyViewPagerAdapter(new ArrayList<BussinessDetailListingData>(),MainActivity.this);
        viewPager.setAdapter(myViewPagerAdapter);
//        ads2.setAdapter(myViewPagerAdapter);

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

/****************/
//        final Handler handler2 = new Handler();
//        final Runnable Update2 = new Runnable() {
//            public void run() {
//                if (currentPage1 == NUM_PAGES) {
//                    currentPage1 = 0;
//                }
////                System.out.println("currentPage1 " + currentPage1);
////                ads2.setCurrentItem(currentPage1++, true);
//            }
//        };
//
//        timer2 = new Timer(); // This will create a new Thread
//        timer2.schedule(new TimerTask() { // task to be scheduled
//
//            @Override
//            public void run() {
//                handler2.post(Update2);
//            }
//        }, DELAY_MS, PERIOD_MS);
     /*   drawerRed.postDelayed(new Runnable() {
                                  @Override
                                  public void run() {
                                      height = drawerRed.getWidth() + 1;
                                      width = drawerRed.getHeight();
                                  }
                              }

                , 100);*/
//        searchlocation.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (!TextUtils.isEmpty(charSequence)) {//&& charSequence.length() > 1
//                    if (!isSearching_Location) {
//                        try {
//                            isSearching_Location = true;
//                            String URL = BuildConfig.SERVER_URL + "public/api/autocomplete";
//                            JSONObject data = new JSONObject();
//                            data.put("word", charSequence);
//                            new SignUpAPICallLocation(URL, 0, "SEARCH_WORD", MainActivity.this, MainActivity.this).execute(data);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
//        dealsWith.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                if (!TextUtils.isEmpty(charSequence)) {
//                    if (!isSearching_dealswith) {
//                        try {
//                            isSearching_dealswith = true;
//                            String URL = BuildConfig.SERVER_URL + "public/api/autocompletedealwith";
//                            JSONObject data = new JSONObject();
//                            data.put("word", charSequence);
//                            new SignUpAPICallLocation(URL, 0, "SEARCH_DEALSWITH", MainActivity.this, MainActivity.this).execute(data);
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
        setBAckColor();
        String URL = BuildConfig.SERVER_URL + "public/api/allcategory/2";
        new SignUpAPICall(URL, 1, "category2", MainActivity.this, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        String URLstate = BuildConfig.SERVER_URL + "public/api/statedata";
        new SignUpAPICall(URLstate, 1, "state", MainActivity.this, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        String ads = BuildConfig.SERVER_URL + "public/api/adslist/0";
        new SignUpAPICall(ads, 1, "ads", MainActivity.this, this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,new JSONObject());
        popular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBAckColor();
                if (categories2 != null) {
                    setValues();
                } else {
                    String URL = BuildConfig.SERVER_URL + "public/api/allcategory/2";
                    new SignUpAPICall(URL, 1, "category2", MainActivity.this, MainActivity.this).execute();
                }

            }
        });
        othercat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popular.setBackgroundColor(getResources().getColor(R.color.white));
                popular.setTextColor(getResources().getColor(R.color.dot_light_screen1));
                othercat.setBackgroundColor(getResources().getColor(R.color.dot_light_screen1));
                othercat.setTextColor(getResources().getColor(R.color.white));
                if (categories1 != null) {
                    setValues1();
                } else {
                    String URL = BuildConfig.SERVER_URL + "public/api/allcategory/1";
                    new SignUpAPICall(URL, 1, "category1", MainActivity.this, MainActivity.this).execute();
                }


            }
        });
    }

    void setBAckColor() {
        popular.setBackgroundColor(getResources().getColor(R.color.dot_light_screen1));
        popular.setTextColor(getResources().getColor(R.color.white));
        othercat.setBackgroundColor(getResources().getColor(R.color.white));
        othercat.setTextColor(getResources().getColor(R.color.dot_light_screen1));
    }

    public void side_menucall(View view) {
        switch (view.getId()) {
            case R.id.overview_view:
                startActivity(new Intent(MainActivity.this, CompanyOverView.class));
                drawer_Click(null);
                break;
            case R.id.email_view:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "vyaparexpress50@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                drawer_Click(null);
                break;
            case R.id.call_view:
                if (isPermissionGranted()) {
                    call_action();
                }
                drawer_Click(null);
                break;
            case R.id.website_view:
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.vyaparexpress.co.in"));
                startActivity(browserIntent);
                drawer_Click(null);
                break;
            case R.id.rating_view:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + "com.whatsapp")));
                } catch (android.content.ActivityNotFoundException anfe) {
                    anfe.printStackTrace();
                }
                drawer_Click(null);
                break;
            case R.id.share_view:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT,
                        "Hey check out my app at: https://play.google.com/store/apps/details?id=com.google.android.apps.plus");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                drawer_Click(null);
                break;

        }
    }

    public void drawer_Click(View view) {

        if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
            drawerRed.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.image_click));
            drawer_layout.closeDrawer(Gravity.LEFT);
        } else {
            drawer_layout.openDrawer(Gravity.LEFT);
        }
    }

    public void call_action() {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + "919312625326"));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        startActivity(callIntent);
    }

    public boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG", "Permission is granted");
                return true;
            } else {

                Log.v("TAG", "Permission is revoked");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG", "Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getApplicationContext(), "Permission granted", Toast.LENGTH_SHORT).show();
                    call_action();
                } else {
                    Toast.makeText(getApplicationContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

        }
    }
  /*  public void onClickData(View view) {
        if (!showDrawer) {
            drawerRed.setVisibility(View.VISIBLE);
            Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_in);
            drawerRed.startAnimation(animZoomIn);
            main_view.animate().translationXBy(height);
            showDrawer = true;
        } else {
            main_view.animate().translationXBy(-height).withEndAction(new Runnable() {
                @Override
                public void run() {
                    Animation animZoomIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.zoom_out);
                    drawerRed.startAnimation(animZoomIn);
                    drawerRed.setVisibility(View.INVISIBLE);
                }
            });
            showDrawer = false;

        }
    }*/

    /*@Override
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
                    CustomerAdapter autoadpL = new CustomerAdapter(MainActivity.this, R.layout.location_suggetion_view, list, searchlocation.getText().toString());

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
                    CustomerAdapter autoadp = new CustomerAdapter(MainActivity.this, R.layout.location_suggetion_view, list, dealsWith.getText().toString());
                    dealsWith.setAdapter(autoadp);
                    autoadp.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }
    }*/

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//        System.out.println("state DDDD" + state.getSelectedItem().toString());
        selectedStateID = getKeyByValue(statelisthas, state.getSelectedItem().toString());
//        System.out.println("state DDDD" + selectedStateID);
        for (State statelist : stateList.getObject()) {
            if (selectedStateID.equals(statelist.getId())) {
//                URLstate = statelist.getSubcatlist();
                Intent intent = new Intent(MainActivity.this, DistrictCityView.class);
                intent.putExtra("allDistrict", statelist.getSubcatlist());
                intent.putExtra("namestate", statelist.getState());
                intent.putExtra("stateid", statelist.getId());
                startActivityForResult(intent, 301);
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            System.out.println("DII RES " + data.getStringExtra("districtid"));
            districtID=data.getStringExtra("districtid");
            cityID=data.getStringExtra("cityid");
        }
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    /*private void addBottomDots(int currentPage) {
        dots = new TextView[dotSize];

        int colorsActive = getResources().getColor(R.color.dot_dark_screen1);
        int colorsInactive = getResources().getColor(R.color.dot_dark_screen2);

        dotsLayout.removeAllViews();
        for (int i = 0; i < dotSize; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(45);
            dots[i].setTextColor(colorsInactive);
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive);
    }*/

    @Override
    public void returnDataToClass(String tag, String data) {
        switch (tag) {
            case "category2":
                try {
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();

                    categories2 = gson.fromJson(ob.toString(), Categories.class);
                    setValues();

//                    gridview.addItemDecoration(itemDecor);
                    // int[] rainbow = getResources().getIntArray(R.array.rainbow);
                    // int color = 0;
                    //final float scale = getResources().getDisplayMetrics().density;
                    /*for (int i = 0; i < categories.getObject().size(); i++) {
                        int pixels = (int) (79 * scale + 0.5f);
                        final RelativeLayout parenttop = new RelativeLayout(MainActivity.this);
                        parenttop.setTag(i);
                        RelativeLayout.LayoutParams paramstop = new RelativeLayout.LayoutParams(pixels, pixels);
                        parenttop.setLayoutParams(paramstop);
                        parenttop.setPadding(3, 0, 0, 0);

                        LinearLayout parent = new LinearLayout(MainActivity.this);
                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(pixels, pixels);
//                        params.setMargins(5, 15, 15, 15);
                        params.gravity = Gravity.CENTER;
                        parent.setLayoutParams(params);
                        parent.setOrientation(LinearLayout.VERTICAL);

                        parent.setBackground(getResources().getDrawable(R.drawable.rectangle_category));
//                        parent.setBackgroundColor(rainbow[color]);
                        color++;
                        if (color > 3)
                            color = 0;
                        parent.setGravity(Gravity.CENTER);

                        ImageView iv = new ImageView(MainActivity.this);
                        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams((int) (30 * scale + 0.5f), (int) (30 * scale + 0.5f));
                        layoutParams.gravity = Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL;
                        iv.setLayoutParams(layoutParams);
                        int resID = getResources().getIdentifier("@drawable/" + "d_" + categories.getObject().get(i).getId(), "drawable", getPackageName());
                        iv.setImageResource(resID);
                        TextView name = new TextView(MainActivity.this);
                        name.setGravity(Gravity.CENTER);
                        name.setTextColor(getResources().getColor(R.color.black));
                        name.setText(categories.getObject().get(i).getTitle());
                        System.out.println("NAME " + categories.getObject().get(i).getId());
                        parent.addView(iv);
                        parent.addView(name);
                        parenttop.addView(parent);
//                        if (i == 0) {
//                            ImageView arrow = new ImageView(MainActivity.this);
//                            RelativeLayout.LayoutParams layoutParamsparent = new RelativeLayout.LayoutParams((int) (10 * scale + 0.5f), (int) (10 * scale + 0.5f));
//                            layoutParamsparent.addRule(RelativeLayout.CENTER_VERTICAL);
//                            layoutParamsparent.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
//                            arrow.setLayoutParams(layoutParamsparent);
//                            arrow.setImageDrawable(getResources().getDrawable(R.drawable.round_dot));
//                            parenttop.addView(arrow);
//                        }

                        parenttop.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                onClickData(null);
                                onclickListner(Integer.parseInt(parenttop.getTag().toString()));
//                                arrow.setImageDrawable(getResources().getDrawable(R.drawable.round_dot));
                                //  String URLstate = categories.getObject().get(Integer.parseInt(parenttop.getTag().toString())).getSubcatlist();
                                // new SignUpAPICall(URLstate, 1, "SUBCATEGORY", MainActivity.this, MainActivity.this).execute();
                            }
                        });
                        drawerRed.addView(parenttop);
                    }*/

//                    String URLstate = categories.getObject().get(0).getSubcatlist();
//                    new SignUpAPICall(URLstate, 1, "SUBCATEGORY", MainActivity.this, MainActivity.this).execute();
//                    gridview.setAdapter(new CategoriesAdapter(this, gsonObj));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "category1":
                try {
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    categories1 = gson.fromJson(ob.toString(), Categories.class);
                    setValues1();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;

            case "state":
                try {
                    JSONObject ob = new JSONObject(data);
//                    Gson gson = new Gson();
                    GsonBuilder gsonBuilder = new GsonBuilder();

                    gsonBuilder.registerTypeAdapter(State.class, new OptionsDeserilizerDistirct());
                    Gson gson = gsonBuilder.create();
                    stateList = gson.fromJson(ob.toString(), StateList.class);
                    for (int i = 0; i < stateList.getObject().size(); i++) {
                        statelisthas.put(stateList.getObject().get(i).getId(), stateList.getObject().get(i).getState());
                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_text_view, R.id.text1, new ArrayList<>(statelist.values()));

                    ArrayList<String> listingdata = new ArrayList<String>(statelisthas.values());

                    System.out.println();
                    Collections.sort(listingdata, new Comparator<String>() {
                        @Override
                        public int compare(String s1, String s2) {
                            return (s1.compareToIgnoreCase(s2));
                        }
                    });


                    adp = new StatebaseAdapter(listingdata, MainActivity.this);
                    state.setAdapter(adp);
//                    state_dialog.setAdapter(adp);
                    state.setOnItemSelectedEvenIfUnchangedListener(MainActivity.this);
                    selectedStateID = getKeyByValue(statelisthas, state.getSelectedItem().toString());
                    System.out.println(selectedStateID +" responce state " + state.getSelectedItem().toString());

//                    state.setSelection(0);
//                    System.out.println("STATT  " + stateList.getObject().get(0).getSubcatlist());
//                    if (stateList.getObject() != null) {
//                        String URLstate = stateList.getObject().get(0).getSubcatlist();
//                        new SignUpAPICall(URLstate, 1, "DISTRICTLIST", MainActivity.this, this).execute();
//                    }
//                    selectedstate = state.getSelectedItem().toString();

//                    state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                        @Override
//                        public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                            // your code here
//                            System.out.println("state DDDD" + state.getSelectedItem().toString());
//                            selectedState = getKeyByValue(statelist, state.getSelectedItem().toString());
//                            for (State statelist : stateList.getObject()) {
//                                if (selectedState.equals(statelist.getId())) {
//                                    URLstate = statelist.getSubcatlist();
//                                    break;
//                                }
//                            }
////                             URLstate = stateList.getObject().get(position).getSubcatlist();
////                            if (!selectedstate.equals(state.getSelectedItem().toString())) {
////                                selectedstate = state.getSelectedItem().toString();
////                                String URLstate = stateList.getObject().get(position).getSubcatlist();
////                                new SignUpAPICall(URLstate, 1, "DISTRICTLIST", MainActivity.this, MainActivity.this).execute();
////                            }
//                        }
//
//                        @Override
//                        public void onNothingSelected(AdapterView<?> parentView) {
//                            // your code here
//                        }
//
//                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "DISTRICTLIST":
                try {
                    System.out.println("DIST   " + data);
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    districtList = gson.fromJson(ob.toString(), DistrictList.class);

//                    for (int i = 0; i < districtList.getObject().size(); i++) {
//                        districtlist.put(districtList.getObject().get(i).getId(), districtList.getObject().get(i).getState());
//                    }
//                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.district_view, new ArrayList<String>(districtlist.values()));
//                    district.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "SUBCATEGORY":
                try {
                    JSONObject ob = new JSONObject(data);
                    Gson gson = new Gson();
                    subCategoryList = gson.fromJson(ob.toString(), Sub_CategoryList.class);
                    Intent intent = new Intent(MainActivity.this, SubcategoryList.class);
                    intent.putExtra("DATA", subCategoryList);
                    intent.putExtra("title", subcattitle);
                    intent.putExtra("Icon", subcategory_Icon);
                    startActivity(intent);

//                    ArrayList<String> statelist = new ArrayList<>();
//                    for (int i = 0; i < gsonObj.getObject().size(); i++) {
//                        statelist.add(gsonObj.getObject().get(i).getCategory());
//                    }
                    // onClickData(null);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            case "SEARCH_BUSSINESS":
                try {
                    JSONObject ob = new JSONObject(data);
//                    Gson gson = new Gson();
//                    BussinessDetailListing gsonObj = gson.fromJson(ob.toString(), BussinessDetailListing.class);
//                    BussinessDetailListingData gsonOb=gsonObj.getObject().get(0);
//                    ArrayList<BussinessDetailListingData> dd=new ArrayList<>();
//                    dd.add(gsonOb);
//                    gsonObj.setObject(dd);
                    DataHolder.setData(ob.toString());
                    Intent intent = new Intent(MainActivity.this, ListingView.class);
//                    intent.putExtra("DATA", ob.toString());
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
                    myViewPagerAdapter = new MyViewPagerAdapter(adsList.getObject(),MainActivity.this);
                    viewPager.setAdapter(myViewPagerAdapter);
                    dots_indicator.setViewPager(viewPager);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;


        }
    }

    void setValues() {
        categories = null;
        categories =categories2;
                DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int[] colorarray = new int[categories2.getObject().size()];
        for (int i = 0; i < categories2.getObject().size(); i++) {
            colorarray[i] = generateRandomColor();
        }

        gridview.setAdapter(new MyRecyclerAdapter(this, categories2, this, width / 2, colorarray));
    }

    void setValues1() {categories = null;
        categories =categories1;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int[] colorarray = new int[categories1.getObject().size()];
        for (int i = 0; i < categories1.getObject().size(); i++) {
            colorarray[i] = generateRandomColor();
        }

        gridview.setAdapter(new MyRecyclerAdapter(this, categories1, this, width / 2, colorarray));
    }

    public int generateRandomColor() {
        // This is the base color which will be mixed with the generated one
        Random mRandom = new Random();
        final int baseColor = Color.WHITE;

        final int baseRed = Color.red(baseColor);
        final int baseGreen = Color.green(baseColor);
        final int baseBlue = Color.blue(baseColor);

        final int red = (baseRed + mRandom.nextInt(256)) / 2;
        final int green = (baseGreen + mRandom.nextInt(256)) / 2;
        final int blue = (baseBlue + mRandom.nextInt(256)) / 2;

        return Color.rgb(red, green, blue);
    }

    public enum DataHolder {
        INSTANCE;

        private String mObjectList;

        public static boolean hasData() {
            return INSTANCE.mObjectList != null;
        }

        public static void setData(final String objectList) {
            INSTANCE.mObjectList = objectList;
        }

        public static String getData() {
            final String retList = INSTANCE.mObjectList;
            INSTANCE.mObjectList = null;
            return retList;
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
    public void onBackPressed() {

        if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
            drawer_layout.closeDrawer(Gravity.LEFT);
            return;
        } else {
            super.onBackPressed();
        }
    }

  /*  @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_location:
                try {
                    searchlocationtext = searchlocation.getText().toString();
//                    if (!TextUtils.isEmpty(searchlocation.getText().toString())) {
                    String URL = BuildConfig.SERVER_URL + "public/api/businesslist";
                    JSONObject data = new JSONObject();
                    String dd = state.getSelectedItem().toString();
                    data.put("state", getKeyByValue(statelist, dd));
                    data.put("dealswith", dealsWith.getText().toString());
                    data.put("text", searchlocationtext);
                    new SignUpAPICall(URL, 0, "SEARCH_BUSSINESS", MainActivity.this, this).execute(data);
//                    } else {
//                        Toast.makeText(MainActivity.this, "Please enter search business", Toast.LENGTH_SHORT).show();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.enquiry:

                break;

        }
    }*/

    @Override
    public void onclickListner(int position) {
        try {
//            String URL = "http://beta.rabinas.co.in/vyaporadmin/public/api/businesslistbyid";
//            JSONObject data = new JSONObject();
//            System.out.println("ID" + subCategoryList.getObject().get(view).getId());
//            data.put("subcatid", subCategoryList.getObject().get(view).getId());
//            new SignUpAPICall(URL, 0, "SEARCH_BUSSINESS", MainActivity.this, this).execute(data);

            subCategoryList = null;
            subcategory_Icon = categories.getObject().get(position).getIcon();
            subcattitle = categories.getObject().get(position).getTitle();
            String URLstate = categories.getObject().get(position).getSubcatlist();
            new SignUpAPICall(URLstate, 1, "SUBCATEGORY", MainActivity.this, MainActivity.this).execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class LocationDialogFragment extends DialogFragment {
        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            return super.onCreateView(inflater, container, savedInstanceState);
        }

        @Override
        public void onResume() {
            ViewGroup.LayoutParams params = getDialog().getWindow().getAttributes();

            params.width = widthT - 150;
//            params.height = heightT/3;
            getDialog().getWindow().setAttributes((WindowManager.LayoutParams) params);

            super.onResume();
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            LayoutInflater inflater = getActivity().getLayoutInflater();

            View view = inflater.inflate(R.layout.dialog_location, null);
            state_dialog = (Spinner) view.findViewById(R.id.state_dialog);
            TextView cross = (TextView) view.findViewById(R.id.cross);
            TextView ok = (TextView) view.findViewById(R.id.ok);

            cross.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                }
            });
            ok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismiss();
                    state.setSelection(state_dialog.getSelectedItemPosition());
                }
            });
            // Inflate and set the layout for the dialog
            // Pass null as the parent view because its going in the dialog layout
            builder.setView(view);
//                    // Add action buttons
//                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int id) {
//                            state.setSelection(state_dialog.getSelectedItemPosition());
//                            // sign in the user ...
//                        }
//                    });
//                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int id) {
//                            getDialog().cancel();
//                        }
//                    });
            return builder.create();
        }
    }
}
