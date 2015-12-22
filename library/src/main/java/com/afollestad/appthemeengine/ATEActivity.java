package com.afollestad.appthemeengine;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author Aidan Follestad (afollestad)
 */
public class ATEActivity extends AppCompatActivity {

    private long updateTime = -1;

    @Nullable
    protected String getATEKey() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ATE.preApply(this, getATEKey());
        super.onCreate(savedInstanceState);
    }

    private void apply() {
        ATE.apply(this, getATEKey());
        updateTime = System.currentTimeMillis();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        apply();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        apply();
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        super.setContentView(view, params);
        apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (ATE.didValuesChange(this, getATEKey(), updateTime))
            recreate();
    }
}