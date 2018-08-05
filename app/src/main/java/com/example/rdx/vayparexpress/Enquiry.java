package com.example.rdx.vayparexpress;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

/**
 * Created by RDX on 1/1/2018.
 */

public class Enquiry extends Activity implements View.OnClickListener, SignUpAPICall.CallBackToClass {
    private EditText company_name, email, phone, contact_person;
    private Button submit;
    TextView backto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.enquiry_view);
        PlayGifView pGif = (PlayGifView) findViewById(R.id.factorylogo);
        pGif.setImageResource(R.drawable.factory_gif);
        backto = (TextView) findViewById(R.id.backto);
        backto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // back button pressed
                Enquiry.this.finish();
            }
        });
        company_name = (EditText) findViewById(R.id.company_name);
        contact_person = (EditText) findViewById(R.id.contact_person);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        try {
            if (isValid()) {
                String URL = BuildConfig.SERVER_URL + "public/api/enquirystore";
                JSONObject data = new JSONObject();
                data.put("companyname", company_name.getText().toString());
                data.put("email", email.getText().toString());
                data.put("phone", phone.getText().toString());
//                data.put("mobile", mobile.getText().toString());
                data.put("contactperson", contact_person.getText().toString());
//                data.put("dealswith", dealswith.getText().toString());
                new SignUpAPICall(URL, 0, "SEARCH_BUSSINESS", Enquiry.this, this).execute(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isValid() {
        if (TextUtils.isEmpty(company_name.getText().toString())) {
            Toast.makeText(Enquiry.this, "Please enter business name", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(contact_person.getText().toString())) {
            Toast.makeText(Enquiry.this, "Please enter contact person", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(email.getText().toString())) {
            Toast.makeText(Enquiry.this, "Please enter email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (!CommonFunction.isValidEmail(email.getText().toString())) {
            Toast.makeText(Enquiry.this, "Please enter correct email", Toast.LENGTH_SHORT).show();
            return false;
        } else if (TextUtils.isEmpty(phone.getText().toString())) {
            Toast.makeText(Enquiry.this, "Please enter phone no", Toast.LENGTH_SHORT).show();
            return false;
        }
//        else if (TextUtils.isEmpty(mobile.getText().toString())){
//            Toast.makeText(Enquiry.this, "Please enter mobile no", Toast.LENGTH_SHORT).show();
//            return false;
//        }
        return true;
    }

    @Override
    public void returnDataToClass(String tag, String data) {
        System.out.println("data" + data);
        try {
            JSONObject dat = new JSONObject(data);
            String message = dat.getString("message");
            Toast.makeText(Enquiry.this, message, Toast.LENGTH_SHORT).show();
            Enquiry.this.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        data{"success":true,"object":null,"message":"Thanks for your Feedback"}
    }
}
