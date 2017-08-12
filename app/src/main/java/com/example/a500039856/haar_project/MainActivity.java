package com.example.a500039856.haar_project;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Handler;

@TargetApi(19)
public class MainActivity extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {

    private static final String TAG = "MAINACT";
    private CameraBridgeViewBase mOpenCvCameraView;
   public static Mat mRgba;
    public static  File mRooCascade,mAmulCascade;
    public static CascadeClassifier mJavaRooDetector,mJavaAmulDetector;
    private boolean mIsPressed=false;

    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS: {
                    Log.i("OpenCV", "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                    break;
                }
                default:
                {
                    super.onManagerConnected(status);
                    break;
                }
            }

            try {
                // load cascade file from application resources
                InputStream is = getResources().openRawResource(R.raw.rooafza10);
                File roocascadeDir = getDir("cascade", Context.MODE_PRIVATE);
                mRooCascade = new File(roocascadeDir, "rooafza10.xml");
                FileOutputStream os = new FileOutputStream(mRooCascade);

                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = is.read(buffer)) != -1) {
                    os.write(buffer, 0, bytesRead);
                }
                is.close();
                os.close();

                mJavaRooDetector = new CascadeClassifier(mRooCascade.getAbsolutePath());
                if (mJavaRooDetector.empty()) {
                    Log.e(TAG, "Failed to load cascade classifier");
                    mJavaRooDetector = null;
                } else
                    Log.i(TAG, "Loaded cascade classifier from " + mRooCascade.getAbsolutePath());

                roocascadeDir.delete();

                is = getResources().openRawResource(R.raw.amul);
                File amulcascadeDir = getDir("cascade", Context.MODE_PRIVATE);
                mAmulCascade = new File(amulcascadeDir, "rooafza10.xml");
                os = new FileOutputStream(mAmulCascade);

                byte[] amulbuffer = new byte[4096];
                while ((bytesRead = is.read(amulbuffer)) != -1) {
                    os.write(amulbuffer, 0, bytesRead);
                }
                is.close();
                os.close();

                mJavaAmulDetector = new CascadeClassifier(mAmulCascade.getAbsolutePath());
                if (mJavaAmulDetector.empty()) {
                    Log.e(TAG, "Failed to load cascade classifier");
                    mJavaAmulDetector = null;
                } else
                    Log.i(TAG, "Loaded cascade classifier from " + mAmulCascade.getAbsolutePath());

                amulcascadeDir.delete();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG, "Failed to load cascade. Exception thrown: " + e);
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.v(TAG, "Instantiated new " + this.getClass());
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.main);

        mOpenCvCameraView = (JavaCameraView) findViewById(R.id.firstpage);
        mOpenCvCameraView.setVisibility(SurfaceView.VISIBLE);
        mOpenCvCameraView.setCvCameraViewListener(this);

        mOpenCvCameraView.setOnLongClickListener(new View.OnLongClickListener(){
            public  boolean onLongClick(View v){
                Log.v(TAG, "Clicked");
                Intent intent=new Intent(getBaseContext(),detect.class);
                startActivity( intent );
                return true;
            }
        });


    }


    public void onResume()
    {
        super.onResume();
        if (!OpenCVLoader.initDebug()) {
                   Log.d("OpenCV", "Internal OpenCV library not found. Using OpenCV Manager for initialization");
                   OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_0_0, this, mLoaderCallback);
                 } else {
                    Log.d("OpenCV", "OpenCV library found inside package. Using it!");
                    mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
               }

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
        if(mOpenCvCameraView!=null)
        {mOpenCvCameraView.disableView();}
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba=new Mat(height,width, CvType.CV_8UC4);
        Log.v(TAG, "oncameraviewstarted called");
    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba=inputFrame.rgba();
        return mRgba;
    }
}



