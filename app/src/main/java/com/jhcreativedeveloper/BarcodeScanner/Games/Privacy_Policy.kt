package com.jhcreativedeveloper.BarcodeScanner.Games

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.jhcreativedeveloper.BarcodeScanner.R

class Privacy_Policy : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy_policy)
        val webView=findViewById<WebView>(R.id.webView)
        webView.settings.javaScriptEnabled=true
        webView.loadUrl("file:///android_asset/"+"privacy_policy.html")
    }
}