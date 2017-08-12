package com.example.a500039856.haar_project;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

/**
 * Created by This Pc on 23-04-2017.
 */
public class webaccess extends Activity {

    private WebView webView;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        mContext=this;
        Intent intent=getIntent();
        webView = (WebView) findViewById(R.id.webView1);
        String url="http://www.amazon.in/s/ref=nb_sb_ss_i_5_4?url=search-alias%3Daps&field-keywords=real+fruit+juice&sprefix=real%2Caps%2C355&crid=1CVIXN4YCFENX";
     //   if(SyncStateContract.Constants.isNetworkAvailable(mContext)){
            webView.setWebViewClient(new WebViewClient());
            webView.setWebChromeClient(new WebChromeClient());

            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setBuiltInZoomControls(true);
            webView.getSettings().setSupportZoom(true);

            webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
            webView.setScrollbarFadingEnabled(false);

            webView.setInitialScale(120);
            webView.loadUrl(url);

      //  }else{
      //      Toast.makeText(mContext, SyncStateContract.Constants.msgNoInternet, Toast.LENGTH_LONG).show();
     //   }
    }
}
