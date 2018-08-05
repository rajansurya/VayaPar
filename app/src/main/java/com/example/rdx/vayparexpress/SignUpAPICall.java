package com.example.rdx.vayparexpress;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONObject;

/**
 * Created by stpl on 24-10-2017.
 */
public class SignUpAPICall extends AsyncTask<JSONObject, Void, String> {
    private String TAG;
    private CallBackToClass fragment;
    private String URL;
    private Context context;
    private ProgressDialog ringProgressDialog;
    int isPostOrGet;

    public SignUpAPICall(String url, int isPostOrGet, String tag, CallBackToClass fragment, Context context) {
        this.TAG = tag;
        this.fragment = fragment;
        this.URL = url;
        this.context = context;
        this.isPostOrGet = isPostOrGet;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        ringProgressDialog = CommonFunction.getProgressDialog(context);
    }

    @Override
    protected String doInBackground(JSONObject... params) {
        return isPostOrGet == 0 ? HttpConnection.performPostCall(URL, params[0], context) : HttpConnection.performGetCall(URL, context);
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        ringProgressDialog.dismiss();
        fragment.returnDataToClass(TAG, s);
    }

    public interface CallBackToClass {
        public void returnDataToClass(String tag, String data);
    }
}
