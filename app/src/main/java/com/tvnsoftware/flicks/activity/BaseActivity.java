package com.tvnsoftware.flicks.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.tvnsoftware.flicks.R;

/**
 * Created by TamHH on 6/15/2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        progress = new ProgressDialog(this);
        progress.setCancelable(false);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    protected void showLoading() {
        if (!progress.isShowing()) {
            progress.show();
        }
    }

    protected void hideLoading() {
        if (progress.isShowing()) {
            progress.dismiss();
        }
    }
}
