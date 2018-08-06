package com.example.rdx.vayparexpress;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.TextUtils;
import android.view.Window;

/**
 * Created by stpl on 7-12-2017.
 */
public class CommonFunction {
    public static ProgressDialog getProgressDialog(Context ctx) {
        ProgressDialog dialog = new ProgressDialog(ctx);
        try {
            dialog.setCancelable(false);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.show();
            dialog.setContentView(R.layout.custom_dialog);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return dialog;
    }
    public  static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
