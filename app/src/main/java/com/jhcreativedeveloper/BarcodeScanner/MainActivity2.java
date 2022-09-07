package com.jhcreativedeveloper.BarcodeScanner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {
    WebView WevV;
    String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
       // data=MainActivity.setresult.toString();
        Toast.makeText(getApplicationContext(), "Data "+data, Toast.LENGTH_SHORT).show();
        WevV=findViewById(R.id.WevV);
        WevV.setWebViewClient(new WebViewClient());
        WevV.loadUrl("https://www.google.co.in/search?q="+data);
    }
    @Override
    public void onBackPressed() {
        if (WevV.canGoBack()) {
            WevV.goBack();
        } else {
            super.onBackPressed();
        }
    }
}