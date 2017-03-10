package com.prowareness.contact;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Naveen Rawat on 10-03-2017.
 */

public class BaseActivity extends AppCompatActivity {

    private ProgressDialog progress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(this);
        progress.setMessage("Please wait ...");
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.setIndeterminate(true);
        progress.setCancelable(false);
    }

    public void showDialog() {

        if (progress != null && !progress.isShowing())
            progress.show();
    }

    public void dismissDialog() {
        if (progress != null && progress.isShowing())
            progress.dismiss();
    }
}
