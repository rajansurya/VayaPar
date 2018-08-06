package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

/**
 * Created by Rajan Gupta on 23-05-2018.
 */

public class TestAnimation extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testview);
       final Button dd=(Button)findViewById(R.id.anim);
        final Animation a = AnimationUtils.loadAnimation(this, R.anim.scal);
        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
dd.startAnimation(a);
            }
        });
    }
}
