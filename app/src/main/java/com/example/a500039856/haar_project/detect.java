package com.example.a500039856.haar_project;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;




/**
 * Created by This Pc on 25-12-2016.
 */
public class detect extends MainActivity  {

    private static final String TAG = "DETECTACT";
    Mat mGray;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent=getIntent();
        mGray=mRgba.clone();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        Log.v(TAG, "Instantiated new " + this.getClass());
        setContentView(R.layout.activity_main);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerView);
        detectFunc();

    }

    public void detectFunc()
    {
        Imgproc.cvtColor(mRgba, mGray, Imgproc.COLOR_BGR2GRAY);
        Core.flip(mGray.t(), mGray,1);
        Log.v(TAG, "oncameraframe called");

        MatOfRect roo = new MatOfRect();
        MatOfRect amul = new MatOfRect();
        mJavaRooDetector.detectMultiScale(mGray, roo, 1.2, 6, 2, new Size(30, 90), new Size());
        mJavaAmulDetector.detectMultiScale(mGray, amul, 1.02, 6, 2, new Size(45, 50), new Size());

     //   if(roo.toArray().length!=0) {

            Toast toast = Toast.makeText(this, "Are you looking for Real Fruit juice", Toast.LENGTH_LONG);
            toast.show();
          //  getrss GetRss=new getrss(this,recyclerView);
          //  GetRss.execute();
        Intent intent=new Intent(getBaseContext(),webaccess.class);
        startActivity(intent);

    //    }
    /*    else if(amul.toArray().length!=0)
        {
            Toast toast_amul=Toast.makeText(this, "Are you looking for Amul Milk", Toast.LENGTH_LONG);
            toast_amul.show();
        }
        else
        { Toast toast_null=Toast.makeText(this, "Sorry could not recognize the object", Toast.LENGTH_LONG);
            toast_null.show();
        }
        */
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
