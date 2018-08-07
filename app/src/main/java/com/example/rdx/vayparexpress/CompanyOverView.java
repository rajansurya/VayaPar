package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

/**
 * Created by Rajan Gupta on 15-05-2018.
 */

public class CompanyOverView extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.company_overview);
       /* WebView overview=(WebView)findViewById(R.id.overview);
        overview.loadData(getString(R.string.overview), "text/html; charset=UTF-8", null);*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        toolbar.setTitle("Company Overview");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                CompanyOverView.this.finish();
            }
        });
    }
}
