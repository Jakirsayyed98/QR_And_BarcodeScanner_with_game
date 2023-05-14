package com.jhcreativedeveloper.BarcodeScanner.Login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.jhcreativedeveloper.BarcodeScanner.BaseActivity
import com.jhcreativedeveloper.BarcodeScanner.MainActivity
import com.jhcreativedeveloper.BarcodeScanner.MainActivity2
import com.jhcreativedeveloper.BarcodeScanner.R
import com.jhcreativedeveloper.BarcodeScanner.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity<ActivitySplashBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        statusBarTextWhite()
        statusBarColor(R.color.blue)
        supportActionBar?.hide()
        setTimerLayout()

    }

    private fun setTimerLayout() {
        Handler(Looper.myLooper()!!).postDelayed({
           ContainerActivity.openContainer(this,"LoginFragment","")
            finish()
//            startActivity(Intent(this, MainActivity::class.java))
        },3000)
    }
}