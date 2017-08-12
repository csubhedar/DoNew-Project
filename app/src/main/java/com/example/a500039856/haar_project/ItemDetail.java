package com.example.a500039856.haar_project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by This Pc on 06-04-2017.
 */
public class ItemDetail extends Activity {

    private static final String TAG = "ITEMDETAIL";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        Log.v(TAG, "Instantiated new " + this.getClass());
        setContentView(R.layout.item_detail);

    }

    public void onResume()
    {
        super.onResume();

    }

    @Override
    public void onPause()
    {
        super.onPause();

    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();

    }
}
