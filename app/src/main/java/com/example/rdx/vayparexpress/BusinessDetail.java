package com.example.rdx.vayparexpress;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import data.model.BussinessDetailListingData;

/**
 * Created by RDX on 12/26/2017.
 */
public class BusinessDetail extends Activity implements View.OnClickListener {
    private TextView companyName, subcotegory, contact_person, email, website_link, dealswith, address, cotegory, location, district,  phoneno1,phoneno2;//state
    private ImageView logo;
    private LinearLayout emaillink,website,call;
    //    private Toolbar toolbar;
    private BussinessDetailListingData myParcelableObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.business_detail_view);
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Business Detail");
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // back button pressed
//                BusinessDetail.this.finish();
//            }
//        });
        Intent i = getIntent();
        myParcelableObject = (BussinessDetailListingData) i.getParcelableExtra("DETAILDATA");
        System.out.println(myParcelableObject.getAddress());
        logo = (ImageView) findViewById(R.id.logo);
        subcotegory = (TextView) findViewById(R.id.subcotegory);
        contact_person = (TextView) findViewById(R.id.contact_person);
        companyName = (TextView) findViewById(R.id.companyName);
        address = (TextView) findViewById(R.id.address);
        emaillink = (LinearLayout) findViewById(R.id.emaillink);
        email = (TextView) findViewById(R.id.email);
        call = (LinearLayout) findViewById(R.id.call);
        website = (LinearLayout) findViewById(R.id.website);
        website_link = (TextView) findViewById(R.id.website_link);
        cotegory = (TextView) findViewById(R.id.cotegory);
        location = (TextView) findViewById(R.id.location);
        district = (TextView) findViewById(R.id.district);
        //state = (TextView) findViewById(R.id.state);
        phoneno1 = (TextView) findViewById(R.id.phoneno1);
        phoneno2 = (TextView) findViewById(R.id.phoneno2);
        dealswith = (TextView) findViewById(R.id.dealswith);

        Glide.with(BusinessDetail.this).load(myParcelableObject.getLogo()).placeholder(R.drawable.noimage).into(logo);
        address.setText(myParcelableObject.getAddress());
        email.setText(myParcelableObject.getEmail());
//        call.setText(myParcelableObject.getMobile());
        call.setOnClickListener(this);
        companyName.setText(myParcelableObject.getCompanyName());
//        String text = "<a href=" + myParcelableObject.getWebsite() + "> " + myParcelableObject.getWebsite() + " </a>";
//        website.setClickable(true);
//        website.setMovementMethod(LinkMovementMethod.getInstance());
        website_link.setText(myParcelableObject.getWebsite());//Html.fromHtml(text)
        website.setOnClickListener(this);
        emaillink.setOnClickListener(this);
        cotegory.setText(myParcelableObject.getCategory());
        location.setText(myParcelableObject.getLocation());
        district.setText(myParcelableObject.getDistrict());
        //state.setText(myParcelableObject.getState());
        phoneno1.setText(myParcelableObject.getMobile());
        phoneno2.setText(myParcelableObject.getPhone());
        dealswith.setText("Mfrs. of "+myParcelableObject.getDealwith());
        subcotegory.setText(myParcelableObject.getSubcategory());
        contact_person.setText(myParcelableObject.getContactperson());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.call:
                if (isPermissionGranted()) {
                    call_action();
                }
                break;
            case R.id.website:
                if (!TextUtils.isEmpty(myParcelableObject.getWebsite())) {
                    String url = myParcelableObject.getWebsite();
                    if (!url.startsWith("http://") && !url.startsWith("https://"))
                        url = "http://" + url;
                    Intent i = new Intent(Intent.ACTION_VIEW);
                    i.setData(Uri.parse(url));
                    startActivity(i);
                }
                break;
            case R.id.emaillink:
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", myParcelableObject.getEmail(), null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
                break;
        }
    }

    public void call_action() {
        String phnum = myParcelableObject.getMobile();
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + phnum));
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
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
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

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
