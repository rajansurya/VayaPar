package com.example.rdx.vayparexpress;//package com.example.rdx.vayparexpress;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.support.annotation.Nullable;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//
///**
// * Created by stpl on 28-12-2017.
// */
//
//public class IPChangerActivity extends Activity implements View.OnClickListener {
//    private ArrayList<IPChanger_Model> datal=new ArrayList<>();
//    private setIP setip;
//
//    @Override
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case 2:
//                setip.changeSystemIP("");
//                break;
//        }
//    }
//
//    interface setIP {
//        public void changeSystemIP(String id);
//    }
//    public IPChangerActivity(){
//
//    }
//    public IPChangerActivity(ArrayList<IPChanger_Model> data, setIP activity) {
//
////        this.data = data;
//        this.setip = activity;
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        for (int i=0;i<5;i++){
//            IPChanger_Model ob=new IPChanger_Model();
//            ob.setButtontext("IP Submit");
//            ob.setTextview_hint("enter ip"+i);
//            ob.setTextview_name("IP "+i);
//            datal.add(ob);
//        }
//
//        LinearLayout linearLayout = new LinearLayout(this);
//        linearLayout.setOrientation(LinearLayout.VERTICAL);
//
//        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        params.setMargins(5, 5, 5, 5);
//        for (int i = 1; i < datal.size(); i++) {
//            LinearLayout linearLayoutloc = new LinearLayout(this);
//            //add textView
//            TextView textView = new TextView(this);
//            textView.setText(datal.get(i).getTextview_name());
//            textView.setLayoutParams(params);
//
//            EditText editText = new EditText(this);
//            editText.setHint(datal.get(i).getTextview_hint());
//            editText.setId(i);
//            editText.setLayoutParams(params);
//            linearLayoutloc.addView(editText);
//            linearLayoutloc.addView(textView);
//
//            linearLayout.addView(linearLayoutloc);
//        }
//
//
//        // added Button
//        Button button = new Button(this);
//        button.setText("Submit");
//        button.setId(2);
//
//        //added the textView and the Button to LinearLayout
//
//        linearLayout.addView(button);
//        setContentView(linearLayout);
//    }
//
//    class IPChanger_Model {
//        private String textview_name;
//        private String textview_hint;
//        private String buttontext;
//
//        public String getTextview_name() {
//            return textview_name;
//        }
//
//        public void setTextview_name(String textview_name) {
//            this.textview_name = textview_name;
//        }
//
//        public String getTextview_hint() {
//            return textview_hint;
//        }
//
//        public void setTextview_hint(String textview_hint) {
//            this.textview_hint = textview_hint;
//        }
//
//        public String getButtontext() {
//            return buttontext;
//        }
//
//        public void setButtontext(String buttontext) {
//            this.buttontext = buttontext;
//        }
//
//
//    }
//}
