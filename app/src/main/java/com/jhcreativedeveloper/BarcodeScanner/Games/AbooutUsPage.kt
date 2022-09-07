package com.jhcreativedeveloper.BarcodeScanner.Games

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.jhcreativedeveloper.BarcodeScanner.R
import mehdi.sakout.aboutpage.AboutPage
import mehdi.sakout.aboutpage.Element
import java.util.*

class AbooutUsPage : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aboout_us_page)

        val aboutPage: View = AboutPage(this)
            .isRTL(false)
            .setDescription("This is the Online Qr & barcode Scanner App. Here You can Scan any QR & Barcode, and Share data with your friends. In this Application you can play 200+ games without install for Free of cost ")
            .addItem(Element().setTitle("Version 6"))
            .addGroup("CONNECT WITH US!")
            .addEmail("jakirsayyed98@gmail.com ") //  .addWebsite("Your website/")
            //  .addYoutube("UCbekhhidkzkGryM7mi5Ys_w")   //Enter your youtube link here (replace with my channel link)
            .addPlayStore("com.jhcreativedeveloper.BarcodeScanner") //Replace all this with your package name
            //  .addInstagram("jarves.usaram")    //Your instagram id
            .addItem(createCopyright())
            .create()
        setContentView(aboutPage)
    }

    private fun createCopyright(): Element? {
        val copyright = Element()
        @SuppressLint("DefaultLocale") val copyrightString = String.format(
            "Copyright %d by JH Creative Developers",
            Calendar.getInstance()[Calendar.YEAR]
        )
        copyright.setTitle(copyrightString)
        // copyright.setIcon(R.mipmap.ic_launcher);
        copyright.setGravity(Gravity.CENTER)
        copyright.setOnClickListener(View.OnClickListener {
            Toast.makeText(applicationContext, copyrightString, Toast.LENGTH_SHORT).show()
            //Toast.makeText((AboutUs.this.copyrightString,Toast.LENGTH_SHORT).show();
        })
        return copyright
    }

    override fun onBackPressed() {
      //  startActivity(Intent(applicationContext, HomeActivity::class.java))
        super.onBackPressed()
        finish()
    }
}